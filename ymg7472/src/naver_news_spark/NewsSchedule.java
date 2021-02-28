package naver_news_spark;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeoutException;

import org.apache.http.ParseException;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class NewsSchedule implements org.quartz.Job{
	
	NaverNewsCrawl db = new NaverNewsCrawl("Á¤Ä¡", "20210207", "post");
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap jdm = context.getJobDetail().getJobDataMap();
		QuartzMain qm = (QuartzMain) jdm.get("job");
		
		if(qm.getRunning()) {
			System.out.println("---------AlreadyRunning--------");
			return;
		}
		qm.setRunning(true);
		try {
			db.crawlNews();
		} catch (IOException | TimeoutException | ParseException | SQLException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		qm.setRunning(false);
	}
}