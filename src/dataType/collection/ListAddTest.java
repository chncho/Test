package dataType.collection;

import java.util.ArrayList;
import java.util.List;

public class ListAddTest {

	public static void main(String[] args) {
		List<String> lst = new ArrayList<String>();
		
		lst.add("a");
		lst.add("b");
		lst.add("c");
		lst.add(0, "e");
		
		System.out.println(lst);
	}
}
