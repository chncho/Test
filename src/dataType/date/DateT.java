package dataType.date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateT {

	public static void main(String[] args) {
		Calendar calendar = new GregorianCalendar(1987, (3 - 1), 24);
		Timestamp ts = new java.sql.Timestamp(calendar.getTimeInMillis());

		Date date = ts;

		System.out.println(date.getYear());
		System.out.println(date.getMonth());
		System.out.println(date.getDay());

		System.out.println("----------------------date.toString();"
				+ date.toString());
		System.out.println(date.getTime());
		System.out.println(">>>>>>>>>>>>>>>>>>>>");
		System.out.println(calendar.get(Calendar.YEAR));
		System.out.println(calendar.get(Calendar.MONTH) + 1);
		System.out.println(calendar.get(Calendar.DATE));

		System.out.println("*******************************");
		Calendar ccc = Calendar.getInstance();
		ccc.setTime(date);
		System.out.println(ccc.get(Calendar.YEAR));
		System.out.println(ccc.get(Calendar.MONTH) + 1);
		System.out.println(ccc.get(Calendar.DATE));

		System.out
				.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		SimpleDateFormat dateformat1 = new SimpleDateFormat(
				"yyyyMMdd");
		
		String a1 = dateformat1.format(new Date());
		System.out.println("时间2:" + a1);
		System.out.println(new Date().getYear() + 1900);

		SimpleDateFormat dateformat2 = new SimpleDateFormat(
				"HHmmss");
		String a2 = dateformat2.format(new Date());
		System.out.println("时间2:" + a2);

		System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		java.sql.Date ddd = new java.sql.Date(new java.util.Date().getTime());
		System.out.println(ddd);
		System.out.println(java.sql.Date.parse("2012-10-10 10:10:10"));
	}
}
