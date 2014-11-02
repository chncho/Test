package dataType;

import java.util.ArrayList;
import java.util.List;

public class FinalTest {

	public static void testFinal(final List lst){
		lst.add("add++");
	}
	
	public static void main(String[] args) {
		final List lst = new ArrayList<String>();
		lst.add("source");
		
		System.out.println(lst);;
		testFinal(lst);
		System.out.println(lst);
	}
}
