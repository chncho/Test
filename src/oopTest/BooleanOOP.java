package oopTest;

public class BooleanOOP {

	public static void main(String[] args) {
		boolean a = false;
		System.out.println("a..."+a);
		changBoolean(a);
		System.out.println("a..."+a);
	}
	
	public static void changBoolean(boolean boo){
		System.out.println("boo..."+boo);
		boo = true;
		System.out.println("boo..."+boo);
	}
}
