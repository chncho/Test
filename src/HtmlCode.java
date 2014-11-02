import org.apache.commons.lang.StringEscapeUtils;


public class HtmlCode {

	public static void main(String[] args) {
		System.out.println(StringEscapeUtils.escapeHtml("好"));
		System.out.println(StringEscapeUtils.unescapeHtml("&#22909;"));
	}
}
