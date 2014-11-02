package oopTest;

import java.lang.reflect.Field;

public class User{
	String name;
	String passwd;
	
	public static void setUser(User user){
		user.setName("aaa");
		user.setPasswd("bbb");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		Class clazz = this.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for(Field field : fields){
			try {
				sb.append(","+field.getName()+":"+field.get(this));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		sb.replace(0, 1, "");
		return "{"+sb.toString()+"}";
	}
}