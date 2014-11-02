import java.io.UnsupportedEncodingException;


public class URLDecode {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "好了了尜砂";
		String str2 = java.net.URLEncoder.encode(str, "utf-8");
		String str3 = java.net.URLDecoder.decode(str,"utf-8");
		System.out.println(str2);
		System.out.println(str3);
	}
}
