package calender_crawl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.Gson;

public class IdSelect {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://dev-swh.ga/minkyu";

	static final String USERNAME = "root";
	static final String PASSWORD = "swhacademy!";
	public String mama(String id) {
	
		Connection connection = null;
		Statement statement = null;
		String result = "";
		try{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("MariaDB ¿¬°á.");
			statement = connection.createStatement();


			//	select
			ResultSet rs = statement.executeQuery("select * from CrawlNews where ID = '" + id + "';");

			while(rs.next()){
				C_news n = new C_news();
				n.setId(rs.getString("ID"));
				n.setTitle(rs.getString("title"));
				n.setContents(rs.getString("contents"));
				n.setDate(rs.getString("date"));
				n.setSubject(rs.getString("subject"));
				result = new Gson().toJson(n);
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
		return result;
	}
}
