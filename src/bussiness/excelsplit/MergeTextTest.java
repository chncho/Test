package bussiness.excelsplit;

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
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MergeTextTest {
	
	static String fileDir = "E:\\WORK_LEFU\\合规&检查\\续展相关\\金控\\第二次导入-合集-新\\回执\\大POS\\ALL\\target\\success";

	private static final String INPUT_FILE_ENCODE = "UTF-8";//"GBK";
	private static final String OUTPUT_FILE_ENCODE = "UTF-8";
	
	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		File dirFile = new File(fileDir);
		File[] fileList = dirFile.listFiles();
		
		File totalFile = new File(fileDir+"/"+"total.txt");
		OutputStreamWriter out = new OutputStreamWriter( new FileOutputStream(totalFile) ,OUTPUT_FILE_ENCODE);
		
		long countTotal = 0;	
		
		for (File file2 : fileList) {
			if(file2.isDirectory()){
				continue;
			}
			long countPerFile = 0;
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),INPUT_FILE_ENCODE));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.length() > 0) {
					out.write((line+"\r\n"));
					countPerFile++;
					countTotal++;
				}
			}
			out.flush();
			countPerFile++;
			System.out.println(file2.getName()+":"+countPerFile);
		}
		System.out.println("total:"+countTotal);
		out.close();
	}}
