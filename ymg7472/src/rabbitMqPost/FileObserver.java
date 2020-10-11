package rabbitMqPost;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import thread.ThreadBasic;
public class FileObserver{
	private final static String QUEUE_NAME = "minkyu2";
    public static void main(String[] args) throws TimeoutException {
        String targetDirectory = "C:\\text1";
		observeFileCreated(targetDirectory);

    }
    
    public void send(String where) throws IOException, TimeoutException {
    	ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("dev-swh.ga");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		
		Gson gson = new Gson();
		Payload p1 = new Payload("start", where.getBytes());//where = asd.txt
		channel.basicPublish("", QUEUE_NAME, null, gson.toJson(p1).getBytes());
		
		File file = new File("C:\\text1\\"+where);
		int seekSize = 1024 * 1024;
		RandomAccessFile rdma = new RandomAccessFile("C:\\text1\\"+where,"r");
		int beforeSize = (int) rdma.length();
		byte[] data = null;
		// 루프 사이즈 = 총길이/seekSize + (총길이%seekSize의 나머지가 0이면 0을 반환 0이아니면 1을 반환)
		long size = rdma.length()/seekSize+(rdma.length()%seekSize == 0 ? 0:1);
		Payload p2 = null;
		for (int i = 0; i < size; i++) {
			if(beforeSize<seekSize) {
				data = new byte[beforeSize];
			}
			else {
				data = new byte[seekSize];
			}
			// seekSize 만큼 증가
			rdma.seek(i*seekSize);
			rdma.read(data);
			beforeSize = beforeSize - seekSize;
			
//			String s = new String(data);
			p2 = new Payload("file", data);
			String json = gson.toJson(p2);
			channel.basicPublish("", QUEUE_NAME, null, json.getBytes());
		}
		Payload p3 = new Payload("end", null);
		String json = gson.toJson(p3);
		channel.basicPublish("", QUEUE_NAME, null, json.getBytes());
		rdma.close();
        channel.close();
        file.delete();
    }
    
    private static void observeFileCreated(String targetDirectory) throws TimeoutException {
    	FileObserver fs = new FileObserver();
        Path faxFolder = Paths.get(targetDirectory);
        try {
            WatchService fileWatchService = FileSystems.getDefault().newWatchService();
            faxFolder.register(fileWatchService, StandardWatchEventKinds.ENTRY_CREATE);
            boolean valid = true;
            do {
                WatchKey watchKey = fileWatchService.take();
                for (WatchEvent event: watchKey.pollEvents()) {
                    WatchEvent.Kind kind = event.kind();
                    if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
                    	fs.send(event.context().toString());
                    }	
                }
                valid = watchKey.reset();
            } while (valid);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
