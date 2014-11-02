package relationShip.someObjects;

import java.lang.reflect.Field;

import reflect.ReflectClass;
/**
 * 测试用对象
 * @author 陈超
 * @date Dec 19, 2012 11:55:38 AM
 */
public class Person{
	String name;
	String gender;
	
	/**
	 * 改了形参与原对象共用的堆内存中的内容
	 * @param person
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @author 陈超
	 * @since Dec 19, 2012 11:52:20 AM
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void changePerson(Person person) throws IllegalArgumentException, IllegalAccessException{
		System.out.println("Person.changePerson...person..."+/*ReflectClass.toString(person)*/person);
		person.setName("哈哈，我改了姓名");
		person.setGender("哈哈，我改了性别");
		System.out.println("Person.changePerson...person..."+/*ReflectClass.toString(person)*/person);
	}
	/**
	 * 改了形参中与原对象独立的栈内存中的链接
	 * @param person
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @author 陈超
	 * @since Dec 19, 2012 11:53:07 AM
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void changePerson2(Person person) throws IllegalArgumentException, IllegalAccessException{
		System.out.println("Person.changePerson...person..."+/*ReflectClass.toString(person)*/person);
		Person person2 = new Person();
		person2.setName("哈哈，我改了姓名");
		person2.setGender("哈哈，我改了性别");
		person = person2;
		System.out.println("Person.changePerson...person..."+/*ReflectClass.toString(person)*/person);
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

	@Override
	public String toString(){
		Class clazz = this.getClass();
		StringBuffer sb = new StringBuffer();
		
		Field[] fields = clazz.getDeclaredFields();//包含私有
		for(Field f : fields){
			try {
				sb.append(","+f.getName()+":"+f.get(this));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		sb.replace(0, 1, "{");
		sb.append("}");
		return sb.toString();
	}
	
	/*public String toString() {
		try {
			return ReflectClass.toString(this);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			return null;
		}
	}*/
}
