package naver_news_spark; 

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
 * naver_news_spark 
 * NewsReceiver.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 7. 5.
 * @author : ymg74
 * @version : v1.0
 */
public class NewsReceiver {

	private static final String EXCHANGE_NAME = "ymg7472";
	
	// argv -> "kern.*" // "*.critical" // "kern.*" "*.critical" // "kern.critical" "A critical kernel error"
	
	public static void main(String[] argv) throws Exception {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("dev-swh.ga");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare("minkyu_test", false, false, false, null);
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String queueName = channel.queueDeclare().getQueue();
		
//		channel.queueBind("minkyu_test", EXCHANGE_NAME, "naver_news");
		
		System.out.println(queueName);
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
				AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				C_news jnews = new Gson().fromJson(message, C_news.class);
				try {
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					session.save(jnews);
					session.getTransaction().commit();
					session.close();
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				} 
			}
		};
 
		channel.basicConsume(queueName, true, consumer);
	
	}

}
