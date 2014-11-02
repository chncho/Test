package exceptionTest.login.checkedException;

import java.util.Scanner;

public class SecurityLogin {

	private static String[] admin = {"admin", "jpf"};

	public SecurityLogin() {
	}
	
	public static void validateLogin(String user, String pass) throws SecurityBreachException {
		if(user.equals(admin[0]) && pass.equals(admin[1])){
			System.out.printf("Welcome %s,%s,%s you are now successfully logged in. "
					,user,pass,9);//###
		}else{
			throw new SecurityBreachException();
		}
	}
	
	public static void main(String... args){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter a Username: ");
		String iUser = input.next();
		
		System.out.println("enter a Password: ");
		String iPass = input.next();
		
		try {
			SecurityLogin.validateLogin(iUser, iPass);
		} catch (SecurityBreachException e) {
			System.out.println("Login Failed 0100!");
			e.printStackTrace();//### always before 0100
			System.out.println("Login Failed 0200!");
		}
	}
}
