package tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.google.gson.Gson;

import naver_news_spark.HibernateUtil;
import naver_news_spark.models.C_news;


public class TcpServer {	
	public static void main(String[] args){
			SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
			ServerSocket ss = null;		// 서버소켓 변수
			Socket sock = null;		// 접속할 클라이언트 소켓변수
			try {
				ss = new ServerSocket(9999);	// 서버소켓생성
				System.out.println("서버 대기중 ");
				while (true) {
					// 클라이언트가 접속 할때까지 대기하다가 접속하면 socket생성
					sock = ss.accept();
					// 클라이언트에서 보낸 한줄의 메시지를 출력
					BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					String msg = in.readLine();
//					System.out.println(msg);
					C_news jnews = new Gson().fromJson(msg, C_news.class);
//					System.out.println(jnews.toString());
					Session session = sessionFactory.openSession();
					session.beginTransaction();
					session.save(jnews);
//					System.out.println(jnews.toString());
					session.getTransaction().commit();
					//클라이언트와의 연결 끊기
					in.close();
					sock.close();
				}
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
		}
	}
