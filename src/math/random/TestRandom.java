package math.random;

import java.util.Random;

public class TestRandom {

	public static void main(String[] args) {
		Random random = new Random(100);
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(9000)+1000 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(89)+11 +"");
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		System.out.println(new java.util.Random().nextInt(9)+1);
		prod1();
		prod2();
		prod3();
	}

//	也就是要求100到999之间的随机数， 
//	Math.random()()返回的是0到1之间的随机数，返回类型为double型，大于等于0，小于1， 
//	引用JDK1.6api 
//	public static double random()()返回带正号的 double 值，该值大于等于 0.0 且小于 1.0。返回值是一个伪随机选择的数，在该范围内（近似）均匀分布。 
//
//	Math.random()*900,就是0到900之间的数了，可以取到0，但是取不到900，但是这个乘积是double类型的，将它强制转换成int类型，然后加上100，就变成了可以取到100，但是取不到1000.完毕
	public static void prod0() 
	{ 
		int i=(int)(Math.random()*900)+100; 
		//int i= new java.util.Random().nextInt(900)+100;也可以
		System.out.println(i); 

	} 
	public static void prod1() {
		System.out.println("----------prod1 in--------------");
		// 1>生成随机数
		// Random random = new Random();
		// Random random = new Random(100);//指定种子数100
		// random调用不同的方法，获得随机数。
		// 如果2个Random对象使用相同的种子（比如都是100），并且以相同的顺序调用相同的函数，那它们返回值完全相同。如下面代码中两个Random对象的输出完全相同
		Random random1 = new Random(100);
		System.out.println(random1.nextInt());
		System.out.println(random1.nextFloat());
		System.out.println(random1.nextBoolean());
		Random random2 = new Random(100);
		System.out.println(random2.nextInt());
		System.out.println(random2.nextFloat());
		System.out.println(random2.nextBoolean());
		System.out.println("----------prod1 out--------------");
	}

	public static void prod2() {
		System.out.println("----------prod2 in--------------");
		// 2>指定范围内的随机数
		// 随机数控制在某个范围内,使用模数运算符%
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.println(Math.abs(random.nextInt()) % 10);
		}
		// 获得的随机数有正有负的，用Math.abs使获取数据范围为非负数
		System.out.println("----------prod2 out--------------");
	}

	public static void prod3() {
		System.out.println("----------prod3 in--------------");
		// 3>获取指定范围内的不重复随机数
		int[] intRet = new int[6];
		int intRd = 0; // 存放随机数
		int count = 0; // 记录生成的随机数个数
		int flag = 0; // 是否已经生成过标志
		while (count < 6) {
			Random rdm = new Random(System.currentTimeMillis());
			intRd = Math.abs(rdm.nextInt()) % 32 + 1;
			for (int i = 0; i < count; i++) {
				if (intRet[i] == intRd) {
					flag = 1;
					break;
				} else {
					flag = 0;
				}
			}
			if (flag == 0) {
				intRet[count] = intRd;
				count++;
			}
		}
		for (int t = 0; t < 6; t++) {
			System.out.println(t + "->" + intRet[t]);
		}
		System.out.println("----------prod3 out--------------");
	}
}
