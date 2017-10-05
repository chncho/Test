package bussiness.textparse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parse21ResultTest {
	public static void main(String[] args) throws IOException {
		String errorName = "商户代码不存在";
		File file = new File("F:\\TEMPS\\test\\21号文\\20170623\\ToParse\\bank_error_4.csv");
		if(!file.exists()){
			file.createNewFile();
		}
		OutputStream out = null;
		List<String> headers = new ArrayList<>();
		headers.add("信息");
		headers.add("银联终端");
		List<Object> dataList = new ArrayList<>();
		Map<String, Integer> errormap = new HashMap<String, Integer>();
		BufferedReader reader = null;
		String dir = "F:\\TEMPS\\test\\21号文\\20170623\\ToParse";
		try {
			String[] result = null;
			out  = new FileOutputStream(file);
			File dirFile = new File(dir);
			File[] fileList = dirFile.listFiles();
			int count = 0;
			String sql = "";
			//BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
			//OutputStream os = new FileOutputStream(file);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			StringBuilder sb  = new StringBuilder();
			for(File file2:fileList){
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),"GB2312"));
				String line = null;
				while((line = reader.readLine()) != null){
					if(line.length() > 0){
						
					
					String errorType = test2(line);
					Integer value = errormap.get(errorType);
					if(value == null){
						errormap.put(errorType, 1);
					}else {
						errormap.put(errorType, value + 1);
					}
					
					/*String posSn = test4(line,"填写的收单机构代码与商户实际的收单机构代码不一致");
					if(posSn !=null){						
						System.err.println(posSn);
					}*/
					
					/*String content = test4(line,errorName);
					if(content != null){
						result = new String[2];
						result[0] = content;
						dataList.add(result);
					}*/
					
					/*int count2 = test3(line);
					count += count2;*/
				}
			}
			//os.close();
			//System.out.println(sb.toString());
			//writer.write(sb.toString());
			writer.close();
			System.out.println(errormap);
			System.out.println(count);
			System.out.println("总条数：" + dataList.size());
			/*ExcelUtil.createExcel(new AbstractExcel() {
				@Override
				public void generateValueColum(Map valueStyles, Row row, Object colValues) {
					Cell cell= row.createCell(0);
					//cell.setCellStyle(cell);
					cell.setCellValue(((String[])colValues)[0]);
					
					Cell cell1= row.createCell(1);
					cell1.setCellValue(((String[])colValues)[1]);
				}
				
				@Override
				public List<Object> generateList(List<Object> objectValues, int page,
						int sumPage) {
					return null;
				}
			}, out, headers, dataList);*/
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String test1(String line,String type) {
		if(line.startsWith("原第") && line.contains(type)){
			return line;
		}
		return null;
	}
	
	public static String test4(String line,String type) {
		if(line.startsWith("原第") && line.contains(type)){
			//String posSn = line.split(",")[0].split(":")[1];
			//System.out.println(line);
			//String posSn = line.split(",")[1];
			//System.out.println(posSn);
			//return posSn;
			return line;
		}
		return null;
	}
	
	public static String test2(String line) {
		if(line.startsWith("原第")){
			try {
				String errortype = line.split(",")[14].split("：")[1];
				return errortype;
			} catch (Exception e) {
				e.printStackTrace();
				return "-1";
			}
		}
		return null;
	}
	
	public static int test3(String line) {
		if(line.startsWith("记录总数")){
			String[] arr = line.split("；")[1].split("：")[1].split("条");
			int count2 = Integer.valueOf(arr[0]);
			return count2;
		}
		return 0;
	}
}
