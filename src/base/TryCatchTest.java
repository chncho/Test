package base;

public class TryCatchTest {

	public static void main(String[] args) {
		new TryCatchTest().testTryCatch();
	}
	void testTryCatch(){
		System.out.println("begining...");
		System.out.println("111");
		if(true){
			System.out.println("333");
		//	return;
		}
		System.out.println("222");
		try {
			System.out.println("executing it ... ");
			throw new Exception("by throw...");
		} catch (Exception e) {
			System.out.println("exception...");
			e.printStackTrace();
		}finally{
			System.out.println("finally");
		}
		System.out.println("ending");
	}
}
