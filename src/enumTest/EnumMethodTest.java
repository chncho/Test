package enumTest;

public enum EnumMethodTest {

	//为每个enum实例添加不同和实现方法
	SAMPLE1{
		String getInfo(){
			return "SAMPLE1";
		}
	},
	SAMPLE2{
		String getInfo(){
			return "SAMPLE2";
		}
	};
	abstract String getInfo();
	
	public static void main(String[] args) {
		for(EnumMethodTest m : EnumMethodTest.values()){
			System.out.println(m.getInfo());
		}
	}
}
