/**
 * class DBProperties
 * get the db preference from properties files
 * @author chenchao
 */
package dbtest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBProperties extends Properties{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5768247833229017526L;
	
	private static DBProperties instance= null;
	
	
	/**
	 * constructor
	 * loading the properties files to inputStream
	 */
	private DBProperties(){
		
		InputStream inputStream = getClass().getResourceAsStream("mysqldb.properties"); 
		try {
			load(inputStream);
		} catch (IOException e) {
			System.out.println("加载属性文件到输入流错误");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * getInstance (belong the confirm of no instance already exists)
	 */
	public static DBProperties getInstance(){
		
		if(instance != null){
			return instance;
		}
		else{
			makeInstance();
			return instance;
		}
		
	}
	
	
	/**
	 * mkeInstance on condition of no instance exist
	 */
	private static synchronized void makeInstance(){
		
		if(instance == null){
			instance = new DBProperties();
		}
		
	}
	
//	public static void main(String[] args) {
//		DBProperties dbp = DBProperties.getInstance();
//		System.out.println(dbp.getProperty("url")
//				+ dbp.getProperty("username")
//				+ dbp.getProperty("userpassword"));
//				
//	}
	
}
