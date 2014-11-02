package dataType;

import java.util.Arrays;

public class StringSplitTest {

	public static void main(String[] args) {
		tstSplit();
	}
	
	private static void tstSplit(){
		String str = "192.168.150.68:11211,192.168.150.62:11212";
		System.out.println("str..."+str);
		String[] strArr = str.split(",");
		System.out.println("strArr..."+Arrays.toString(strArr));
	}
}
