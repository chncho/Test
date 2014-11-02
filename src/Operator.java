
public class Operator {

	public static void main(String[] args) {
		
		//testAnd();
		test1();
	}
	
	
	public static void test1(){

		System.out.println(0%2);
		System.out.println(1%2);
		System.out.println(2%2);
		System.out.println(3%2);
	}
	
	public static void testAnd(){
		int tmp = 15;
		System.out.println(tmp&1);
		System.out.println(tmp&2);
		System.out.println(tmp&4);
		System.out.println(tmp&8);
	}
	/**
	 * 
	 * & 和&&有什么区别
	 * @author 陈超
	 * @since 2013-4-15 下午05:02:31
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void differenceBetweenAnd_andAnd(){
		for(int i=0; i<21; i++){
			System.out.println("i="+i+";\t i/5="+(i/5)+";\t (i/5%2)="+(i/5%2)+";");
		}
		
		System.out.println("=================& 和&&有什么区别=================");
		/*
		 * 是不是全部运算
		 */
		int i=0;
		int j=0;
		System.out.println("i...j..."+i+"..."+j);
		System.out.println(++i>2 && ++j>2);
		System.out.println("i...j..."+i+"..."+j);
		int k=0;/*
		k=k+1;//2 0
		k=k+2;//2 1
		k=k+4;//2 2
*/		
		/*
		 * 做按位与运算
		 */
		/**
		 * 1: 商品
		 * 2：类别
		 * 4：评论
		 */

		k=0;
//		k=k+1;
		k=k+2;
		k=k+4;

		System.out.println("商品管理权限"+(1==(k&1)));
		System.out.println("类别管理权限"+(2==(k&2)));
		System.out.println("评论管理权限"+(4==(k&4)));
		if(1==(k&1)){
			System.out.println("有商品管理权限");
		}
		if(2==(k&2)){
			System.out.println("有类别管理权限");
		}
		if(4==(k&4)){
			System.out.println("有评论管理权限");
		}
	}
}
