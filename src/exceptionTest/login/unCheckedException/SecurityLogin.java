package exceptionTest.login.unCheckedException;

import java.util.Scanner;

public class SecurityLogin {
	private static String[] admin={"admin", "jpf"};

	public SecurityLogin() {
	}
	
	public static void validateLogin(String user, String pass){
		if(user.equals(admin[0]) && pass.equals(admin[1])){
			System.out.printf("Welcome %s, you are now successfully logged in.",user);
		}else{
			throw new SecurityBreachException();
		}
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a Username: ");
		String iUser = input.next();
		
		System.out.println("Enter a Password: ");
		String iPass = input.next();
		
		SecurityLogin.validateLogin(iUser, iPass);
	}
}
