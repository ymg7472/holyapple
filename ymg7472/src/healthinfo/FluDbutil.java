package healthinfo; 

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * <pre>
 * healthinfo 
 * DB_util.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 9. 13.
 * @author : ymg74
 * @version : v1.0
 */
public class FluDbutil {
	SessionFactory sessionFactory = null;
	Session session = null;
	public FluDbutil(SessionFactory sessionFactory, Session session) {
		this.sessionFactory = sessionFactory;
		this.session = session;
	}

	@SuppressWarnings({ "rawtypes", "deprecation", "unchecked" })
	public ArrayList<FluStat> getFluStatByDate(String date, String locate, String diss) {
		int diss1 = 0;
		if(diss.equals("감기")) {
			diss1 = 1;
		}else {
			diss1 = 2;
		}
		ArrayList<FluStat> result = new ArrayList<FluStat>();
		try {
			List<FluStat> list = session.createQuery("select a from FluStat a, SmallCityCode b where a.scc = b.smallCityCode and a.date = " + date + " and a.dis = " + diss1, FluStat.class).getResultList();
			Iterator<FluStat> iterator = list.iterator();
			while(iterator.hasNext()){
				FluStat fs = iterator.next();
				String loc = fs.getScc().getBcc().getLocation();
				if(loc.equals(locate)) {
					result.add(fs);
				}
				
			}
//원래 a.scc.bcc.location 으로 했었는데 안되서
		} catch (HibernateException e) {
			e.printStackTrace(); 
		}

		return result;
		
//		return result;
	}
	public void close() {
		session.close(); 
	}
	public ArrayList<FluStat> getFluStatByloc(String locate) {
		try {
			List<FluStat> list = session.createQuery("select a from FluStat a, SmallCityCode b where a.scc = b.smallCityCode", FluStat.class).getResultList();
			Iterator<FluStat> iterator = list.iterator();
			ArrayList<FluStat> result = new ArrayList<FluStat>();
			while(iterator.hasNext()){
				FluStat fs = iterator.next();
				String loc = fs.getScc().getBcc().getLocation().toString() + fs.getScc().getLocation().replaceAll(" ", "");
				if(loc.equals(locate)) {
					result.add(fs);
				}
			}

		} catch (HibernateException e) {
			e.printStackTrace(); 
		}
		return null;
	}
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil8.getSessionFactory();
		Session session = sessionFactory.openSession();
		FluDbutil fd = new FluDbutil(sessionFactory, session);
//		ArrayList<FluStat> result = fd.getFluStatByloc("서울");
		ArrayList<FluStat> result1 = fd.getFluStatByDate("20140101", "서울", "감기");
		for(FluStat f : result1) {
			System.out.println(f);
		}
	}


}
