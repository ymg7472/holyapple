package quartz;

import java.sql.SQLException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import mask.DatabaseUtil;


public class SchedulerProcessor implements org.quartz.Job{
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("hi");
		
		
	}
}