package algorithm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <pre>
 * algorithm 
 * MariaDBSelect.java
 *
 * 설명 : jdbc를 이용한 select 예제
 * </pre>
 * 
 * @since : 2020. 5. 24.
 * @author : ymg74
 * @version : v1.0
 */
public class MariaDBSelect {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://dev-swh.ga/minkyu";

	static final String USERNAME = "root";
	static final String PASSWORD = "swhacademy!";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("MariaDB 연결.");
			statement = connection.createStatement();

			//	select
			ResultSet rs = statement.executeQuery("SELECT * FROM employees;");
			
			while(rs.next()){
				int employee_id = rs.getInt("employee_id");
				String name = rs.getString("name");
				double hourly_pay = rs.getDouble("hourly_pay");
				long employee_contact = rs.getLong("employee_contact");
				System.out.println("employee_id : " + employee_id);
				System.out.println("name: " + name);
				System.out.println("hourly_pay: " + hourly_pay);
				System.out.println("employee_contact: " + employee_contact);
				System.out.println(rs.getInt(1));	// 첫번 째 열
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
		System.out.println("MariaDB 연결종료.");
	}
}
