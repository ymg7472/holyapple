package movieCrawl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * <pre>
 * kr.co.swh.lecture.database.java
 * MariaDBTransaction.java
 *
 * 설명 :데이터베이스 트랜잭션 예제
 * </pre>
 * 
 * @since : 2017. 10. 26.
 * @author : tobby48
 * @version : v1.0
 */
public class MariaDBTransaction1 {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://192.168.0.41/minkyu";

	static final String USERNAME = "root";
	static final String PASSWORD = "swhacademy!";

	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Connection connection = null;
		PreparedStatement preparedStatementInsert = null;
		PreparedStatement preparedStatementUpdate = null;

		String insertTableSQL = "INSERT INTO ranking"
				+ "(name) VALUES"
				+ "(?)";

		try{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("MariaDB 연결.");

			connection.setAutoCommit(false);
			String url = "http://www.cgv.co.kr/movies/"; 
			Document doc = null;        

			try {
				doc = Jsoup.connect(url).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Elements element = doc.select("div.sect-movie-chart");    
			Iterator<Element> ie2 = element.select("strong.title").iterator();
			preparedStatementInsert = connection.prepareStatement(insertTableSQL);
			
		
	    
		
			while (ie2.hasNext()) {
				String name = ie2.next().text();

				preparedStatementInsert.setString(1, name);
				preparedStatementInsert.executeUpdate();
				preparedStatementInsert.clearParameters();
			}
			
			//preparedStatementInsert = connection.prepareStatement(insertTableSQL);

			
			/*	정상처리	*/
//			preparedStatementUpdate.setString(1, "소리");
//			preparedStatementUpdate.setInt(2, 168);
			
			/*	오류
			 * 	첫번째 쿼리가 수행되지 않음	
			 * */
			
			
			//preparedStatementUpdate.executeUpdate();
			connection.commit();
			System.out.println("트랜잭션 정상처리");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			connection.rollback();
		} finally {
			if (preparedStatementInsert != null) preparedStatementInsert.close();
			if (preparedStatementUpdate != null) preparedStatementUpdate.close();
			if (connection != null) connection.close();
		}
		System.out.println("MariaDB 연결종료.");
	}

}