package dataType;

import java.io.File;

public class StringReplace {

	public static void main(String[] args) {	
		String path = "D:/enviroment/apache-tomcat-6.0.37-windows-x64/apache-tomcat-6.0.37/webapps/dxt_game_admin/weixin/2014/1/18";
		File f = new File(path);
		
		System.out.println(f.getAbsolutePath());
		System.out.println(f.getAbsolutePath().indexOf("\\"));
		System.out.println(f.getAbsolutePath().replaceAll("\\\\", "/"));
	}
}
