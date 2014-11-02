package dataType;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalTest {

	/**
	 * 使用BigDecimal并用String来构造
	 * 功能描述：
	 *
	 * @param args
	 *
	 * @author 陈超
	 *
	 * @since 2013-7-30
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void main(String[] args) {
		test2();
	}
	
	public static void test1(){
		BigDecimal priceT = new BigDecimal(0+"");
		priceT.setScale(2,BigDecimal.ROUND_HALF_UP);
		priceT = priceT.add(new BigDecimal(3.111+""),MathContext.UNLIMITED);
		priceT = priceT.add(new BigDecimal(2.22+""),MathContext.UNLIMITED);
		System.out.println(priceT.doubleValue());
		
		
		double d = 1.1;
		d = d+ 2.2;
		System.out.println(d);
	}
	public static void test2(){
		BigDecimal priceT = new BigDecimal(0+"");
		priceT.setScale(1,BigDecimal.ROUND_HALF_UP);
		priceT = priceT.add(new BigDecimal(3.111+""));//,MathContext.UNLIMITED);
		priceT = priceT.add(new BigDecimal(2.22+""));//,MathContext.UNLIMITED);
		System.out.println("priceT.intValue()..."+priceT.intValue());
		
		
		double d = 1.1;
		d = d+ 2.2;
		System.out.println(d);
	}
}
