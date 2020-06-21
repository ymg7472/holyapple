package mask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <pre>
 * mask 
 * DbUtil.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 6. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class DbUtil {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	String DB_URL = null;
	String USERNAME = null;
	String PASSWORD = null;
	
	private Connection connection = null;
	private Statement statement = null;
	public DbUtil (String url, String name, String pw) {
		DB_URL = url;
		USERNAME = name;
		PASSWORD = pw;
		try{
			Class.forName(JDBC_DRIVER);
			setConnection(DriverManager.getConnection(DB_URL,USERNAME,PASSWORD));
			System.out.println("MariaDB 연결.");
			setStatement(getConnection().createStatement());
		}catch(SQLException se1){
			se1.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	public Statement getStatement() {
		return statement;
	}
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	public Connection getConnection() {
		return connection;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
