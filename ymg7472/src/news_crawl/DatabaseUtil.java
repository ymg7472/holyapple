package news_crawl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.bitbucket.eunjeon.seunjeon.Morpheme;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DatabaseUtil {
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	String DB_URL = null;
	String USERNAME = null;
	String PASSWORD = null;
	
	Connection connection = null;
	Statement statement = null;
	DatabaseUtil (String url, String name, String pw) {
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
	
	public String byId(String id) {
		String result = "";
		try {
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
		}
		
		return result;
	}

	public String byDate(String sub1, String wantdate1) {
		ArrayList<C_news> hihi = new ArrayList<C_news>();
		try {
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
		}
		String lili = new GsonBuilder().serializeNulls().create().toJson(hihi);
		return lili;
	}
	
	public ArrayList<WordCloud> baba(String sub1, String wantdate1) {
		String mor = "";
		ArrayList<String> data = new ArrayList<String>();	
		try{
			ResultSet rs = statement.executeQuery("select * from CrawlNews where subject = '" + sub1 + "' and date = " + wantdate1 +";");

			while(rs.next()){
				for (LNode node : Analyzer.parseJava(rs.getString("title"))) {
					Morpheme m = node.morpheme();
					mor = m.feature().head().toString();
					if(mor.equals("NNG") || mor.equals("NNP")) {
						if(!m.surface().equals("네이버") && !m.surface().equals("뉴스")) {
							data.add(m.surface());
						}
					}
				}

			}
		}catch(SQLException se1){
			se1.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		ArrayList<WordCloud> wl = new ArrayList<WordCloud>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i<data.size(); i++) {
			if(!map.containsKey(data.get(i))) {
				map.put(data.get(i), 1);
			}else {
				map.put(data.get(i), map.get(data.get(i))+1);
			}
		}
		List<String> keList = new ArrayList<>(map.keySet());
		List<Integer> vaList = new ArrayList<>(map.values());
		for(int i = 0; i<map.size(); i++) {
			if(vaList.get(i)>=8) {
				WordCloud w = new WordCloud(keList.get(i), vaList.get(i));
				wl.add(w);
			}
		}
		
		return wl;
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
