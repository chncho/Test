package dataType.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAndStringArray {

	public static void main(String[] args) {
		List a = new ArrayList();
		a.add("aa");
		a.add("ff");
		String[] s = new String[a.size()];
		a.toArray(s);
		System.out.println(Arrays.toString(s));
		
	}
}
