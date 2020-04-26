package mask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

import mask.model.Sales;

public class SalesSelect {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://dev-swh.ga/minkyu";

	static final String USERNAME = "root";
	static final String PASSWORD = "swhacademy!";
	public ArrayList<Sales> getSales() {
		Connection connection = null;
		Statement statement = null;
		ArrayList<Sales> hihi = new ArrayList<Sales>();
		try{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("MariaDB ¿¬°á.");
			statement = connection.createStatement();


			//	select
			ResultSet rs = statement.executeQuery("select * from masksales;");

			while(rs.next()){
				Sales s = new Sales();
				s.setCode(rs.getString("code"));
				s.setCreated_at(rs.getString("created_at"));
				s.setRemain_stat(rs.getString("remain_stat"));
				s.setStock_at(rs.getString("stock_at"));
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
		return hihi;
	}

}
