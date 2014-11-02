package timerAndThread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MyTimer {
	 public static void main(String[] args) throws InterruptedException
	 {
	  Timer timer=new Timer();
	  TimerTask TaskOne=new Task("NO."+1);
	  //100毫秒后执行TaskOne
	  timer.schedule(TaskOne, 100);
	  TimerTask TaskTwo=new Task("NO."+2);
	  //200毫秒后执行TaskTwo，每隔500毫秒执行一次
	  timer.schedule(TaskTwo,200,500);
	  TimerTask TaskThree=new Task("NO."+3);
	  //在指定时间执行TaskThree
	  Date date=new Date(System.currentTimeMillis()+1000);
	  timer.schedule(TaskThree, date);
//	  try
//	  {
	   //等待5秒
	   Thread.sleep(5000);
//	  }catch(InterruptedException e)
//	  {e.printStackTrace();}
	  //终止定时器，并取消定时器中的任务
	  timer.cancel();
	  System.out.println("timer canceled!");
	 }
	}

	 
	class Task extends TimerTask
	{
	 private String ID;
	 public Task(String ID)
	 {
	  this.ID=ID;
	 }
	 public void run()
	 {
	  long time=System.currentTimeMillis();
	  System.out.println("run Task-"+this.ID+"at time:"+time);
	 }
	}
