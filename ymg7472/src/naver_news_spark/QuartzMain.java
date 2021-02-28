package naver_news_spark;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;


/**
 * <pre>
 * quartz 
 * QuartzMain.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 5. 31.
 * @author : ymg74
 * @version : v1.0
 */
public class QuartzMain {
	private StdSchedulerFactory schedulerFactory;
	private org.quartz.Scheduler scheduler;
	
	public QuartzMain(){
		schedulerFactory = new org.quartz.impl.StdSchedulerFactory();
		try {
			scheduler = schedulerFactory.getScheduler();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean initialise(String cronExpr, Class<NewsSchedule> class1){
		String id = UUID.randomUUID().toString();
		String cron = String.valueOf(cronExpr);
		
		JobDetail newJob = JobBuilder.newJob(class1).withIdentity(id).build();
		JobDataMap jdm = newJob.getJobDataMap();
		jdm.put("job", this);
		Trigger trigger = null;
		try {
			trigger = TriggerBuilder.newTrigger().withIdentity("trigger_" + id)
					.withSchedule(CronScheduleBuilder.cronSchedule(cron)).forJob(id)
					.build();
			if (trigger != null) {
				scheduler.scheduleJob(newJob, trigger);
			}
		} catch (Exception e) {
		}
		return true;
	}
	
	public void start(){
		try {
			scheduler.start();
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop(){
		try {
			if(schedulerFactory != null && scheduler.isStarted()){
				scheduler.shutdown();
			}
		} catch (SchedulerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isRunning = false;
	public void setRunning(boolean isrunning) {
		this.isRunning = isrunning;
	}
	public boolean getRunning() {
		return isRunning;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuartzMain quartz = new QuartzMain();
		long time = System.currentTimeMillis(); 

		SimpleDateFormat dayTime = new SimpleDateFormat("hh");

		String str = dayTime.format(new Date(time));
		
//		0/1 * * * * ?         매 1초 간격
//		0 0/1 * * * ?         매 1분 간격
//		0 0 0/1 * * ?         매 1시간 간격
//		0 0 0 * * ?            매일 0시 마다
//		0 0 0 1 * ?            매월 1일 마다
//		0 0 0 1,10,20 * ?    매월 1일, 10일, 20일 마다
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH");
		LocalDateTime now = LocalDateTime.now();
		
		if(quartz.initialise("0/5 * * * * ? ", NewsSchedule.class)){
			quartz.start();
		}
	}
}
