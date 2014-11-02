package characterEncode;

import java.io.UnsupportedEncodingException;

public class CharcterConvert_gb2312 {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println("............................................");
	//	String str_utf8 = new String("жа".getBytes("UTF-8"),"UTF-8");
		String str_utf8 = new String("жа");
		String str_iso8859_1 = new String(str_utf8.getBytes("UTF-8"),"ISO-8859-1");
		String str_gbk = new String( str_iso8859_1.getBytes("ISO-8859-1"),"GBK");
		
		System.out.println("{ str_utf8: "+str_utf8 + "; str_iso8859_1: "+str_iso8859_1+"; str_gbk: "+str_gbk+"; }");
	}
}
