package bussiness.excelsplit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class SplitCsv2ExcelTest {
	private static final int FLUSHNUM = 100;
	private static final String FILE_ENCODE = "UTF-8";
//	private static final String FILE_ENCODE = "GBK";
//	private static final String FILE_ENCODE = "GB2312";
	private static final String SPLITS_CHAR = ",";
//	private static final String SPLITS_CHAR = "\\?\\?";
	private static int countsPerExcel=2*10000;
	//文件夹，自动扫描文件夹下的所有文件,其下不能有文件夹
	 private static String sourceExcelFolder = "E:\\WORK_LEFU\\新卡友\\ky-zd-0908";
	 
	 public static void main(String[] args) throws IOException {
		 
			File fileNew ;
	        FileOutputStream outNew;
	     	Workbook workbookNew;
			Sheet sheetNew ;
           
          
			BufferedReader reader = null;
			File dirFile = new File(sourceExcelFolder);
			File[] fileList = dirFile.listFiles();
			
			File resDir = new File(sourceExcelFolder+"/splited");
			if(resDir.exists()){
				resDir.delete();
			}
			resDir.mkdirs();
			
			
			String filename;
			
			for (File file2 : fileList) {
				if(file2.isDirectory()){
					continue;
				}
				
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),FILE_ENCODE));
				String line = null;
				String headLine = null;
				long lineCounts = 0;
				int pageIndex=1;
				
				filename = fetchFilePureName(file2)+"-"+pageIndex;
				
				fileNew = new File(resDir.getAbsolutePath()+"/"+filename+".xlsx");
		        fileNew.createNewFile();
		        outNew = new FileOutputStream(fileNew);
		        workbookNew = new SXSSFWorkbook(FLUSHNUM);
				sheetNew = workbookNew.createSheet("sheet1");
					
				while ((line = reader.readLine()) != null) {
					if (line.length() > 0) {
						long countsPerPage = lineCounts%countsPerExcel;
						
						if(0==lineCounts) {
							headLine=line;
						}
						
						if(0==lineCounts){
							fillRow(sheetNew,headLine+","+"批次号",countsPerPage);
						}else if(0!=lineCounts&&(0==countsPerPage)){
							workbookNew.write(outNew);
							outNew.flush();
							outNew.close();
							((SXSSFWorkbook) workbookNew).dispose();
							
							pageIndex++;
							
							fileNew = new File(resDir.getAbsolutePath()+"/"+fetchFilePureName(file2)+"-"+pageIndex+".xlsx");
					        fileNew.createNewFile();
					        outNew = new FileOutputStream(fileNew);
					        workbookNew = new SXSSFWorkbook(FLUSHNUM);
							sheetNew = workbookNew.createSheet("sheet1");
							
							fillRow(sheetNew,headLine+","+"批次号",countsPerPage);
							lineCounts++;
							countsPerPage = lineCounts%countsPerExcel;
							fillRow(sheetNew,line+","+fileNew.getName(),countsPerPage);
						}else{
							fillRow(sheetNew,line+","+fileNew.getName(),countsPerPage);
						}
						
						lineCounts++;
					}
				}
				
				workbookNew.write(outNew);
				outNew.flush();
				outNew.close();
				((SXSSFWorkbook) workbookNew).dispose();
				
				System.out.println(file2.getName()+":总行数(包含标题)："+lineCounts+"；总文件数："+pageIndex+"；总记录数："+(lineCounts-pageIndex));
			}
		
	}
	 

	private static String fetchFilePureName(File file2) {
		return file2.getName().substring(0, file2.getName().indexOf("."));
	}


	private static void fillRow(Sheet sheetNew, String line,long lineCounts) {
		Row row = sheetNew.createRow((int) lineCounts);
		String[] lineArr = line.split(SPLITS_CHAR);
		for(int i=0;i<lineArr.length;i++){
			row.createCell(i).setCellValue(lineArr[i].replaceAll("\"", "").replaceAll("\\?", ""));;
		}
	}
	 
}



