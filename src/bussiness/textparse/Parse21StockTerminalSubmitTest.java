package bussiness.textparse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Parse21StockTerminalSubmitTest {

//	private static final int TERMINAL_NO_INDEX = 1;//存量终端强化
	private static final int TERMINAL_NO_INDEX = 4;//正常提报
	static String fileDir = "F:\\TEMPS\\test\\21号文\\20170628\\整理\\终端提报返回\\statics";
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		File dirFile = new File(fileDir);
		File[] fileList = dirFile.listFiles();
		
		File totalFile = new File(fileDir+"/"+"total.txt");
		OutputStream out = new FileOutputStream(totalFile);
		
		long countTotal = 0;	
		
		for (File file2 : fileList) {
			long countPerFile = 0;
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),"GBK"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.length() > 0) {
					out.write((line.split(",")[TERMINAL_NO_INDEX]+"\r\n").getBytes("GBK"));
					countPerFile++;
				}
			}
			out.flush();
			countPerFile++;
			System.out.println(file2.getName()+":"+countPerFile);
			countTotal += countPerFile;
		}
		System.out.println("total:"+countTotal);
		out.close();
	}



}
