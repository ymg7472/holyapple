package rabbitMqPost; 


import static spark.Spark.post;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeoutException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import naver_news_spark.models.C_news;

/**
 * <pre>
 * healthinfo 
 * HealthSpark.java
 *
 * ���� :
 * </pre>
 * 
 * @since : 2020. 8. 30.
 * @author : ymg74
 * @version : v1.0
 */
public class RabbitSpark {
	private final static String QUEUE_NAME = "minkyu2";
	FileOutputStream fos = null;
	
	public void writeFile1(String name) {
		File file = new File("C:\\text\\"+name);
		try {
			fos = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeFile2(byte[] body) throws FileNotFoundException {
		//		long time = System.currentTimeMillis(); 
		//		SimpleDateFormat dayTime = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss.SSS초");
		//		String str = dayTime.format(new Date(time));
		try {
			fos.write(body);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void writeFile3() {
		if(fos != null) {
			try {
				fos.flush();
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
//        try {
//            writer = new FileWriter(file, true);
//            writer.write(body);
//            writer.flush();
//        } catch(IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if(writer != null) writer.close();
//            } catch(IOException e) {
//                e.printStackTrace();
//            }
//        }

	public void sendMsg(byte[] body) {
		HttpClient client = HttpClients.createDefault();
		Gson gson = new Gson();
		String json = new String(body);
		Payload msg = gson.fromJson(json, Payload.class);
		HttpPost request = new HttpPost("http://localhost:4567/"+msg.getMsg());
        try {
            //  �߰��� ������
            HttpEntity entity = new StringEntity(json);
            //  ����
            request.setEntity(entity);
            HttpResponse response = client.execute(request);          
            //  ����
            String result = EntityUtils.toString(response.getEntity());
//            System.out.println(result);
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
	}
	public static void main(String[] args) throws IOException, TimeoutException {
		// TODO Auto-generated method stub
		RabbitSpark rs = new RabbitSpark();
		ConnectionFactory factory = new ConnectionFactory();
		Gson gson = new Gson();
		factory.setHost("dev-swh.ga");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
		Consumer consumer = new DefaultConsumer(channel) {
			int t = 1;
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body) throws IOException {
				rs.sendMsg(body);
				System.out.println(t);
				t++;
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
		//그런것 같아요
		post("/start", (request, response) -> {
			Payload p = gson.fromJson(request.body(), Payload.class);
			String temp = new String(p.getContent());
			rs.writeFile1(temp);
			return "success ";
		});
		post("/file", (request, response) -> {
			Payload p = gson.fromJson(request.body(), Payload.class);
			rs.writeFile2(p.getContent());
			return "success ";
		});
		post("/end", (request, response) -> {
			Payload p = gson.fromJson(request.body(), Payload.class);
			rs.writeFile3();
			return "success ";
		});

	}

}
