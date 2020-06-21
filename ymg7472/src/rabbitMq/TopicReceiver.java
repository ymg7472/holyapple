package rabbitMq; 


import com.rabbitmq.client.*;
import java.io.IOException;


/**
 * <pre>
 * rabbitMq 
 * TopicReceiver.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 6. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class TopicReceiver {
	
	private static final String EXCHANGE_NAME = "news.new";
	
	// argv -> "kern.*" // "*.critical" // "kern.*" "*.critical" // "kern.critical" "A critical kernel error"
	
	public static void main(String[] argv) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("dev-swh.ga");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String queueName = channel.queueDeclare().getQueue();
		
		channel.queueBind(queueName, EXCHANGE_NAME, "naverKey");
		
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
				AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
 
		channel.basicConsume(queueName, true, consumer);
	
	}
}