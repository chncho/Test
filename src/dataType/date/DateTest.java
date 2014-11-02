package dataType.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		DateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		System.out.println(format.format(new Date()));
	}
}
