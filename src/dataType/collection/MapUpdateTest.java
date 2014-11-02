package dataType.collection;
import java.util.HashMap;
import java.util.Map;


public class MapUpdateTest {

	public static void main(String[] args) {
		
		Person person = new Person();
		person.setName("namename");
		person.setGender(1);
		
		Map map = new HashMap();
		map.put("p", person);
		System.out.println(map.get("p"));
		
		Person pp = (Person)map.get("p");
		pp.setName("kkkk");
		System.out.println(map.get("p"));
	}
}

class Person{
	String name;
	Integer gender;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return this.getName() + "  " + this.getGender();
	}
	
	
}
