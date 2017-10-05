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
 * 解析金控大POS正常的返回文件，并转换成能导入运营系统格式的excel文件
 * 返回文件格式为文本，例如：
 * MEC00000|834589052110072|8617251842|秋叶源经营部|0|77742831|6217003120010277629|陈秀筠
	MEC00000|834121050450106|8617251858|鼎安数码专营|0|77742832|6214854711471432|逯俊锋
	MEC00000|834361053110988|8617251931|宝华现代宾馆|0|77742833|6214855510454107|张建淮
	
	各列说明：0金控接口标识|1新生成商编|2原商编|3商户名称|4单笔结算手续费|5终端号|结算卡号|结算卡账户名
	
	
 * @author chnch
 *
 */
public class SplitCsv2ExcelForJKPosTest {
	 private static final int FLUSHNUM = 100;
	 //每个文件多少行，包含标题
	 private static final int countsPerExcel=1000000;//20000;
	 //文件夹，自动扫描文件夹下的所有文件,其下不能有文件夹
	 private static final String sourceExcelFolder = "E:\\WORK_LEFU\\合规&检查\\续展相关\\金控\\第二次导入-合集-新\\回执\\大POS\\ALL\\target\\success\\total";
	 //返回文件无标题，因此指定一个
	 private static final String headLine = "第三方商编|第三方终端号|唯一标识";
	 //针对返回文件格式为文本的：0金控接口标识|1新生成商编|2原商编|3商户名称|4单笔结算手续费|5终端号|结算卡号|结算卡账户名
	 private static final int[] columnArr = new int[]{1,5,2};//1,5,2//每一列需要取原来数据的第几块
	 
	 public static void main(String[] args) throws IOException {
		 
			File fileNew ;
	        FileOutputStream outNew;
	     	Workbook workbookNew;
			Sheet sheetNew ;
           
          
			BufferedReader reader = null;
			File dirFile = new File(sourceExcelFolder);
			File[] fileList = dirFile.listFiles();
			
			
			for (File file2 : fileList) {
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),"UTF-8"));
				String line = null;
				long lineCounts = 0;
				int pageIndex=1;
				
				fileNew = new File(sourceExcelFolder+"/"+fetchFilePureName(file2)+"-"+pageIndex+".xlsx");
		        fileNew.createNewFile();
		        outNew = new FileOutputStream(fileNew);
		        workbookNew = new SXSSFWorkbook(FLUSHNUM);
				sheetNew = workbookNew.createSheet("sheet1");
					
				while ((line = reader.readLine()) != null) {
					if (line.length() > 0) {
						long countsPerPage = lineCounts%countsPerExcel;
						
						if(0==lineCounts){
							fillHeader(sheetNew,headLine,countsPerPage);
							lineCounts++;
							countsPerPage = lineCounts%countsPerExcel;
							fillRow(sheetNew,line,countsPerPage);
						}else if(0!=lineCounts&&(0==countsPerPage)){
							workbookNew.write(outNew);
							outNew.flush();
							outNew.close();
							((SXSSFWorkbook) workbookNew).dispose();
							
							pageIndex++;
							
							fileNew = new File(sourceExcelFolder+"/"+fetchFilePureName(file2)+"-"+pageIndex+".xlsx");
					        fileNew.createNewFile();
					        outNew = new FileOutputStream(fileNew);
					        workbookNew = new SXSSFWorkbook(FLUSHNUM);
							sheetNew = workbookNew.createSheet("sheet1");
							
							fillHeader(sheetNew,headLine,countsPerPage);
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
				
				System.out.println(file2.getName()+";总文件数："+pageIndex+";总记录数:"+(lineCounts-pageIndex));
			}
		
	}
	 
	private static String fetchFilePureName(File file2) {
		return file2.getName().substring(0, file2.getName().indexOf("."));
	}

	private static void fillHeader(Sheet sheetNew, String line,long lineCounts) {
		Row row = sheetNew.createRow((int) lineCounts);
		String[] lineArr = line.split("\\|");

		for(int i=0;i<columnArr.length; i++){
			row.createCell(i).setCellValue(lineArr[i].replaceAll("\"", ""));
		}
		
	}
	private static void fillRow(Sheet sheetNew, String line,long lineCounts) {
		Row row = sheetNew.createRow((int) lineCounts);
		String[] lineArr = line.split("\\|");

		for(int i=0;i<columnArr.length; i++){
			row.createCell(i).setCellValue(lineArr[columnArr[i]].replaceAll("\"", ""));
		}
		
	}
	 
}



