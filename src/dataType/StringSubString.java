package dataType;

public class StringSubString {

	public static void main(String[] args) {
		String str = "1234567890";
		int ran1 = 3;
		int ran2 = 20;

		System.out.println(str.length());
		System.out.println(Math.min(ran1-1+ran2,str.length()));
		System.out.println(str.substring(ran1-1, Math.min(ran1-1+ran2,str.length())));
	}
}
