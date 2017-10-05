package regrex;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestReg {

	public static void main(String[] args) {
		{
			
			System.out.println(Pattern.matches("^((0\\d{2,3}))(\\d{7,8})$","0104444444"));
		}
		String a = "天天向上";
		System.out.println(a.contains("天天"));
		System.out.println(a.matches("^天天*$"));
		
		{
		System.out.println("=====================================================");
		String s1 = "http://sports.sina.com.cn/j/2012-11-13/18216294422.shtml";
		Matcher m = Pattern.compile("https?://.*?(?=/)").matcher(s1);
		if(m.find()) {
			System.out.println(m.group());
		}
		}
		
		{
		System.out.println("=====================================================");
		String s1 = "attachment; filename=\"4msWR51_VM0wNp0uZkgdAZ9BjwR2Gy40-of8sQPYPf5xae2kiRjFU9JbmtVvZBuk.jpg\"";
		Matcher m = Pattern.compile("(filename=\")"+".*?(?=\")").matcher(s1);
		if(m.find()) {
			System.out.println(m.group());
		}
		System.out.println(s1.substring(s1.indexOf("filename=\"")+"filename=\"".length(),s1.length()-1));
		}
	}
}
