package naver_news_spark; 

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;

/**
 * <pre>
 * calender_crawl 
 * TestClass.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 5. 24.
 * @author : ymg74
 * @version : v1.0
 */
public class TestClass {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		NaverNewsCrawl db = new NaverNewsCrawl("it", "20210207", "post");
		try {
			db.crawlNews();
		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		int i = db.getTotalPage("105", "230");
//		int j = db.getPageCount();
//		System.out.println(i + " and " + j);
//		System.out.println(db.getDiff("105", "230")+"Ήψ");
		

	}

}
