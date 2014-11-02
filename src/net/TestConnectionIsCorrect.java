package net;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 测试网络连接是否正常
 * @author 陈超
 * @date Nov 29, 2012 3:55:03 PM
 */
public class TestConnectionIsCorrect {

	public static void main(String[] args) {
		URL url = null;
		try {
			url= new URL("http://www.dixintong.com");
//			url= new URL("http://www.baidu.com");
			InputStream in = url.openStream();//???????????????
			System.out.println("Internet connect correct!");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Internet connect not correct!");
//			e.printStackTrace();
		}
	}
}
