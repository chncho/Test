/* 
 * All content copyright Terracotta, Inc., unless otherwise indicated. All rights reserved. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not 
 * use this file except in compliance with the License. You may obtain a copy 
 * of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT 
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 * License for the specific language governing permissions and limitations 
 * under the License.
 * 
 */
 
package professional.quartz.examples.example3;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.TriggerKey.triggerKey;

import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This Example will demonstrate all of the basics of scheduling capabilities of Quartz using Cron Triggers.
 * 
 * @author Bill Kratzer
 */
public class UpdateTriggerExample {
	SchedulerFactory sf;
	Scheduler sched;

  public void run() throws Exception {
    Logger log = LoggerFactory.getLogger(UpdateTriggerExample.class);

    log.info("------- Initializing -------------------");

    // First we must get a reference to a scheduler
    sf = new StdSchedulerFactory();
    sched = sf.getScheduler();
    log.info("------- Initialization Complete --------");

    log.info("------- Scheduling Jobs ----------------");

    // jobs can be scheduled before sched.start() has been called

    // job 1 will run every 1 seconds
    JobDetail job = newJob(SimpleJob.class).withIdentity("job1", "group1").build();

    CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/5 * * * * ?"))
        .build();

    Date ft = sched.scheduleJob(job, trigger);
    log.info(job.getKey() + " has been scheduled to run at: " + ft + " and repeat based on expression: "
             + trigger.getCronExpression());


    log.info("------- Starting Scheduler ----------------");

    // All of the jobs have been added to the scheduler, but none of the
    // jobs
    // will run until the scheduler has been started
    sched.start();

    log.info("------- Started Scheduler -----------------");

    log.info("------- Waiting ... ------------");
    try {
      // wait five minutes to show jobs
      Thread.sleep(30L * 1000L);
      updateTrigger(10);
      log.info("------- trigger updated ... ------------");
      Thread.sleep(40L * 1000L);
      // executing...
    } catch (Exception e) {
      log.error("",e);
    }

    log.info("------- Shutting Down ---------------------");

    sched.shutdown(true);

    log.info("------- Shutdown Complete -----------------");

    SchedulerMetaData metaData = sched.getMetaData();
    log.info("Executed " + metaData.getNumberOfJobsExecuted() + " jobs.");

  }
  
  public void updateTrigger(int seconds) throws SchedulerException{

	// retrieve the trigger
	  Trigger oldTrigger = sched.getTrigger(triggerKey("trigger1", "group1"));

	  
	  CronTrigger trigger = newTrigger().withIdentity("trigger1", "group1").withSchedule(cronSchedule("0/1 * * * * ?"))
			  	.startNow()
		        .build();
	  
	  // obtain a builder that would produce the trigger
	  TriggerBuilder tb = oldTrigger.getTriggerBuilder();

	  // update the schedule associated with the builder, and build the new trigger
	  // (other builder methods could be called, to change the trigger in any desired way)
	  Trigger newTrigger =  tb.withSchedule(cronSchedule("0/"+seconds+" * * * * ?")).build();

	  sched.rescheduleJob(oldTrigger.getKey(), newTrigger);
  }
  public void replaceTrigger(int seconds){
//	// Define a new Trigger 
//	  Trigger trigger = newTrigger()
//	      .withIdentity("newTrigger", "group1")
//	      .withSchedule(cronSchedule("0/"+seconds+" * * * * ?"))
//	      .startNow()
//	      .build();
//
//	  // tell the scheduler to remove the old trigger with the given key, and put the new one in its place
//	  sched.rescheduleJob(triggerKey("oldTrigger", "group1"), trigger);
  }

  public static void main(String[] args) throws Exception {

    UpdateTriggerExample example = new UpdateTriggerExample();
    example.run();
  }

}
