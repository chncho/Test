package dataType;

import java.util.Arrays;

public class StringTest {

	public static void main(String[] args) {

//		testSubStr();
//
//		testSubStr2();
		
		testReplaceAllReg();
	}

	public static void test001() {
		StringBuffer strb = new StringBuffer();
		strb.append("aaa&bbb&ccc&ddd");
		System.out.println(strb.indexOf("&"));
		strb.replace(strb.indexOf("&"), strb.indexOf("&") + 1, "lll");
		System.out.println(strb.toString());
		System.out.println(strb.indexOf("&"));
		strb.replace(strb.indexOf("&"), strb.indexOf("&") + 1, "lll");
		System.out.println(strb.toString());
		System.out.println(strb.indexOf("&"));
		strb.replace(strb.indexOf("&"), strb.indexOf("&") + 1, "lll");
		System.out.println(strb.indexOf("&"));
		System.out.println(strb.toString());

		System.out.println("====================================");
		String str = "!@BB@CCC@DDD@E@FF@GGG@HHHH@f";
		int index1 = str.indexOf('@') + 1;
		int index2 = str.lastIndexOf('@');
		String str2 = str.subSequence(index1, index2).toString();
		System.out.println(str2);

		System.out.println("=====================================");

		String strContent = "123,456,789,000";
		System.out.println(strContent.contains("123"));

		System.out.println("========================================");
		String[] strs = new String[] { "saleorback", "productid", "imei",
				"saledate", "actualsalesprice", "custname", "marketmobile" };
		System.out.println(Arrays.toString(strs));
		System.out.println(Arrays.toString(strs).contains("productid"));
		System.out.println("========================================");
		String checked = "tr7890opklmnj^^^";
		System.out.println(checked);
		checked = checked.replace("^", "");
		System.out.println(checked);

		
		String ssssss = "中中中中(HHHHH)";
		// ssssss.

		System.out.println("========================================");
		String ssss = "abcdefg";
		System.out.println(Arrays.toString(ssss.split("")));

		// /////////////////
		System.out.println("====================================");
		String abc = "http://123.196.123.76/e_business/UploadImages/2012-07-19/1342686250443.jpg http://123.196.123.76/e_business/UploadImages/2012-07-19/1342672286864.jpg";
		System.out.println(abc.replaceAll("http://123.196.123.76",
				"http://www.dixintong.com"));

		// //////////////
		System.out.println("====================================");
		String a = "abc@@@csdfsf@@@jdfosjfij\n";
		String[] as = a.split("@@@");
		System.out.println(as.length + " " + Arrays.toString(as));
		a = a + "@@@";
		as = a.split("@@@");
		System.out.println(as.length + " " + Arrays.toString(as));

	}

	/**
	 * 正则表达式替换
	 */
	public static void testReplaceAllReg(){
		System.out.println("========================================");
		String strss = "哈哈(呱呱)哈哈(呱呱)";
		System.out.println(strss.replaceAll("([^()]+)", "()"));

		String strss2 = "aaa(bb)aaa(bb)";
		System.out.println(strss.replaceAll("(.+)", "()"));

		String strsss = "刘翔(0.0)-姚明(dd.0)";
		System.out.println(strsss.replaceAll("\\(.*?\\)", "\\(\\)"));

		String strsss2 = "刘翔(0.0)-姚明(dd.0)";
		System.out.println(strsss2.replaceAll("\\(.*?\\)", "\\(\\)"));

		System.out.println("========================================");
		System.out.println("========================================");
		String str_ = "aaaa【bbbbbbbbbbb】aaaaaaaaaaa";
		System.out.println(str_.replaceFirst("\\【.*?\\】", "HAHA"));
		System.out.println("========================================");
	}
	
	public static void testSubStr() {
		System.out.println("============================================");
		System.out.println("...into testSubStr...");

		String str = "手机号：13333333333";
		System.out.println("str=" + str);
		System.out.println("str.substring(4, 15)=" + str.substring(4, 15));

		System.out.println("...out testSubStr...");
		System.out.println("============================================");
	}

	public static void testSubStr2() {
		System.out.println("============================================");
		System.out.println("...into testSubStr2...");

		String str = "/callzgx.do?zgx=zhuguixinag&xujiang=xj&chenchao=chenchao&signature=md5Str";
		System.out.println("str=" + str);
		System.out.println("str.substring(0,str.lastIndexOf(\"&\"))="
				+ str.substring(0, str.lastIndexOf("&")));
		System.out
				.println("str.substring(str.lastIndexOf(\"=\"),str.length())="
						+ str.substring(str.lastIndexOf("="), str.length()));

		System.out.println("...out testSubStr2...");
		System.out.println("============================================");

	}
}
