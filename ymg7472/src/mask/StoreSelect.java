package mask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

import mask.model.Stores;


public class StoreSelect {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://dev-swh.ga/minkyu";

	static final String USERNAME = "root";
	static final String PASSWORD = "swhacademy!";
	public ArrayList<Stores> getStore() {
		Connection connection = null;
		Statement statement = null;
		ArrayList<Stores> hihi = new ArrayList<Stores>();
		try{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("MariaDB ¿¬°á.");
			statement = connection.createStatement();


			//	select
			ResultSet rs = statement.executeQuery("SELECT * FROM maskstores limit 100;");

			while(rs.next()){
				Stores s = new Stores();
				s.setCode(rs.getString("code"));
				s.setName(rs.getString("name"));
				s.setAddr(rs.getString("addr"));
				s.setType(rs.getString("type"));
				s.setLat(rs.getString("lat"));
				s.setLng(rs.getString("lng"));
				hihi.add(s);
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
		return hihi;
	}
}
