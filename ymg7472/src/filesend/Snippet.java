package filesend; 

import org.apache.log4j.Logger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 * <pre>
 * filesend 
 * Snippet.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 10. 4.
 * @author : ymg74
 * @version : v1.0
 */
public class Snippet {
	
	private Logger log	= Logger.getLogger(this.getClass()); 

	
	public static void main(String[] args) {
		SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		Calendar cal = Calendar.getInstance();
		String today = null;
		today = formatter.format(cal.getTime());
		Timestamp ts = Timestamp.valueOf(today);
	
	}
	
}

