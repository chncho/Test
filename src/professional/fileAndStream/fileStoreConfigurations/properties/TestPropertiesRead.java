/**
 * class DBProperties
 * get the db preference from properties files
 * @author chenchao
 */
package professional.fileAndStream.fileStoreConfigurations.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestPropertiesRead extends Properties{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5768247833229017526L;
	
	private static TestPropertiesRead instance= null;
	
	
	private TestPropertiesRead(){}
	/**
	 * constructor
	 * loading the properties files to inputStream
	 */
	private TestPropertiesRead(String propFileName){
		
		InputStream inputStream = getClass().getResourceAsStream(propFileName); 
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
	public static TestPropertiesRead getInstance(String propFileName){
		if(instance != null){
			return instance;
		}
		else{
			makeInstance(propFileName);
			return instance;
		}
		
	}
	
	
	/**
	 * mkeInstance on condition of no instance exist
	 */
	private static synchronized void makeInstance(String propFileName){
		
		if(instance == null){
			instance = new TestPropertiesRead(propFileName);
		}
		
	}
	
	public static void main(String[] args) {
		TestPropertiesRead dbp = TestPropertiesRead.getInstance("/test.properties");
		System.out.println(dbp.getProperty("defaultNick")
				+ dbp.getProperty("username")//若文件中未配，则返回null
				+ dbp.getProperty("defaultHeadShot"));
				
	}
	
}
