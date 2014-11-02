
public class ClassTypeTest {

	public static void main(String[] args) {
		Object strObj = "strstrstr";
		Object intObj = 12345678;
		
		System.out.println(strObj.getClass());
		System.out.println(intObj.getClass());
		
		System.out.println(strObj.getClass().getName());
		System.out.println(intObj.getClass().getSimpleName());
	}
}
