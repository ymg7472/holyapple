package calender_crawl;
import static spark.Spark.*;
import java.util.List;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
public class Spark1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("FROM CrawlNews WHERE subject=:subject");
		query.setParameter("subject", "Á¤Ä¡");
		ArrayList resultList = (ArrayList) query.getResultList();
		
	}

}


