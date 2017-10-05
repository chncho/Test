package special;

import java.util.Calendar;

public class LoopTest {

	public static void main(String[] args) {
		test();
	}
	
	public static void test(){
		int curYear = Calendar.getInstance().get(Calendar.YEAR);
		for(int i=curYear-18; i>=curYear-75; i--){
			System.out.println(i);
		}
	}
}
