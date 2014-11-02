package relationShip.paraTransInMethod;

import reflect.ReflectClass;
import relationShip.someObjects.Person;

public class ObjectTransInMethod {
	public static Person person=null;
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		person = new Person();
		person.setName("陈超");
		person.setGender("男");
		System.out.println("main...person..."+person);
		Person.changePerson(person);
		System.out.println("main...person..."+person);
	  /*person = new Person();异常？？？
		person.setName("陈超");
		person.setGender("男");
		System.out.println("main...person..."+person);
		Person.changePerson(person);
		System.out.println("main...person..."+person);*/
		
		System.out.println("=====================");
		person = new Person();
		person.setName("陈超");
		person.setGender("男");
		System.out.println("main...person..."+person);
		Person.changePerson2(person);
		System.out.println("main...person..."+person);
	}
}

