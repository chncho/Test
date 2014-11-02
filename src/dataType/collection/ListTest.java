package dataType.collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ListTest {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		{Iterator<Integer> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}}
		
		list.remove(list.size()-1);
		
		System.out.println("aaaaaa");
		{Iterator<Integer> it = list.iterator();
		while(it.hasNext()){
			System.out.println(it.next());
		}}
	}
	public static void a(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);

		
		System.out.println(list+" ...size... "+list.size());
		
		Iterator<Integer> it = list.iterator();
		/*if(it.hasNext()){
			it.next();
			it.remove();
		}*/
		if(it.hasNext()){
			list.remove(it.next());
		}
		/*
[1, 2, 3, 4, 5] ...size... 5
Exception in thread "main" java.util.ConcurrentModificationException
[2, 3, 4, 5] ...size... 4
	at java.util.AbstractList$Itr.checkForComodification(AbstractList.java:372)
	at java.util.AbstractList$Itr.next(AbstractList.java:343)
	at dataType.collection.ListTest.main(ListTest.java:29)
*/
		System.out.println(list +" ...size... " +list.size());
		while(it.hasNext()){
			System.out.print(">" + it.next());
		}
		
		System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::");
		System.out.println(list);
		System.out.println(list.get(0));
		
	}
}
