package dataType.collection;

import java.util.HashMap;
import java.util.Map;

public class MapGetTest {

	public static void main(String[] args) {

		Map<String,Integer> map = new HashMap<String,Integer>();
	
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		
		System.out.println(map.get("d"));
	}
}
