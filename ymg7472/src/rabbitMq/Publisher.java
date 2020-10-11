package rabbitMq; 

import org.apache.log4j.Logger;


import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <pre>
 * rabbitMq 
 * Publisher.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 6. 14.
 * @author : ymg74
 * @version : v1.0
 */
public class Publisher {

	private final static String QUEUE_NAME = "hello";

	public static void main(String[] args) throws IOException, TimeoutException {
		Scanner s = new Scanner(System.in);
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("dev-swh.ga");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//		System.out.println("write");
//		String msg = s.nextLine();
		channel.basicPublish("", QUEUE_NAME, null, "hello world".getBytes());
		
		channel.close();
		connection.close();
	}
}