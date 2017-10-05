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

public class Parse21TerminalResultTest {
	static String fileDir = "F:\\TEMPS\\test\\21号文\\20170628\\整理\\终端提报返回";//"\\statics";
	private static int TERMINAL_NO_INDEX;
	private static final int TERMINAL_NO_INDEX_NORMAL = 5;//正常提报
	private static final int TERMINAL_NO_INDEX_STOCk = 1;//存量强化
	
	public static void main(String[] args) throws IOException {
		StringBuffer sbException = new StringBuffer();
		long excpetionCounts=0;
		Map<String, OutputStream> errormap = new HashMap<String, OutputStream>();
		BufferedReader reader = null;
		File dirFile = new File(fileDir);
		File[] fileList = dirFile.listFiles();
		
		long countTotal = 0;
		String headsInfo=null;
		File totalDir = new File(fileDir+"/statics");
		if(totalDir.exists()){
			totalDir.delete();
		}
		totalDir.mkdirs();
		
		for (File file2 : fileList) {
			if(file2.isDirectory()){
				continue;
			}
			System.out.println("filename\t:\t"+file2.getName());
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),"GBK"));
			String line = null;
			long countPerFile = 0;
			while ((line = reader.readLine()) != null) {
				try {
					if (line.length() > 0) {
						if(line.startsWith("R")){
							headsInfo = line;
							TERMINAL_NO_INDEX=TERMINAL_NO_INDEX_NORMAL;
							continue;
						}else if(line.startsWith("记录总数")){
							headsInfo = line;
							TERMINAL_NO_INDEX=TERMINAL_NO_INDEX_STOCk;
							continue;
						}else if(line.startsWith("-------以下为原装载文件中因存在错误而装载失败的记录，请修改后重新装载------")){
							break;
						}
						String[] lineSplits =  line.split(",");
						String errorType;
						if(TERMINAL_NO_INDEX==TERMINAL_NO_INDEX_NORMAL){
							errorType = /*lineSplits[lineSplits.length-2]+"_"+lineSplits[0]+"_"+*/lineSplits[lineSplits.length-1];
						}else{
							errorType = line.substring(line.indexOf("出错，原因是：")+"出错，原因是：".length());
						}
						OutputStream outputStream = errormap.get(errorType);
						if (outputStream == null) {
							System.out.println(totalDir.getPath()+"\\"+errorType+".txt");
							File file = new File( totalDir.getPath()+"/"+errorType+".txt");
							file.createNewFile();
							outputStream = new FileOutputStream(file);
							errormap.put(errorType, outputStream);
						} 
						outputStream.write((lineSplits[TERMINAL_NO_INDEX]+"\r\n").getBytes("GBK"));
						countPerFile++;
					}
				} catch (Exception e) {
					sbException.append(line+"\t:\t"+e.toString()+"\r\n");
					excpetionCounts++;
				}
			}
			countPerFile++;
			System.out.println(file2.getName()+":"+countPerFile);
			countTotal += countPerFile;
			
			
		}
		System.out.println("... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ...");
		System.out.println("excpetionCounts\t:\t"+excpetionCounts);
		System.out.println("sbException ... ... ... \r\n"+sbException);
		Iterator<OutputStream> it = errormap.values().iterator();
		while(it.hasNext()){
			OutputStream out = it.next();
			out.flush();
			out.close();
		}
		System.out.println("total:"+countTotal);
		System.out.println("... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ... ...");
	}

}
