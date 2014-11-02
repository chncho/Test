package professional.quartz;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerExample {
	
	public static void main(String[] args) throws SchedulerException, InterruptedException {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();

		//theTestJobsFromAPI(sched);
		JobDetail job = JobBuilder.newJob(SimpleJob.class)
			    .withIdentity("job1", "group1")
			    .build();

		CronTrigger trigger = TriggerBuilder.newTrigger()
		    .withIdentity("trigger1", "group1")
		    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 0 * * ?"))
		    .build();

		sched.scheduleJob(job, trigger);
		
		
		//The scheduler is then started (it also would have been fine to start it before scheduling the jobs).

		sched.start();
		//To let the program have an opportunity to run the job, we then sleep for five minutes (300 seconds). The scheduler is running in the background and should fire off several jobs during that time.

		//Note: Because many of the jobs have hourly and daily restrictions on them, not all of the jobs will run in this example. For example: Job #6 only runs on Weekdays while Job #7 only runs on Weekends.

		Thread.sleep(300L * 1000L);
		//Finally, we will gracefully shutdown the scheduler:

		sched.shutdown(true);
	}
	
	public static void theTestJobsFromAPI(Scheduler sched) throws SchedulerException{

		//Job #1 is scheduled to run every 20 seconds

		JobDetail job = JobBuilder.newJob(SimpleJob.class)
		    .withIdentity("job1", "group1")
		    .build();

		CronTrigger trigger = TriggerBuilder.newTrigger()
		    .withIdentity("trigger1", "group1")
		    .withSchedule(CronScheduleBuilder.cronSchedule("0/20 * * * * ?"))
		    .build();

		sched.scheduleJob(job, trigger);
		//Job #2 is scheduled to run every other minute, starting at 15 seconds past the minute.

		job = JobBuilder.newJob(SimpleJob.class)
		    .withIdentity("job2", "group1")
		    .build();

		trigger = TriggerBuilder.newTrigger()
		    .withIdentity("trigger2", "group1")
		    .withSchedule(CronScheduleBuilder.cronSchedule("15 0/2 * * * ?"))
		    .build();

		sched.scheduleJob(job, trigger);
		//Job #3 is scheduled to every other minute, between 8am and 5pm (17 o'clock).

		job = JobBuilder.newJob(SimpleJob.class)
		    .withIdentity("job3", "group1")
		    .build();

		trigger = TriggerBuilder.newTrigger()
		    .withIdentity("trigger3", "group1")
		    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 8-17 * * ?"))
		    .build();

		sched.scheduleJob(job, trigger);
		//Job #4 is scheduled to run every three minutes but only between 5pm and 11pm

		job = JobBuilder.newJob(SimpleJob.class)
		    .withIdentity("job4", "group1")
		    .build();

		trigger = TriggerBuilder.newTrigger()
		    .withIdentity("trigger4", "group1")
		    .withSchedule(CronScheduleBuilder.cronSchedule("0 0/3 17-23 * * ?"))
		    .build();

		sched.scheduleJob(job, trigger);
		//Job #5 is scheduled to run at 10am on the 1st and 15th days of the month

		job = JobBuilder.newJob(SimpleJob.class)
		    .withIdentity("job5", "group1")
		    .build();

		trigger = TriggerBuilder.newTrigger()
		    .withIdentity("trigger5", "group1")
		    .withSchedule(CronScheduleBuilder.cronSchedule("0 0 10am 1,15 * ?"))
		    .build();

		sched.scheduleJob(job, trigger);
		//Job #6 is scheduled to run every 30 seconds on Weekdays (Monday through Friday)

		job = JobBuilder.newJob(SimpleJob.class)
		    .withIdentity("job6", "group1")
		    .build();

		trigger = TriggerBuilder.newTrigger()
		    .withIdentity("trigger6", "group1")
		    .withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * MON-FRI"))
		    .build();

		sched.scheduleJob(job, trigger);
		//Job #7 is scheduled to run every 30 seconds on Weekends (Saturday and Sunday)

		job = JobBuilder.newJob(SimpleJob.class)
		    .withIdentity("job7", "group1")
		    .build();

		trigger = TriggerBuilder.newTrigger()
		    .withIdentity("trigger7", "group1")
		    .withSchedule(CronScheduleBuilder.cronSchedule("0,30 * * ? * SAT,SUN"))
		    .build();

		sched.scheduleJob(job, trigger);
	}
}
