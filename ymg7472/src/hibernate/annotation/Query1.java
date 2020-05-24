package hibernate.annotation;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * <pre>
 * kr.co.swh.lecture.database.java.hibernate.annotation
 * Query1.java
 *
 * 설명 : 하이버네이트 어노테이션 예제1 테스트
 * </pre>
 * 
 * @since : 2017. 10. 26.
 * @author : tobby48
 * @version : v1.0
 */
public class Query1 {
	public static void main( String[] args ){
        SessionFactory sessionFactory = HibernateAnnotationUtil.getSessionFactory();

        Cart cart = new Cart();
        Items item1 = new Items(cart);
        Items item2 = new Items(cart);
        Set<Items> itemsSet = new HashSet<Items>();
        itemsSet.add(item1);
        itemsSet.add(item2);
        cart.setItems(itemsSet);
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(cart);
        session.save(item1);
        session.save(item2);
        session.getTransaction().commit();
        System.out.println("Insert completed");

        System.out.println("Cart ID=" + cart.getId());
        System.out.println("item1 ID=" + item1.getId() + ", Foreign Key Cart ID=" + item1.getCart().getId());
        System.out.println("item2 ID=" + item2.getId() + ", Foreign Key Cart ID=" + item1.getCart().getId());
        
        session.close();
        sessionFactory.close();
    }
}