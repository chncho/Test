package professional.desensitization;

/**
 * 
 * 处理数据脱敏工具类
 *
 * @author 陈超
 *
 * @version $Revision$
 *
 * @since 2015年12月9日
 */
public class DesensitizationUtils {

	public static void main(String[] args) {

		System.out.println(testDesensMobile("12345"));
		System.out.println(testDesensEmail("1@126.com"));
		
	}
	

	/**
	 * 
	 * 功能描述：脱敏手机号
	 *
	 * @param str
	 * @return
	 *
	 * @author 陈超
	 *
	 * @since 2015年12月9日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String testDesensMobile(String str){
		if(str.length()>7){
			return str.substring(0,3)+returnDesensSymbol(str.length()-7)+str.substring(str.length()-4, str.length());
		}else if(str.length()>4){
			return str.substring(0,3)+"*"+str.substring(str.length()-4, str.length());
		}
		return str;
	}
	/**
	 * 
	 * 功能描述：脱敏邮箱
	 *
	 * @param str
	 * @return
	 *
	 * @author 陈超
	 *
	 * @since 2015年12月9日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String testDesensEmail(String str){
		String temp = str.substring(0,str.lastIndexOf("@"));
		if(temp.length()>2){
			return temp.substring(0,1)+returnDesensSymbol(temp.length()-2)+temp.substring(temp.length()-1, temp.length())+str.substring(str.lastIndexOf("@"));
		}else{
			return temp.substring(0,1)+"*"+temp.substring(temp.length()-1, temp.length())+str.substring(str.lastIndexOf("@"));
		}
	}
	/**
	 * 
	 * 功能描述：返回脱敏符号
	 *
	 * @param length
	 * @return
	 *
	 * @author 陈超
	 *
	 * @since 2015年12月9日
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String returnDesensSymbol(int length){
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<length; i++){
			sb.append("*");
		}
		return sb.toString();
	}
}
