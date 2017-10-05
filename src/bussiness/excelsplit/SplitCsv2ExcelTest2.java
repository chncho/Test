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

public class SplitCsv2ExcelTest2 {
	 private static final int FLUSHNUM = 100;
	private static int countsPerExcel=20000;
	 private static String sourceExcelFolder = "E:\\WORK_LEFU\\合规&检查\\续展相关\\金控\\手刷\\JK-ishua\\C-1.5";
	 
	 public static void main(String[] args) throws IOException {
		 
			File fileNew ;
	        FileOutputStream outNew;
	     	Workbook workbookNew;
			Sheet sheetNew ;
           
          
			Map<String, List<String>> errormap = new HashMap<String, List<String>>();
			BufferedReader reader = null;
			File dirFile = new File(sourceExcelFolder);
			File[] fileList = dirFile.listFiles();
			
			
			for (File file2 : fileList) {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),"UTF-8"));
				String line = null;
				String headLine = null;
				long lineCounts = 0;
				int pageIndex=1;
				
				fileNew = new File(sourceExcelFolder+"/"+file2.getName()+"-"+pageIndex+".xlsx");
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
							fillRow(sheetNew,headLine,countsPerPage);
						}else if(0!=lineCounts&&(0==countsPerPage)){
							workbookNew.write(outNew);
							outNew.flush();
							outNew.close();
							((SXSSFWorkbook) workbookNew).dispose();
							
							pageIndex++;
							
							fileNew = new File(sourceExcelFolder+"/"+file2.getName()+"-"+pageIndex+".xlsx");
					        fileNew.createNewFile();
					        outNew = new FileOutputStream(fileNew);
					        workbookNew = new SXSSFWorkbook(FLUSHNUM);
							sheetNew = workbookNew.createSheet("sheet1");
							
							fillRow(sheetNew,headLine,countsPerPage);
							lineCounts++;
							countsPerPage = lineCounts%countsPerExcel;
							fillRow(sheetNew,line,countsPerPage);
						}else{
							fillRow(sheetNew,line,countsPerPage);
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
	 

	private static void fillRow(Sheet sheetNew, String line,long lineCounts) {
		Row row = sheetNew.createRow((int) lineCounts);
		String[] lineArr = line.split(",");
		for(int i=0;i<lineArr.length;i++){
			row.createCell(i).setCellValue(lineArr[i].replaceAll("\"", ""));
		}
	}
	 
}



