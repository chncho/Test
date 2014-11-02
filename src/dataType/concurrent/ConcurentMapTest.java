package dataType.concurrent;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurentMapTest {

	public static void main(String[] args) {
		ConcurrentMap cmap = new ConcurrentHashMap();
		cmap.put("a", "a");
		cmap.put("b", "b");
		
		System.out.println(cmap.get("a"));
		
		System.out.println(cmap.get("b"));
		
	}
}
