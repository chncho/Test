package professional.fileAndStream.csv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * CSV操作(导出和导入)
 * 
 * @author 林计钦
 * @version 1.0 Jan 27, 2014 4:17:02 PM
 */
public class CsvTest {
	
	public static void main(String[] args) {
//		new CsvTest().exportCsv();
		new CsvTest().importCsv();
	}
	

    /**
     * CSV导出
     * 
     * @throws Exception
     */
    @Test
    public void exportCsv() {
        List<String> dataList=new ArrayList<String>();
        dataList.add("\"1\",\"张三\",\"男\"");
        dataList.add("\"2\",\"李四\",\"男\"");
        dataList.add("\"3\",\"小红\",\"女\"");
        boolean isSuccess=CSVUtils.exportCsv(new File("D:/test/ljq.csv"), dataList,"UTF-8");
        System.out.println(isSuccess);
    }
    
    /**
     * CSV导出
     * 
     * @throws Exception
     */
    @Test
    public void importCsv()  {
        List<String> dataList=CSVUtils.importCsv(new File("D:/test/ljq.csv"),"UTF-8");
        if(dataList!=null && !dataList.isEmpty()){
            for(String data : dataList){
                //System.out.println(data);
                System.out.println(data.split(",")[1]);
            }
        }
    }
    
    
}