package relationShip.paraTransInMethod;


//??????????????????????????
public class StringTransInMethod {
	static String sourceStr = null;

	public static void main(String[] args) {
		
		sourceStr="ssssss";
		
		System.out.println("main sourceStr......"+sourceStr);
		changeValue(sourceStr);
		System.out.println("main sourceStr......"+sourceStr);
	}
	
	
	public static void changeValue(String str){
		str += "cccccccccccccc";
		System.out.println("changeValue(String str) str  "+str);
		System.out.println("changeValue(String str) sourceStr "+sourceStr);
	}
}
