
public class TheClassPropertyOfClassOrObject {

	public static void main(String[] args) {
		String str = "astring";
		System.out.println("str.getClass()..."+str.getClass());
		System.out.println("String.class..." + String.class);
		System.out.println("str.getClass()==String.class..." + (str.getClass()==String.class));
		
		Integer intt = null;
//		System.out.println(intt.getClass());
		Object ooo = intt;
		ooo="";
		System.out.println("----"+(Integer)ooo);
	}
}
