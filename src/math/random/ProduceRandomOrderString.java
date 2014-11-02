package math.random;

import java.util.Random;

public class ProduceRandomOrderString {

	public static void main(String[] args) {
		String arr = 
					"abcdefghijklmnopqrstuvwxyz" +
					 "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuffer sb = new StringBuffer();
		StringBuffer sbRes = new StringBuffer();
		sb.append(arr);
		
		Random rand = new Random(20);

		int lenCache = sb.length();
		for(int i=0;i<lenCache;i++){
			int theIndex = rand.nextInt(sb.length());
			sbRes.append(sb.charAt(theIndex));
			sb.deleteCharAt(theIndex);
			System.out.println(i+"..."+sbRes.toString());
			System.out.println(i+"..."+sb.toString());
		}

		System.out.println("..."+sbRes.toString());
		System.out.println("..."+sb.toString());
	}//XkbOgHAJcezmjtKnMhspSPLFYaZlixNTQdCIRrEBfVovGDyuUqwW
}
