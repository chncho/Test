package dataType;
public class CompareNullValue {

	public static void main(String[] args) {
		System.out.println(1 > Integer.parseInt(null));

		/*
		 * Exception in thread "main" java.lang.NumberFormatException: null at
		 * java.lang.Integer.parseInt(Integer.java:415) at
		 * java.lang.Integer.parseInt(Integer.java:497) at
		 * CompareNullValue.main(CompareNullValue.java:5)
		 */
	}
}
