package reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 反射测试
 * @author 陈超
 * @date Dec 19, 2012 11:17:16 AM
 */
public class ReflectClass {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException, SecurityException, InvocationTargetException, NoSuchMethodException {
		Person person = new Person();
		person.setName("陈超");
		person.setGender("男");
		
		System.out.println(ReflectClass.toString(person));
	}
	
	/**
	 * toString方法，输出对象的属性
	 * （对对象属性仍为对象的情况未做处理；即只做了其一级属性的输出）
	 * @param obj
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @author 陈超
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws SecurityException 
	 * @since Dec 19, 2012 11:16:42 AM
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String toString(Object obj) throws IllegalArgumentException, IllegalAccessException, SecurityException, InvocationTargetException, NoSuchMethodException{
		//此方法会报错，非一个文件的情况
		/*Class clazz = obj.getClass();
		StringBuffer sb = new StringBuffer();
		
		Field[] fields = clazz.getDeclaredFields();//包含私有
		for(Field f : fields){
			sb.append(","+f.getName()+":"+f.get(obj));
		}
		sb.replace(0, 1, "{");
		sb.append("}");
		return sb.toString();*/
		//此方法可行
		Class clazz = obj.getClass();
		StringBuffer sb = new StringBuffer();
		
		Field[] fields = clazz.getDeclaredFields();//包含私有
		for(Field f : fields){
			
			sb.append(","+f.getName()+":"+  clazz.getMethod("get"+f.getName().substring(0,1).toUpperCase()+f.getName().substring(1))
					.invoke(obj));
		}
		sb.replace(0, 1, "{");
		sb.append("}");
		return sb.toString();/**/
	}
}

class Person{
	String name;
	String gender;
	
	static void changePerson(Person person){
		person.setGender("哈哈，我改了姓名");
		person.setGender("哈哈，我改了性别");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
}