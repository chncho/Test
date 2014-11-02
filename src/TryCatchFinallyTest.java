public class TryCatchFinallyTest {

	public static void main(String[] args) {
		System.out.println(new TryCatchFinallyTest().test());
		;
	}

	static int test() {
		int x = 1;
		System.out.println("1:x="+x);
		try {
			x++;
			System.out.println("2:x="+x);
			return x;
		} finally {
			++x;
			System.out.println("3:x="+x);
		}
	}
}
