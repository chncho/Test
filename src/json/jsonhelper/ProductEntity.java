package json.jsonhelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProductEntity {
	private String productoid;//		':'11039741'
	private String productid;//	':'321420010023',
	private String productname;//	':'Apple iPhone 4 (8G) 行货正品 全新未拆封（黑色）',
	private double sumprice;//':'800.0'
	private int num;//':'1'
	private double price;//	':'800.0'}
	
	
	public String getProductoid() {
		return productoid;
	}
	public void setProductoid(String productoid) {
		this.productoid = productoid;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}
	public double getSumprice() {
		return sumprice;
	}
	public void setSumprice(double sumprice) {
		this.sumprice = sumprice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		Class clazz = ProductEntity.class;
		Field[] fields = clazz.getDeclaredFields();
		
		sb.append("{");
		for(Field f : fields){
			String fname = f.getName();
			try {
				Method mthd = clazz.getDeclaredMethod("get"+fname.substring(0, 1).toUpperCase()+fname.substring(1));
				sb.append(fname+":"+mthd.invoke(this)+",");
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		sb.substring(0, sb.length()-1).concat("}");
		
		return sb.toString();
	}
}
