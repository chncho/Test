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

public class Csv2ExcelForJKPOSTest {
	 private static final int FLUSHNUM = 100;
	//文件夹，自动扫描文件夹下的文件
	 private static String sourceExcelFolder = "E:\\大POS\\返回文件-正式\\正常";
	 private static String headLine = "A|B|C";
	 
	 public static void main(String[] args) throws IOException {
		 
			File fileNew ;
	        FileOutputStream outNew;
	     	Workbook workbookNew;
			Sheet sheetNew ;
           
          
			BufferedReader reader = null;
			File dirFile = new File(sourceExcelFolder);
			File[] fileList = dirFile.listFiles();
			
			
			for (File file2 : fileList) {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),"GBK"));
				String line = null;
				long lineCounts = 0;
				
				fileNew = new File(sourceExcelFolder+"/"+file2.getName()+".xlsx");
		        fileNew.createNewFile();
		        outNew = new FileOutputStream(fileNew);
		        workbookNew = new SXSSFWorkbook(FLUSHNUM);
				sheetNew = workbookNew.createSheet("sheet1");

				fillRow(sheetNew,headLine,lineCounts);
				lineCounts++;
				while ((line = reader.readLine()) != null) {
					if (line.length() > 0) {
						
						fillRow(sheetNew,line,lineCounts);
						
						lineCounts++;
					}
				}
				
				workbookNew.write(outNew);
				outNew.flush();
				outNew.close();
				((SXSSFWorkbook) workbookNew).dispose();
				
				System.out.println("总记录数："+(lineCounts-1));
			}
		
	}
	 

	private static void fillRow(Sheet sheetNew, String line,long lineCounts) {
		Row row = sheetNew.createRow((int) lineCounts);
		String[] lineArr = line.split("|");

		row.createCell(0).setCellValue(lineArr[1].replaceAll("\"", ""));
		row.createCell(1).setCellValue(lineArr[4].replaceAll("\"", ""));
		row.createCell(2).setCellValue(lineArr[1].replaceAll("\"", ""));
	}
	 
}



