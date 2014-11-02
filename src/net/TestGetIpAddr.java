package net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestGetIpAddr {

	public static void main(String[] args) throws UnknownHostException {
		testGet1();
//		testGet2();
	}
	
	public static void testGet1()throws UnknownHostException{
		String str = InetAddress.getLocalHost().getHostAddress()  ;
		System.out.println(str);
	}
	public static void testGet2()throws UnknownHostException{
		String str = getLocalIP()  ;
		System.out.println(str);
	}
	
	public static String getLocalIP(){  
		InetAddress addr = null;  
		        try {  
		            addr = InetAddress.getLocalHost();  
		        } catch (UnknownHostException e) {  
		            e.printStackTrace();  
		                        return null;  
		        }  
		        byte[] ipAddr = addr.getAddress();  
		        String ipAddrStr = "";  
		        for (int i = 0; i < ipAddr.length; i++) {  
		            if (i > 0) {  
		                ipAddrStr += ".";  
		            }  
		            ipAddrStr += ipAddr[i] & 0xFF;  
		        }  
		        //System.out.println(ipAddrStr);  
		                return ipAddrStr;  
		}  
	
	public static void testGet3(){

//		String str = java.net.Inet4Address.getLocalHost().getAddress();
//		System.out.println(str);
	}
}
