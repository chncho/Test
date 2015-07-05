package professional.attack;

import java.util.Arrays;

/**
 * 慢富外汇攻击代码分析
 * 
 *
 * @author 陈超
 *
 * @version $Revision$
 *
 * @since 2015-7-3
 */
public class MdffxWebAttakTest {

	public static void main(String[] args) {
		test1();
		//eval(eval("request")(.1105.)) ;
	}
	
	public static void test1(){
		char[] charArr = new char[]{114, 101, 113, 117,101, 115, 116};
		char[] charArr2 = new char[]{1105};
		
				
		System.out.println(Arrays.toString(charArr));
		System.out.println(Arrays.toString(charArr2));
		
		//eval("request");
	}
}
