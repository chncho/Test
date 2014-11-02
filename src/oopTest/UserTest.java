package oopTest;

public class UserTest {
	public static void main(String[] args) {
		User user = new User();
		user.setName("a");
		user.setPasswd("b");
		System.out.println(user);
		User.setUser(user);
		System.out.println(user);
		System.out.println("====================================");
	}
}
