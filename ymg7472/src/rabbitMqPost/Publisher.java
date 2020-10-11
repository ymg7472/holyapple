package rabbitMqPost; 

import org.apache.log4j.Logger;


import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

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

	private final static String QUEUE_NAME = "minkyu_test";

	public static void main(String[] args) throws IOException, TimeoutException {
		Scanner s = new Scanner(System.in);
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("dev-swh.ga");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println("입력:");
		String msg = s.nextLine();
		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		System.out.println(msg + "을(를) 보냄");
		
		channel.close();
		connection.close();
	}
}