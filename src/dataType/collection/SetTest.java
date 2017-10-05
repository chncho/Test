package dataType.collection;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		testAdd_AddAll();
	}
	
	
	
	
	public static void testAdd_AddAll(){
		Set<String> set = new HashSet<String>();

		set.add("abcdef");
		set.add("aaaaaa");
		set.add("bbbbbb");
		set.add("aaaaaa");
		
		Set<String> set2 = new HashSet<String>();

		set2.add("abcdef");
		set2.add("aaaaaa");
		set2.add("bbbbbb");
		set2.add("aaaaaa");
		
		set.addAll(set2);
		
		System.out.println(set);
		
	}
}
