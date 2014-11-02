package tableDrivenTest.directAccessTables;

import java.util.Arrays;
import java.util.List;

public class DaysInMonth {

	public static int getDaysInMonth1(int month) {
		int days = 0;
		switch (month) {
		case 1:
			days=31;
			break;
		case 2:
			days=28;
			break;
		case 3:
			days=31;
			break;
		case 4:
			days=30;
			break;
		case 5:
			days=31;
			break;
		case 6:
			days=30;
			break;
		case 7:
			days=31;
			break;
		case 8:
			days=31;
			break;
		case 9:
			days=30;
			break;
		case 10:
			days=31;
			break;
		case 11:
			days=30;
			break;
		case 12:
			days=31;
			break;
		default:
			break;
		}
		return days;
	}
	public static int getDaysInMonth2(int month) {
		List<Integer> month31 = Arrays.asList(new Integer[]{1,3,5,7,8,10,12});
		List<Integer> month30 = Arrays.asList(new Integer[]{4,6,9,11});
		int days = 0;String[] sss = {};
		if(month31.contains(month)){
			days = 31;
		}else if(month30.contains(month)){
			days = 30;
		}else{
			days = 28;
		}
		return days;
	}
	
	public static int getDaysInMonth3(int month) {
		int[] daysInM = {31,28,31,30,31,30,31,31,30,31,30,31};
		return daysInM[month-1];
	}
	
	/*private int leapYearIndex(){
		return 
	}*/
	
	
	public static void main(String[] args) {
		System.out.println(DaysInMonth.getDaysInMonth1(3));
		System.out.println(DaysInMonth.getDaysInMonth2(3));
		System.out.println(DaysInMonth.getDaysInMonth3(3));
	}
}
