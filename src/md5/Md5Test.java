package md5;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class Md5Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "6456C7FAC445F3C1C3E7F4405159D80E";
		MD5 md5=new MD5();
		System.out.println(md5.getMD5ofStr(md5.getMD5ofStr(str)));

		System.out.println("............................................");
		String str2 = "dxt123ahaha456";
		String str2_md5_1=md5.getMD5ofStr(str2);
		String str2_md5_2=md5.getMD5ofStr(md5.getMD5ofStr(str2));
		String str2_md5_3=md5.getMD5ofStr(md5.getMD5ofStr(md5.getMD5ofStr(str2)));
		System.out.println("str2_md5_1..."+str2_md5_1);
		System.out.println("str2_md5_2..."+str2_md5_2);
		System.out.println("str2_md5_3..."+str2_md5_3);

		System.out.println(md5.getMD5ofStr(md5.getMD5ofStr(".3269")));
		System.out.println(md5.getMD5ofStr(md5.getMD5ofStr("3269")));
	}
	
}
