package professional.calendar;

import java.util.Calendar;

public class CalendarTest {

	public static void main(String[] args) {
		test();
	}
	
	public static void test(){
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		System.out.println(calendar.getTime());
		calendar.add(Calendar.DAY_OF_MONTH,1);
		calendar.set(2014, 11, 1);
		calendar.add(calendar.DAY_OF_MONTH, -1);
		System.out.println(calendar.getTime());
	}
}
