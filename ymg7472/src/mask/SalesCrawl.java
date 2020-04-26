package mask;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.google.gson.Gson;

import mask.model.ResponseSales;
import mask.model.Sales;

public class SalesCrawl {
	public void crawlSales() throws SQLException, ClassNotFoundException{
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
		
	
	}


}
