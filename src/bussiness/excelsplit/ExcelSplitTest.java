package bussiness.excelsplit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tools.ant.taskdefs.Length.When;

public class ExcelSplitTest {
	 private static int countsPerExcel=2*10000;
	 private static String sourceExcelPath = "D:\\ExcelNoPage.xlsx";
	 
	 public static void main(String[] args) throws IOException, IllegalAccessException, InvocationTargetException, InvalidFormatException {
	        	File fileNew ;
	            FileOutputStream outNew;
	        	Workbook workbookNew;
				Sheet sheetNew ;
				
				
				
				
				
//				sheet2.createFreezePane(0, 1, 0, 1);
				
	        	
//	            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(new FileInputStream(new File(sourceExcelPath)));
	            XSSFWorkbook workbook = new XSSFWorkbook(new File(sourceExcelPath));
	            XSSFSheet sheet = workbook.getSheetAt(0);

	            int pageIndex = 0;
	            long rowCounts = 0;
	           
	            fileNew = new File(sourceExcelPath+"-"+pageIndex+".xlsx");
	        	fileNew.createNewFile();
	            outNew = new FileOutputStream(fileNew);
	        	workbookNew = new SXSSFWorkbook(ExcelUtil.FLUSHNUM);
				sheetNew = workbookNew.createSheet("sheet1");
				
				Row headerRow;
	            for (int k = 1; k <= sheet.getLastRowNum(); k++) {
	            	Row row = sheet.getRow(k);
	            	if(k==1){
	            		headerRow=row;
	            	}
	            	if(rowCounts>0 && rowCounts%10==0){
	            		pageIndex++;
	            		fileNew = new File(sourceExcelPath+"-"+pageIndex+".xlsx");
	    	        	fileNew.createNewFile();
	    	            outNew = new FileOutputStream(fileNew);
	    	        	workbookNew = new SXSSFWorkbook(ExcelUtil.FLUSHNUM);
	    				sheetNew = workbookNew.createSheet("sheet1");
	    				
	    				rowCounts=0;
	    				rowCounts++;//===
		            	Row rowNew = sheetNew.createRow(k);
		            	BeanUtils.copyProperties(rowNew, row);
		            	fillRow(row,rowNew);
	            	}
	            	
	            	rowCounts++;//===
	            	Row rowNew = sheetNew.createRow(k);
	            	BeanUtils.copyProperties(rowNew, row);
	            	
	            	fillRow(row,rowNew);
					
	            }
	            
	            workbookNew.write(outNew);
				outNew.flush();
				outNew.close();
				((SXSSFWorkbook) workbookNew).dispose();
	}

	private static void fillRow(Row row, Row rowNew) {
		Iterator<Cell> it = row.cellIterator();
    	int cellIndex = 0;
    	while(it.hasNext()){
    		Cell cell = it.next();
    		Cell cell2 = rowNew.createCell(cellIndex);
    		
    		fillCell(cell,cell2,cellIndex);

    		cellIndex++;
    		//System.out.println(cellIndex+"-"+cell.getStringCellValue());
    	}
	}

	private static void fillCell(Cell cellSource, Cell cellTaget, int cellIndex) {
		switch (cellSource.getCellType()) {
		case Cell.CELL_TYPE_BLANK:
//			cell2.setCellValue(cell.getcell);
			System.out.println(cellIndex+"-Cell.CELL_TYPE_BLANK:"+cellSource);
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellTaget.setCellValue(cellSource.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_ERROR:
//			cell2.setCellValue(cell.getBooleanCellValue());
			System.out.println(cellIndex+"-Cell.CELL_TYPE_ERROR:"+cellSource);
			break;
		case Cell.CELL_TYPE_FORMULA:
			cellTaget.setCellValue(cellSource.getCellFormula());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			cellTaget.setCellValue(cellSource.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING:
			cellTaget.setCellValue(cellSource.getStringCellValue());
			break;

		default:
			break;
		}
	}

	 
}



