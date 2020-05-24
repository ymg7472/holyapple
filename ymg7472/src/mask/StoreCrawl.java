package mask;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.google.gson.Gson;
import mask.model.ResponseStores;
import mask.model.Stores;

public class StoreCrawl {
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

}
