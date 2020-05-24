package calender_crawl;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.bitbucket.eunjeon.seunjeon.Morpheme;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
			if(vaList.get(i)>=20) {
				WordCloud w = new WordCloud(keList.get(i), vaList.get(i));
				wl.add(w);
			}
		}
		
		return wl;
	}
	public void crawlNews(String wantSub, String wantDate) throws IOException{
		PrintStream s = System.out;
		String num1="100";
		String num2="269";
		int how = 0;
		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("http://localhost:4567/users");
		if(wantSub.equals("정치")) {
			num1="100";
			num2="269";
		}else if(wantSub.equals("경제")){
			num1="101";
			num2="263";
		}else if(wantSub.equals("사회")) {
			num1="102";
			num2="257";
		}else {
			num1="102";
			num2="257";

		}
		ArrayList<String> links = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<C_news> news1 = new ArrayList<C_news>();
		int holy = 1;
		while(true) {
			String articleURL = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2="+ num2 +"&sid1="+ num1 +"&mid=shm&date="+ wantDate +"&page="+ holy;
			Document doc= null;
			try {
				doc = Jsoup.connect(articleURL).timeout(0).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Elements ele = doc.getElementsByClass("list_body newsflash_body");
			Elements news = ele.select("a");
			//		s.println(news.eachAttr("href"));
			ArrayList<String> readylinks = (ArrayList<String>) news.eachAttr("href");
			ArrayList<String> r1 = new ArrayList<String>();
			if (links.contains(readylinks.get(1))) {

				break;	
			}else {
				for(int i = 0; i < readylinks.size(); i++) {
					if(!r1.contains(readylinks.get(i))) {
						r1.add(readylinks.get(i));
					}
				}
				for(int i = 0; i < r1.size(); i++) {
					links.add(r1.get(i));
				}
			}


			for(int i = 0; i < r1.size(); i++) {
				String g = r1.get(i);
				id.add(g.substring(g.length()-10, g.length()));
			}

			for(int i = 0; i < r1.size(); i++) {
				String sub = wantSub;
				C_news n = new C_news();
				n.setSubject(sub);
				String idVal = "";
				Pattern pattern = Pattern.compile("(?<=(&oid=|\\?oid=)|(&aid=|\\?aid=))[\\d]+");
				String articleURL1 = r1.get(i);
				Matcher m = pattern.matcher(articleURL1);
				while(m.find()) {
					//group() 메소드를 호출하고 정규 표현에 일치된 문자열을 꺼냄
					idVal = idVal + m.group() +"-";

				}
				//				String aid = articleURL1.substring(articleURL1.length()-10, articleURL1.length()); 
				//				String oid = articleURL1.substring(articleURL1.length()-18, articleURL1.length()-15);
				n.setId(idVal.substring(0,idVal.length()-1));
				//				System.out.println(n.getId());
				//				n.setId(oid+"-"+aid);
				Document doc1=null;
				try {
					doc1 = Jsoup.connect(articleURL1).timeout(0).get();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Elements t1 = doc1.select("title");
				String tit = t1.text().toString();
				n.setTitle(tit);
				t1 = doc1.getElementsByClass("_article_body_contents");
				n.setContents(t1.text());
				t1 = doc1.getElementsByClass("t11");
				n.setDate(wantDate);

				String json = new Gson().toJson(n);
				HttpEntity entity = new StringEntity(json, "UTF-8");
				request.setEntity(entity);
				HttpResponse response = client.execute(request);
				String result = EntityUtils.toString(response.getEntity());
				System.out.println(result);
					//			            //  응답
					//			            String result = EntityUtils.toString(response.getEntity());
					//			            System.out.println(result);
			}
			//s.println(json);
			client = HttpClients.createDefault();
			readylinks.clear();
			r1.clear();
			s.println(holy);
			holy++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
