package rabbitMq; 


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import java.util.concurrent.TimeoutException;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.*;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import mask.model.MaskInfo;
import mask.model.ResponseSales;
import mask.model.ResponseStores;
import mask.model.Sales;
import mask.model.Stores;


public class RabbitTest {
	private final static String QUEUE_NAME = "minkyu_test";
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	String DB_URL = null;
	String USERNAME = null;
	String PASSWORD = null;
	
	Connection connection1 = null;
	Statement statement = null;
	public RabbitTest (String url, String name, String pw) {
		DB_URL = url;
		USERNAME = name;
		PASSWORD = pw;
		try{
			Class.forName(JDBC_DRIVER);
			connection1 = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("Connected!");
			statement = connection1.createStatement();
		}catch(SQLException se1){
			se1.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public ArrayList<MaskInfo> getMaskInfoByName(String s) {
		ArrayList<MaskInfo> hihi = new ArrayList<MaskInfo>();
		try {
		ResultSet rs = statement.executeQuery("SELECT a.created_at, a.remain_stat, a.stock_at, b.code, b.name, b.addr, b.`type`, b.lat, b.lng FROM masksales AS a, maskstores AS b WHERE a.code=b.code AND b.name like '%"+ s +"%';");
		while(rs.next()){
			MaskInfo m = new MaskInfo(); 
			m.setAll(rs);
			hihi.add(m);
		}
		rs.close();
		}catch(SQLException se1){
			se1.printStackTrace();
		}
		
		return hihi;
	}
	public void close() {
		try{
			if(statement!=null) statement.close();
			if(connection1!=null) connection1.close();
		}catch(SQLException se2){
			se2.printStackTrace();
		}
	}
	public static void main(String[] argv) throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		RabbitTest rb = new RabbitTest("jdbc:mysql://dev-swh.ga/minkyu", "root", "swhacademy!");
		factory.setHost("dev-swh.ga");
		com.rabbitmq.client.Connection connection;
		connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope,
					AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(rb.getMaskInfoByName(message));
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
	
}

