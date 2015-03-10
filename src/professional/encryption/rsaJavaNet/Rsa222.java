package professional.encryption.rsaJavaNet;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Scanner;

import javax.crypto.Cipher;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Rsa222 {
	public static void main(String[] args) throws Exception {  
	       Scanner sc=new Scanner(System.in);  
	  
	       //获取公钥、模及明文的字符串  
	        System.out.println("请输入公钥：");  
	       String str_exponent=sc.nextLine();  
	       System.out.println("请输入模：");  
	       String str_modulus="";  
	       String st="";  
	       while(!(st=sc.nextLine()).equals(""))  
	       {  
	            str_modulus+=st;  
	       }  
	       System.out.println("请输入明文：");  
	       String str_m=sc.nextLine();  
	          
	       //创建公钥  
	        byte[] ary_exponent=(new BASE64Decoder()).decodeBuffer(str_exponent);  
	       byte[] ary_modulus=(new BASE64Decoder()).decodeBuffer(str_modulus);  
	       //注意构造函数，调用时指明正负值，1代表正值，否则报错  
	        BigInteger big_exponent = new BigInteger(1,ary_exponent);  
	       BigInteger big_modulus = new BigInteger(1,ary_modulus);  
	       RSAPublicKeySpec keyspec=new RSAPublicKeySpec(big_modulus,big_exponent);  
	       KeyFactory keyfac=KeyFactory.getInstance("RSA");  
	       PublicKey publicKey=keyfac.generatePublic(keyspec);  
	  
	        //进行加密  
	        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");     
	       cipher.init(Cipher.ENCRYPT_MODE, publicKey);     
	       byte[] enBytes = cipher.doFinal(str_m.getBytes());     
	       String s = (new BASE64Encoder()).encodeBuffer(enBytes);  
	       System.out.println("加密结果为：" + s);  
	} 
}
