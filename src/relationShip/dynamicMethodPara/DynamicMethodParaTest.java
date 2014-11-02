package relationShip.dynamicMethodPara;

public class DynamicMethodParaTest {

	static String method1(String str1,Object...objs){
		if(objs.length>0){
			str1+=objs[0];
		}
		return str1;
	}
	
	public static void main(String[] args) {
		System.out.println(method1("aaa","bbb"));
	}
}
