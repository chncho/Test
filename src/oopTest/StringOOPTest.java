package oopTest;

import java.lang.reflect.Field;
/**
 * String 是final类型的
 * @author 陈超
 * @date Nov 15, 2012 2:22:29 PM
 */
public class StringOOPTest {

	public static void main(String[] args) {
		String str = "aaa";
		System.out.println("str..."+str);
		new StringOOPTest().appendSth(str);
		System.out.println("str..."+str);
		
		String str2 = new String("aaa");
		System.out.println("str2..."+str2);
		new StringOOPTest().appendSth(str2);
		System.out.println("str2..."+str2);
	}
	private void appendSth(String s){//没有修改原来str指向的地址
		System.out.println("s..."+s);
		s+="haha";//让形参s指向新地址
		s+="haha";//让形参s指向新地址
		System.out.println("s..."+s);
	}
}