package news_crawl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.bitbucket.eunjeon.seunjeon.Morpheme;

import com.google.gson.GsonBuilder;
public class Morpheme1 {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://dev-swh.ga/minkyu";

	static final String USERNAME = "root";
	static final String PASSWORD = "swhacademy!";
	public ArrayList<WordCloud> baba(String sub1, String wantdate1) {
		Connection connection = null;
		Statement statement = null;
		ArrayList<String> data = new ArrayList<String>();	
		String mor = "";
		try{
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
			System.out.println("MariaDB ?ó∞Í≤?.");
			statement = connection.createStatement();


			//	select
			ResultSet rs = statement.executeQuery("select * from CrawlNews where subject = '" + sub1 + "' and date = " + wantdate1 +";");

			while(rs.next()){
				for (LNode node : Analyzer.parseJava(rs.getString("title"))) {
					Morpheme m = node.morpheme();
					mor = m.feature().head().toString();
					if(mor.equals("NNG") || mor.equals("NNP")) {
						if(!m.surface().equals("?Ñ§?ù¥Î≤?") && !m.surface().equals("?â¥?ä§")) {
							data.add(m.surface());
						}
					}
				}

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


}
