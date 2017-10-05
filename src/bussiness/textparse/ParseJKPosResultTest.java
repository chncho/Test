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
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.chainsaw.Main;
/**
 * 解析金控返回的异常文件，并按错误类型输出到不同文件中
 * 异常文件格式为文本，如：
 * 8625343369|衡水志勇加油站|“市”有误，无法获取市编码（衡水市）
	8625362021|信誉商行店|“市”有误，无法获取市编码（衡水市）
	8625367053|衡水启程金属丝网制品有限公司|“市”有误，无法获取市编码（衡水市）
 * @author chnch
 *
 */
public class ParseJKPosResultTest {
	private static final int errorTypeIndex = 2;
	//列之间的分隔符
	private static final String columnSplitChar = "\\|";
	//存放异常返回文件的文件夹，其下不能有文件夹，程序会自动扫描文件夹下的所有文件。荐于数据量的原因，未做针对内存的程序优化
	static String feedBackFileDir = "E:\\WORK_LEFU\\合规&检查\\续展相关\\金控\\第二次导入-合集-新\\回执\\大POS\\ALL\\target\\error";
	static String feedBackParseResFileDir = "E:\\WORK_LEFU\\合规&检查\\续展相关\\金控\\第二次导入-合集-新\\回执\\大POS\\ALL\\target\\error\\parseResult";

	private static final String INPUT_FILE_ENCODE = "UTF-8";
	private static final String OUTPUT_FILE_ENCODE = "UTF-8";
	
	public static void main(String[] args) throws IOException {
//		System.out.println(convertFileName("输入内容非当前可填内容(餐饮类\\一般类\\...)（房车类）"));
		execute();
	}
	private static void execute() throws UnsupportedEncodingException, IOException{

		Map<String, List<String>> errormap = new HashMap<String, List<String>>();
		BufferedReader reader = null;
		File dirFile = new File(feedBackFileDir);
		File[] fileList = dirFile.listFiles();
		
		File mergedDirFile = new File(feedBackParseResFileDir);
		if(mergedDirFile.exists()){
			mergedDirFile.delete();
		}
		mergedDirFile.mkdirs();
		
		int count = 0;
		String headsInfo=null;
		
		for (File file2 : fileList) {
			if(file2.isDirectory()){
				System.out.println("it is a dir\t"+file2.getAbsolutePath());
				continue;
			}
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),INPUT_FILE_ENCODE));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.length() > 0) {
					String[] lineSplits =  line.split(columnSplitChar);
					String errorType = lineSplits[errorTypeIndex];
					List<String> list = errormap.get(errorType);
					if (list == null) {
						list = new ArrayList<String>();
						errormap.put(errorType, list);
					} 
					list.add(buildResultLine(lineSplits));
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
			
//			System.out.println("... ... ... "+convertFileName(errorMsg));
			
			File file = new File(feedBackParseResFileDir+"\\"+convertFileName(errorMsg)+".txt");
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file),OUTPUT_FILE_ENCODE);
 			for(String res : resList){
				out.write(res);
			}
			
			out.flush();
			out.close();
		}
	}
	private static String convertFileName(String errorMsg) {
		return errorMsg.replaceAll("\\\\", "").replaceAll("/", "");
	}
	
	//从反馈文件中的错误信息中提取出原注册的文件信息来
	private static String buildResultLine(String[] lineSplits) {
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<lineSplits.length; i++){
			sb.append(lineSplits[i]+",");
		}
		
		try {
			return sb.substring(0, sb.length()-1)+"\r\n";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
