package annotation1;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * <pre>
 * kr.co.swh.lecture.database.java.hibernate.annotation
 * Query2.java
 *
 * 설명 : 하이버네이트 어노테이션 예제2 테스트
 * </pre>
 * 
 * @since : 2017. 10. 26.
 * @author : tobby48
 * @version : v1.0
 */
public class Query2 {

	private static SessionFactory sessionFactory; 
	
	public Integer addPerson(String fname, String lname, int salary){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Integer personID = null;
		try {
			tx = session.beginTransaction();
			Person person = new Person();
			person.setFirstName(fname);
			person.setLastName(lname);
			person.setSalary(salary);
			personID = (Integer) session.save(person); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
		return personID;
	}

	public void listPerson( ){
		Session session = sessionFactory.openSession();
		try {
			Iterator<Person> iterator = session.createQuery("FROM Person", Person.class).list().iterator(); 
			while(iterator.hasNext()){
				Person person = (Person) iterator.next(); 
				System.out.print("First Name: " + person.getFirstName()); 
				System.out.print("Last Name: " + person.getLastName()); 
				System.out.println("Salary: " + person.getSalary()); 
			}
		} catch (HibernateException e) {
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	public void updatePerson(Integer personId, int salary){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Person person = (Person)session.get(Person.class, personId); 
			person.setSalary(salary);
			session.update(person); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	public void deletePerson(Integer personId){
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Person person = (Person)session.get(Person.class, personId); 
			session.delete(person); 
			tx.commit();
		} catch (HibernateException e) {
			if (tx!=null) tx.rollback();
			e.printStackTrace(); 
		} finally {
			session.close(); 
		}
	}

	public static void main( String[] args ){
		sessionFactory = HibernateAnnotationUtil.getSessionFactory();

		Query2 query = new Query2();
		Integer value1 = query.addPerson("유키", "이", 1000);
		Integer value2 = query.addPerson("코야", "허", 5000);
		query.addPerson("SWH", "Academy", 10000);
		query.listPerson();
		
		query.updatePerson(value1, 5000);
		query.deletePerson(value2);
		query.listPerson();
		
		sessionFactory.close();
	}
}
