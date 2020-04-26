package quartz;

import java.sql.SQLException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import mask.SalesCrawl;
import mask.StoreCrawl;


/**
 * <pre>
 * kr.co.swh.lecture.opensource.quartz 
 * SchedulerProcessor.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2018. 11. 30.
 * @author : tobby48
 * @version : v1.0
 */
public class SchedulerProcessor implements org.quartz.Job{
	StoreCrawl st = new StoreCrawl();
	SalesCrawl ss = new SalesCrawl();
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			st.crawlStores();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			ss.crawlSales();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}