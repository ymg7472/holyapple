package naver_news_spark; 

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.util.Scanner;
/**
 * <pre>
 * naver_news_spark 
 * NewsSender.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 7. 5.
 * @author : ymg74
 * @version : v1.0
 */
public class NewsSender {

	private static final String EXCHANGE_NAME = "ymg7472";

	public static void main(String[] argv) throws Exception {
		Scanner sc = new Scanner(System.in);
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("dev-swh.ga");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String routingKey = "test";
		System.out.println("키워드 입력");
		String message = sc.nextLine();

		channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + routingKey + "':'" + message + "'");
	}

}
