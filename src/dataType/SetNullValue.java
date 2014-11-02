package dataType;
import java.util.Date;


public class SetNullValue {

	public static void main(String[] args) {
		Person1 person1 = new Person1();
		person1.setDate(null);
		person1.setId(null);
		person1.setName(null);
		System.out.println("x");
	}
}

class Person1{
	private Integer id;
	private String name;
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
