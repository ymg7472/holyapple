package quartz;

import java.sql.SQLException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import mask.DatabaseUtil;


public class SchedulerProcessor implements org.quartz.Job{
	DatabaseUtil db = new DatabaseUtil("jdbc:mysql://dev-swh.ga/minkyu", "root", "swhacademy!");
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			db.crawlSales();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			db.crawlStores();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}