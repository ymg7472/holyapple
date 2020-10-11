package mask;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import healthinfo.BigCityCode;
import healthinfo.FluStat;
import healthinfo.SmallCityCode;
import mask.model.Sales;
import mask.model.Stores;


public class HibernateUtil7 {
	static SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry;
	
	static{
		try{
			Configuration configuration = new Configuration().configure("hibernate.cfg3.xml");
			
			//	¿¹Á¦1
//			configuration.addAnnotatedClass(Sales.class);
//			configuration.addAnnotatedClass(Stores.class);
			configuration.addAnnotatedClass(SmallCityCode.class);
			configuration.addAnnotatedClass(BigCityCode.class);
			configuration.addAnnotatedClass(FluStat.class);
			serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}catch(HibernateException e){
			e.printStackTrace();
		}
	}
	
	public static SessionFactory getSessionFactory(){ 
		return sessionFactory;
	}
}
