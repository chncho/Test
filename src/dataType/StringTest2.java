package dataType;

public class StringTest2 {

	public static void main(String[] args) {
		testSubStringIndexOf();
//		testSubStringIndexOf2();
	}
	
	public static void testSubStringIndexOf(){
		System.out.println(00001==1);
		System.out.println("test substring indexOf");
		
		String str = "a&b&c";
		
		int firstIndex = str.indexOf("&");
		int secondIndex = str.indexOf("&", firstIndex+1);
		String str1 = str.substring(0,firstIndex );
		String str2 = str.substring(firstIndex+1, secondIndex);
		String str3 = str.substring(secondIndex+1, str.length());
			
		System.out.println("str..."+str);
		System.out.println("firstIndex..."+firstIndex);
		System.out.println("secondIndex..."+secondIndex);
		System.out.println(0+","+firstIndex+"|"+str1+"|");
		System.out.println((firstIndex+1)+"," +secondIndex+"|"+str2+"|");
		System.out.println((secondIndex+1)+","+ str.length()+"|"+str3+"|");
	}
	//http://192.168.1.131/w/member/searchSub.action?flag=5&page.curPage=1&member.handset=13333333333&jsessionid=E19EE3EF815D5F8A34021051EFAB3205
	public static void testSubStringIndexOf2(){
		System.out.println("test substring indexOf2");
		
		String str = "http://192.168.1.131/w/member/searchSub.action?flag=5&page.curPage=1&member.handset=13333333333&jsessionid=E19EE3EF815D5F8A34021051EFAB3205";
		
		int firstIndex = str.indexOf("http://");
		int secondIndex = str.indexOf("/member/");
		String str1 = str.substring(firstIndex,secondIndex );
			
		System.out.println("firstIndex..."+firstIndex);
		System.out.println("secondIndex..."+secondIndex);
		System.out.println("|"+str1+"|");
	}
}
