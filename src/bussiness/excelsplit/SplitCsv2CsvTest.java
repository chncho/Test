package bussiness.excelsplit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * 自动将文件夹下的所有文件分割并转换成Excel
 * 
 * @author chnch
 *
 */
public class SplitCsv2CsvTest {

	private static final String OUTPUT_FILE_ENCODE = "UTF-8";
//	private static final String INPUT_FILE_ENCODE = "UTF-8";
	private static final String INPUT_FILE_ENCODE = "GBK";
//	private static final String INPUT_FILE_ENCODE = "GB2312";
	private static final String SPLITS_CHAR = ",";
//	private static final String SPLITS_CHAR = "\\?\\?";
	private static int countsPerCsv=90*10000;
	//文件夹，自动扫描文件夹下的所有文件,其下不能有文件夹
	 private static String sourceExcelFolder = "F:\\TEMPS\\test\\testSplit";
	 
	 public static void main(String[] args) throws IOException {
		 
			File fileNew ;
			OutputStreamWriter outNew = null;
           
          
			BufferedReader reader = null;
			File dirFile = new File(sourceExcelFolder);
			File[] fileList = dirFile.listFiles();
			
			File resDir = new File(sourceExcelFolder+"/splited");
			if(resDir.exists()){
				resDir.delete();
			}
			resDir.mkdirs();
			
			
			for (File file2 : fileList) {
				if(file2.isDirectory()){
					continue;
				}
				
				
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),INPUT_FILE_ENCODE));
				String line = null;
				String headLine = null;
				long lineCounts = 0;
				int pageIndex=0;
				
				
					
				while ((line = reader.readLine()) != null) {
					if (line.length() > 0) {
						long countsPerPage = lineCounts%countsPerCsv;
						
						if(0==lineCounts) {
							headLine=line;
						}
						
						if(0==countsPerPage){
							
							if(0!=pageIndex){
								outNew.flush();
								outNew.close();
							}
							
							pageIndex++;
							
							fileNew = new File(resDir.getAbsolutePath()+"/"+fetchFilePureName(file2)+"-"+pageIndex+".csv");
							outNew = new OutputStreamWriter(new FileOutputStream(fileNew), OUTPUT_FILE_ENCODE);
							
							fillRow(outNew,headLine);
							lineCounts++;
							if(lineCounts>1){
								fillRow(outNew,line);
								lineCounts++;
							}
						}else{
							fillRow(outNew,line);
							lineCounts++;
						}
						countsPerPage = lineCounts%countsPerCsv;
					}
				}
				
				outNew.flush();
				outNew.close();
				
				System.out.println(file2.getName()+":总行数(包含标题)："+lineCounts+"；总文件数："+pageIndex+"；总记录数："+(lineCounts-pageIndex));
			}
		
	}
	 



	private static void fillRow(OutputStreamWriter outNew, String line) throws IOException {
		outNew.write(line+"\r\n");
	}




	private static String fetchFilePureName(File file2) {
		return file2.getName().substring(0, file2.getName().indexOf("."));
	}
	 
}



