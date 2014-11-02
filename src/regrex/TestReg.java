package regrex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestReg {

	public static void main(String[] args) {
		String a = "天天向上";
		System.out.println(a.contains("天天"));
		System.out.println(a.matches("^天天*$"));
		
		System.out.println("=====================================================");
		String s1 = "http://sports.sina.com.cn/j/2012-11-13/18216294422.shtml";
		Matcher m = Pattern.compile("https?://.*?(?=/)").matcher(s1);
		if(m.find()) {
			System.out.println(m.group());
		}
	}
}
