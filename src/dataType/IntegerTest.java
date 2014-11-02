package dataType;

public class IntegerTest {

	public static void main(String[] args) {
//		System.out.println("Integer.parseInt(\"\")..."+Integer.parseInt(""));
		
		
		/*	System.out.println("new Integer(\"aa\")"+new Integer("aa"));
		 * 
		 *Exception in thread "main" java.lang.NumberFormatException: For input string: ""
			at java.lang.NumberFormatException.forInputString(NumberFormatException.java:48)
			at java.lang.Integer.parseInt(Integer.java:470)
			at java.lang.Integer.parseInt(Integer.java:499)
			at dataType.IntegerTest.main(IntegerTest.java:6)

		 */
		
		/*	System.out.println("new Integer(\"aa\")"+Integer.parseInt("aa"));
		 * 
		 *Exception in thread "main" java.lang.NumberFormatException: For input string: ""
			at java.lang.NumberFormatException.forInputString(NumberFormatException.java:48)
			at java.lang.Integer.parseInt(Integer.java:470)
			at java.lang.Integer.parseInt(Integer.java:499)
			at dataType.IntegerTest.main(IntegerTest.java:6)
		 */
		
//		testNewInteger();
//		testRange();
		testGetInt();
	}
	
	public static void testGetInt(){
		int i = 23;
		System.out.println(testGetInt(i,1));
		System.out.println(testGetInt(i,10));
	}
	/**
	 * 
	 * 功能描述：获得一个数字的第几位数是多少
	 *
	 * @param value
	 * @param dijiwei 个位1,十位10 ...
	 * @return
	 *
	 * @author 陈超
	 *
	 * @since 2014-4-8
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static int testGetInt(int value,int dijiwei){
		return value%(dijiwei*10)/(dijiwei);
	}
	/**
	 * 
	 * 功能描述：测试数值范围
	 *
	 *
	 * @author 陈超
	 *
	 * @since 2014-4-8
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void testRange(){
		System.out.println("Integer.max,Integer.min..."+Integer.MAX_VALUE+","+Integer.MIN_VALUE);
	}
	public static void testNewInteger(){
		String str = "33.0";
		System.out.println(Integer.parseInt(str));
		System.out.println(new Integer(str));
	}
}
