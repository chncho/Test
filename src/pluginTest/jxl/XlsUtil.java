package pluginTest.jxl;


import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class XlsUtil {
	    private Workbook readBook;  
	  
	    private int currIndex;  
	  
	    private List<String> alLineContent;  
	  
	    private Cell cell;  
	  
	    private int firstDataCount;  
	  
	    private boolean isFirst;  
	  
	    private Sheet sheet;  
	  
	    private WritableWorkbook writeBook;  
	  
	    private WritableSheet writeSheet;  
	  
	    private Label label;  
	  
	    /** 
	     * 初始化xls文件的读取参数 
	     *  
	     * @param filePath 
	     *            xls文件路径 
	     *  
	     */  
	    public void read(String filePath) throws Exception {  
	        try {  
	            // 创建xls工作表对象  
	            readBook = Workbook.getWorkbook(new File(filePath));  
	            // 只读取第一个工作表中的内容  
	            sheet = readBook.getSheet(0);  
	            currIndex = 0;  
	            isFirst = true;  
	        } catch (Exception e) {  
	            throw new Exception(e);  
	        }  
	    }  
	  
	    /** 
	     *  
	     * @param is 
	     *            初始化xls文件的读取参数 
	     * @throws Exception 
	     */  
	  
	    public void read(InputStream is) throws Exception {  
	        try {  
	            // 创建xls工作表对象  
	            readBook = Workbook.getWorkbook(is);  
	            // 只读取第一个工作表中的内容  
	            sheet = readBook.getSheet(0);  
	            currIndex = 0;  
	            isFirst = true;  
	        } catch (Exception e) {  
	            throw new Exception(e);  
	        }  
	    }  
	  
	    /** 
	     * 读取一行xls文件中的数据 
	     *  
	     * @return 包含数据的String列表 
	     */  
	    public List<String> readLine() {  
	        alLineContent = new ArrayList<String>();
	        int i = 0;  
	        String content = null;  
	        while (true) {  
	            if (!isFirst && i >= firstDataCount)  
	                break;  
	            try {  
	                // 读取一个单元格的数据  
	                cell = sheet.getCell(i, currIndex);  
	                i++;  
	            } catch (Exception e) {  
	                // 没有数据可读取  
	                if (i == 0)  
	                    return null;  
	                // 读取首行  
	                if (isFirst) {  
	                    firstDataCount = i;  
	                    isFirst = false;  
	                    break;  
	                } else  
	                    content = "";  
	            }  
	            content = cell.getContents();  
	            // 首行存在空值时认为提取数据完毕  
	            if (isFirst && "".equals(content)) {  
	                firstDataCount = i - 1;  
	                isFirst = false;  
	                break;  
	            }  
	            alLineContent.add(content);  
	        }  
	        currIndex++;  
	        return alLineContent;  
	    }  
	  
	    /** 
	     * 读取xls文件中的所有可读取数据 
	     */  
	    public List<List<String>> readAll() {  
	    	List<List<String>> alAllData = new ArrayList<List<String>>();  
	    	List<String> data = null;  
	        while (true) {  
	            data = this.readLine();  
	            if (data == null)  
	                break;  
	            alAllData.add(data);  
	        }  
	        return alAllData;  
	    }  
	  
	    public void closeRead() {  
	        readBook.close();  
	    }  
	  
	    /** 
	     * 创建一个xls文件并初始化写入参数 
	     *  
	     * @param filePath 
	     *            xls文件路径 
	     */  
	    public void write(String filePath) throws Exception {  
	        try {  
	            // 打开.xls文件  
	            writeBook = Workbook.createWorkbook(new File(filePath));  
	            // 创建一个工作表  
	            writeSheet = writeBook.createSheet("Sheet1", 0);  
	            currIndex = 0;  
	        } catch (Exception e) {  
	            throw new Exception(e);  
	        }  
	    }  
	  
	    /** 
	     * 将一条数据写入xls文件中 
	     *  
	     * @param dataLine 
	     *            需要写入的数据集合 
	     */  
	    public void writeLine(ArrayList<String> dataLine) throws Exception {  
	        try {  
	            for (int i = 0; i < dataLine.size(); i++) {  
	                label = new Label(i, currIndex, dataLine.get(i));  
	                writeSheet.addCell(label);  
	            }  
	            currIndex++;  
	        } catch (Exception e) {  
	            throw new Exception(e);  
	        }  
	    }  
	  
	    /** 
	     * 将所有数据写入xls文件 
	     *  
	     * @param data需要写入的数据 
	     */  
	    public void writeAll(ArrayList<ArrayList<String>> data) throws Exception {  
	        for (int i = 0; i < data.size(); i++) {  
	            this.writeLine(data.get(i));  
	        }  
	    }  
	  
	    public void closeWrite() throws Exception {  
	        try {  
	            // 将值写到文件中  
	            writeBook.write();  
	            writeBook.close();  
	        } catch (Exception e) {  
	            throw new Exception(e);  
	        }  
	    }  
}
