package professional.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class SimpleJob implements Job{

	public void execute(JobExecutionContext context) throws JobExecutionException {
	    JobKey jobKey = context.getJobDetail().getKey();
	    //_log.info("SimpleJob says: " + jobKey + " executing at " + new Date());
	    System.out.println("SimpleJob says: " + jobKey + " executing at " + new Date());
	    
	}
}
