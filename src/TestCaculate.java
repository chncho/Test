import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class TestCaculate {

	/*
		办公用品...520.4
		交通费...831.0
		差旅费...3003.0
		总计...4354.4
	
	 */
	public static void main(String[] args) {
		BigDecimal bangong = sumAll(PriceLstsBANGONG);
		BigDecimal jiaotong = sumAll(PriceLstsCHUZUCHE).add(sumAll(PriceLstsGONGJIAO));
		BigDecimal chailv = sumAll(PriceLstsHUOCHE).add(sumAll(PriceLstsQICHE));
		System.out.println("办公用品..."+bangong);
		System.out.println("交通费..."+jiaotong);
		System.out.println("差旅费..."+chailv);
		System.out.println("总计..."+bangong.add(jiaotong).add(chailv));
	}
	
	
	static final double[] PriceLstsBANGONG;
	static final double[] PriceLstsCHUZUCHE;
	static final double[] PriceLstsGONGJIAO;
	static final double[] PriceLstsQICHE;
	static final double[] PriceLstsHUOCHE;
	static{
		PriceLstsBANGONG=new double[]{
				277.1,61.9,88.7,92.7,//办公用品
		};
		PriceLstsCHUZUCHE=new double[]{
				47,27,31,22,14,38,25,5,1,2,1,1,//出租车
		};
		PriceLstsGONGJIAO= new double[]{
				20,20,20,27,10,10,20,20,50,100,100,100,20,50,10,20,20,//公交卡等
		};
		PriceLstsQICHE= new double[]{
				30,30,100,100,75,75,	//汽车
		};
		PriceLstsHUOCHE= new double[]{
				536,575,23.5,35.5,54.5,54.5,23.5,44.5,44.5,254.5,72,349,361,72,93//火车票
		};
	}
	/**
	 * 
	 * 功能描述：给数组里面的数字求和
	 *
	 * @param items
	 * @return
	 *
	 * @author 陈超
	 *
	 * @since 2014-3-25
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static BigDecimal sumAll(double[] items){
		BigDecimal sum = BigDecimal.valueOf(0);
		for(double item : items){
			sum=sum.add(BigDecimal.valueOf(item));
		}
		return sum;
	}
}
