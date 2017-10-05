package special;

public class SwitchCaseTest {

	public static void main(String[] args) {
		/**
		 * ERRORS
		 */
		int x = 2,y = 3;
		switch (x) {
		default:
			y++;
			System.out.println("defailt");
		case 3:
			y++;
			System.out.println("3");
		case 4:
			y++;
			System.out.println("4");

		}
	}
}
