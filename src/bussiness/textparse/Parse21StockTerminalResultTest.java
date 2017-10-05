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

public class Parse21StockTerminalResultTest {
	static String feedBackFileDir = "F:\\TEMPS\\test\\21号文\\20170628\\整理\\存量终端提报返回";
	
	public static void main(String[] args) throws IOException {
		Map<String, List<String>> errormap = new HashMap<String, List<String>>();
		BufferedReader reader = null;
		File dirFile = new File(feedBackFileDir);
		File[] fileList = dirFile.listFiles();
		
		int count = 0;
		String headsInfo=null;
		
		for (File file2 : fileList) {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),"GBK"));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.length() > 0) {
					if(line.startsWith("记录总数")){
						headsInfo = line;
						continue;
					}
					if(!line.startsWith("原第")){
						continue;
					}
					String errorType = line.substring(line.indexOf("出错，原因是：")+"出错，原因是：".length());
					List<String> list = errormap.get(errorType);
					if (list == null) {
						list = new ArrayList<String>();
						errormap.put(errorType, list);
					} 
					list.add(line.substring(line.indexOf("条记录:")+"条记录:".length(), line.indexOf("出错，原因是："))+"\r\n");
				}
			}
			System.out.println(file2.getName()+":headsInfo:"+headsInfo);
			printErrormapStatics(file2.getName(),errormap);
			System.out.println(file2.getName()+":"+count);
		}
	}
	//把错误汇总信息打印出来 ，把错误信息明细输出到文件中
	private static void printErrormapStatics(String filename,Map<String, List<String>> errormap) throws UnsupportedEncodingException, IOException {
		Iterator<String> it = errormap.keySet().iterator();
		while(it.hasNext()){
			String errorMsg = it.next();
			List<String> resList = errormap.get(errorMsg);
			long size = resList.size();
			System.out.println(filename+":"+errorMsg+"\t:\t"+size);
			
			File file = new File(feedBackFileDir+"\\"+errorMsg+".txt");
			OutputStream out = new FileOutputStream(file);
			for(String res : resList){
				out.write(res.getBytes("GBK"));
			}
			
			out.flush();
			out.close();
		}
	}

}
