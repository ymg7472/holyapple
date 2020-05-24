package hibernate.annotation;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * <pre>
 * kr.co.swh.lecture.database.java.hibernate.annotation
 * HibernateAnnotationUtil.java
 *
 * 설명 : 하이버네이트 어노테이션
 * </pre>
 * 
 * @since : 2017. 10. 26.
 * @author : tobby48
 * @version : v1.0
 */
public class HibernateAnnotationUtil {
	static SessionFactory sessionFactory;
	static ServiceRegistry serviceRegistry;
	
	static{
		try{
			Configuration configuration = new Configuration().configure("hibernate-annotation.cfg.xml");
			
			//	예제1
			configuration.addAnnotatedClass(Cart.class);
			configuration.addAnnotatedClass(Items.class);
			
			//	예제2
			configuration.addAnnotatedClass(Person.class);
			
			
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
