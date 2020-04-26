package mask;

import java.sql.SQLException;

public class sadf12 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StoreCrawl s = new StoreCrawl();
		try {
			s.crawlStores();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
