package dataType.date;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateCompare {

	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		
		Calendar calendar1 = new GregorianCalendar(1987,(6-1),24);
		Calendar calendar2 = new GregorianCalendar(1987,(7-1),24);
		
		long substract = calendar2.getTimeInMillis()-calendar1.getTimeInMillis();
		System.out.println(substract + " 毫秒");
		System.out.println(substract/(1000*60*60*24) + " 天");
	}
}
