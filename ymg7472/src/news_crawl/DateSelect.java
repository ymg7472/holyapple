package news_crawl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;
public class DateSelect {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://dev-swh.ga/minkyu";

	static final String USERNAME = "root";
	static final String PASSWORD = "swhacademy!";
	public String getByDate(String sub1, String wantdate1) {
		Connection connection = null;
		Statement statement = null;
		ArrayList<String> ni = new ArrayList<String>();
		ArrayList<C_news> hihi = new ArrayList<C_news>();
		try{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("MariaDB ¿¬°á.");
			statement = connection.createStatement();


			//	select
			ResultSet rs = statement.executeQuery("select * from CrawlNews where subject = '" + sub1 + "' and date = " + wantdate1 +";");

			while(rs.next()){
				C_news n = new C_news();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("title"));
				n.setContents(rs.getString("contents"));
				n.setDate(rs.getString("date"));
				n.setSubject(rs.getString("subject"));
				hihi.add(n);
			}
			rs.close();
		}catch(SQLException se1){
			se1.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try{
				if(statement!=null) statement.close();
				if(connection!=null) connection.close();
			}catch(SQLException se2){
			}
		}
		String lili = new GsonBuilder().serializeNulls().create().toJson(hihi);
		return lili;
	}


}
