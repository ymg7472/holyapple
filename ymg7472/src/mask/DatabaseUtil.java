package mask;

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

import mask.model.MaskInfo;
import mask.model.ResponseSales;
import mask.model.ResponseStores;
import mask.model.Sales;
import mask.model.Stores;

/**
 * <pre>
 * mask 
 * DatabaseUtil.java
 *
 * 설명 : 마스크 정보를 select, crawl 하는 클래스
 * 
 * </pre>
 * 
 * @since : 2020. 5. 24.
 * @author : ymg74
 * @version : v1.0
 */
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
	
	public ArrayList<MaskInfo> getMaskInfo() {
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
			ResultSet rs = statement.executeQuery("SELECT * FROM maskstores;");

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
			ResponseSales s2 = gson.fromJson(pre, ResponseSales.class);
			if(s2.getSales().isEmpty()) {
				break;
			}
			
			
			ArrayList<Sales> info1 = s2.getSales();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			for(Sales c:info1) {
				session.saveOrUpdate(c);
//				session.close();
			}
			session.getTransaction().commit();
			session.close();

		}
		sessionFactory.close();
		System.out.println("트랜잭션 정상처리");
		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime)/1000;
		System.out.println("소요시간(초) : "+secDiffTime);
		
		
	}
	
	public void crawlStores() throws SQLException, ClassNotFoundException{
		long beforeTime = System.currentTimeMillis();
		Document doc= null;
		int page = 0;
		String page1 = "";
		Gson gson = new Gson();
		SessionFactory sessionFactory = HibernateUtil7.getSessionFactory();
		while(true) {
			page++;
			page1 = Integer.toString(page);	
			String url = "https://8oi9s0nnth.apigw.ntruss.com/corona19-masks/v1/stores/json?page=" + page1;
			try {
				doc = Jsoup.connect(url).ignoreContentType(true).timeout(0).get();
			} catch (IOException e) {
				e.printStackTrace();
			} 
			String pre = doc.text();
			ResponseStores s2 = gson.fromJson(pre, ResponseStores.class);
			if(s2.getStoreInfos().isEmpty()) {
				break;
			}
			ArrayList<Stores> info1 = s2.getStoreInfos();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			for(Stores c:info1) {
				session.saveOrUpdate(c);
			}
			session.getTransaction().commit();
			session.close();

		}
		sessionFactory.close();
		System.out.println("트랜잭션 정상처리");
		long afterTime = System.currentTimeMillis();
		long secDiffTime = (afterTime - beforeTime)/1000;
		System.out.println("소요시간(초) : "+secDiffTime);
	}
	
	public ArrayList<MaskInfo> getMaskInfoByName(String s) {
		ArrayList<MaskInfo> hihi = new ArrayList<MaskInfo>();
		try {
		ResultSet rs = statement.executeQuery("SELECT a.created_at, a.remain_stat, a.stock_at, b.code, b.name, b.addr, b.`type`, b.lat, b.lng FROM masksales AS a, maskstores AS b WHERE a.code=b.code AND b.name like '%"+ s +"%';");
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
	public void close() {
		try{
			if(statement!=null) statement.close();
			if(connection!=null) connection.close();
		}catch(SQLException se2){
			se2.printStackTrace();
		}
	}
	
}
