package bussiness.excelsplit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MergeExcel2CsvTest {
	private static final long FLUSH_COUNTS = 100;
	private static final String INPUT_FILE_ENCODE = "GBK";
	private static final String OUTPUT_FILE_ENCODE = "UTF-8";
	private static String dir = "E:\\WORK_LEFU\\合规&检查\\续展相关\\金控\\第二次导入-合集-新\\回执\\手刷\\1-1&B1-B21批回执&A-1A-2A-3A-4";
//	private static final int titleLine=1;
//	private static final int firstDataLine=2;
	
	static long rowCounts = 0;
	static int fileCounts = 0;
	
	public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, InvalidFormatException {

		File dirFile = new File(dir);
		File[] fileList =  dirFile.listFiles();
		
		File mergedDirFile = new File(dirFile.getAbsolutePath()+"/mergedCsv");
		if(mergedDirFile.exists()){
			mergedDirFile.delete();
		}
		mergedDirFile.mkdirs();
		
		File totalFile = new File(mergedDirFile.getAbsolutePath()+"/"+"mergedCsv.csv");
//		OutputStream out = ;
		OutputStreamWriter outs = new OutputStreamWriter(new FileOutputStream(totalFile), OUTPUT_FILE_ENCODE);


		for(File file : fileList){
			if(file.isDirectory()){
				continue;
			}
			fileCounts++;
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int countsInFile=0;
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if(i==0){//认为每个sheet第一行都是标题
					if (rowCounts == 0) {//只填充一个标题
						rowCounts++;
						countsInFile++;
						fillCsvRowCommon(row, outs);
					}else{
						continue;
					}
				}else{
					rowCounts++;
					countsInFile++;
					fillCsvRowCommon(row, outs);
				}
				if(rowCounts%FLUSH_COUNTS==0){
					outs.flush();
				}
			}
			System.out.println("第\t"+fileCounts+"\t个文件合并完成，条数\t：\t"+countsInFile);
		}
		outs.close();
		System.out.println("共合并文件：\t"+fileCounts+"\t个；条数（含标题）：\t"+rowCounts+"\t条；为空的单元枨：\t"+cellSourceNullCounts+"\t个；");
	}

	private static void fillCsvRowCommon(Row row, OutputStreamWriter outs) throws UnsupportedEncodingException, IOException {
		StringBuffer sb = new StringBuffer();
		Iterator<Cell> it = row.cellIterator();
    	while(it.hasNext()){
    		Cell cell = it.next();
    		sb.append(returnCellValue(cell)+",");
    	}
    	sb.append(-rowCounts);
    	outs.write((sb+"\r\n" ));
//    	outs.write( (sb+"\r\n" ).getBytes(INPUT_FILE_ENCODE) );
	}
	//(id,optimistic,batch_code,bank_interface_code,customer_no,identification)
	//2,4
	private static void fillCsvRow4KaYou(Row row, OutputStream out) throws UnsupportedEncodingException, IOException {
		StringBuffer sb = new StringBuffer();
		Iterator<Cell> it = row.cellIterator();
    	while(it.hasNext()){
    		Cell cell = it.next();
    		sb.append(returnCellValue(cell)+",");
    	}
    	sb.append(-rowCounts);
    	
    	out.write( ((
    			returnCellValue(row.getCell(2))+","+returnCellValue(row.getCell(4))
    			)+"\r\n" ).getBytes(INPUT_FILE_ENCODE) );
	}
	private static long cellSourceNullCounts = 0;
	private static Object returnCellValue(Cell cellSource) {
		if(null==cellSource) {
			System.out.println("null"+cellSource);
			cellSourceNullCounts++;
			return "";
		}
		switch (cellSource.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
//			cell2.setCellValue(cell.getcell);
			System.out.println("Cell.CELL_TYPE_BLANK:"+cellSource);
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			return cellSource.getBooleanCellValue();
		case Cell.CELL_TYPE_ERROR:
//			cell2.setCellValue(cell.getBooleanCellValue());
			System.out.println("Cell.CELL_TYPE_ERROR:"+cellSource);
			break;
		case Cell.CELL_TYPE_FORMULA:
			return cellSource.getCellFormula();
		case Cell.CELL_TYPE_NUMERIC:
			return cellSource.getNumericCellValue();
		case Cell.CELL_TYPE_STRING:
			return cellSource.getStringCellValue();
		default:
			break;
		}
		return "";
	}
	 
}
