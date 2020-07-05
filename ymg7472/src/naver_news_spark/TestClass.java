package naver_news_spark; 

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

/**
 * <pre>
 * calender_crawl 
 * TestClass.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 5. 24.
 * @author : ymg74
 * @version : v1.0
 */
public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseUtil db = new DatabaseUtil("jdbc:mysql://dev-swh.ga/minkyu", "root", "swhacademy!");
		try {
			db.crawlNews("정치", "20200704", "rabbit");
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
	}

}
