package rabbitMqPost; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <pre>
 * rabbitMqPost 
 * FileThread.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 10. 4.
 * @author : ymg74
 * @version : v1.0
 */
public class FileThread extends Thread {
	String where;
	private final static String QUEUE_NAME = "minkyu2";
	Channel channel = null;
	public FileThread(String where) throws IOException, TimeoutException {
		this.where = where;
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("dev-swh.ga");
		Connection connection = factory.newConnection();
		channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
	}
	
	public void run(){
        File file = new File("C:\\text\\"+where);
        FileInputStream fin = null;
        try {
            // create FileInputStream object
            fin = new FileInputStream(file);
 
            byte fileContent[] = new byte[(int)file.length()];
             
            // Reads up to certain bytes of data from this input stream into an array of bytes.
            fin.read(fileContent);
            //create string from byte array
            String s = new String(fileContent);
            System.out.println("File content: " + s);
        }
        finally {
            // close the streams using close method
            try {
                if (fin != null) {
                    fin.close();
                }
            }
            catch (IOException ioe) {
                System.out.println("Error while closing stream: " + ioe);
            }
        }

	}
}
