package mask;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;

import mask.model.MaskInfo;
import mask.model.Sales;
import mask.model.Stores;

public class DatabaseUtil {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	String DB_URL = null;
	String USERNAME = null;
	String PASSWORD = null;
	
	Connection connection = null;
	Statement statement = null;
	public DatabaseUtil (String url, String name, String pw) {
		DB_URL = url;
		USERNAME = name;
		PASSWORD = pw;
		try{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("MariaDB 연결.");
			statement = connection.createStatement();
		}catch(SQLException se1){
			se1.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public ArrayList<MaskInfo> maskInfo() {
		ArrayList<MaskInfo> hihi = new ArrayList<MaskInfo>();
		try {
		ResultSet rs = statement.executeQuery("SELECT a.created_at, a.remain_stat, a.stock_at, b.code, b.name, b.addr, b.`type`, b.lat, b.lng FROM masksales AS a, maskstores AS b WHERE a.code=b.code AND b.addr like '경기도 이천%';");
		while(rs.next()){
			MaskInfo m = new MaskInfo(); 
			m.setCode(rs.getString("code"));
			m.setName(rs.getString("name"));
			m.setAddr(rs.getString("addr"));
			m.setType(rs.getString("type"));
			m.setLat(rs.getString("lat"));
			m.setLng(rs.getString("lng"));
			m.setCreated_at(rs.getString("created_at"));
			m.setRemain_stat(rs.getString("remain_stat"));
			m.setStock_at(rs.getString("stock_at"));
			hihi.add(m);
		}
		rs.close();
		}catch(SQLException se1){
			se1.printStackTrace();
		}
		
		return hihi;
	}
	public ArrayList<Sales> getSales() {
		ArrayList<Sales> hihi = new ArrayList<Sales>();
		try {
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
		}
		return hihi;
	}
	public ArrayList<Stores> getStore() {
		ArrayList<Stores> hihi = new ArrayList<Stores>();
		try {
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
		}
		return hihi;

	}
	public void crawlSales() throws SQLException, ClassNotFoundException{
		long beforeTime = System.currentTimeMillis();
		Document doc= null;
		int page = 0;
		String page1 = "";
		Gson gson = new Gson();
		SessionFactory sessionFactory = HibernateUtil7.getSessionFactory();
		while(true) {
			page++;
			if(page==37) continue;
			page1 = Integer.toString(page);
			String url = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/sales/json?page=" + page1;
			try {
				doc = Jsoup.connect(url).ignoreContentType(true).timeout(0).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			String pre = doc.text();
		}
		
	}
	public void close() {
		try{
			if(statement!=null) statement.close();
			if(connection!=null) connection.close();
		}catch(SQLException se2){
			se2.printStackTrace();
		}
	}
	
}
