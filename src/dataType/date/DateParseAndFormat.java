package dataType.date;

import java.text.ParseException;
import java.util.Date;

/**
 * 时间解析与格式化
 * @author 陈超
 * @date Dec 29, 2012 3:08:12 PM
 */
public class DateParseAndFormat {

	public static void main(String[] args) throws ParseException {
		String dateStr = "2012-12-29T12:01:01";
		java.text.DateFormat format = new java.text.SimpleDateFormat("yyyy-dd-MM'T'HH:mm:ss");
		Date date = format.parse(dateStr);
		System.out.println(date);
	}
}
