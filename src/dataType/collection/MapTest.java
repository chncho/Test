package dataType.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		testPut();
	}
	
	public static void testPut(){
		Map<String,Object> map = new HashMap<String,Object>();
		String str=null;
		map.put("a", "a");
		map.put("b", null);
		map.put("c", str);
		map.put("a", "e");
		System.out.println(map);
	}
}
