package mask; 

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import mask.model.Sales;
import mask.model.Stores;



/**
 * <pre>
 * mask 
 * MaskTest.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 5. 24.
 * @author : ymg74
 * @version : v1.0
 */
public class MaskTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseUtil db = new DatabaseUtil("jdbc:mysql://dev-swh.ga/minkyu", "root", "swhacademy!");
		ArrayList<Stores> a = db.getStore();
		for(Stores qw : a) {
			System.out.println(qw);
		}
	}

}
