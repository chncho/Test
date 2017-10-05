package bussiness.excelsplit;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

public class AbstractExcelTest  implements AbstractExcel {

	private double doub;
	 private Date dat;
	 private String str;
	 
	 public static void main(String[] args) throws FileNotFoundException, Exception {
		 new AbstractExcelTest().testExcelNoPage();
		 //new AbstractExcelTest().testExcelPage();

	}
	 public void testExcelNoPage() throws FileNotFoundException, Exception{
		 List<String> heards = new ArrayList<String>();
		 heards.add("表头1");
		 heards.add("表头2");
		 heards.add("表头3");
			List<Object> tests = new LinkedList<Object>();
			//模拟数据库导出数据
			AbstractExcelTest abstractExcelTest;
			for (int i = 0; i < 300; i++) {
				abstractExcelTest = new AbstractExcelTest();
				abstractExcelTest.setDat(new Date());
				abstractExcelTest.setDoub(new Double("123.456"));
				abstractExcelTest.setStr("我是String");
				tests.add(abstractExcelTest);
			}
			ExcelUtil.createExcel(new AbstractExcelTest(), new FileOutputStream(
					"D:\\ExcelNoPage.xlsx"), heards,
					tests);
	 }
	 public void testExcelPage() throws FileNotFoundException, Exception{
		 List<String> heards = new ArrayList<String>();
		 heards.add("表头1");
		 heards.add("表头2");
		 heards.add("表头3");
			List<Object> tests = new LinkedList<Object>();
			//模拟数据库导出数据
			AbstractExcelTest abstractExcelTest;
			for (int i = 0; i < 30000; i++) {
				abstractExcelTest = new AbstractExcelTest();
				abstractExcelTest.setDat(new Date());
				abstractExcelTest.setDoub(new Double("123.456"));
				tests.add(abstractExcelTest);
			}
			ExcelUtil.createExcel(new AbstractExcelTest(), new FileOutputStream(
					"D:\\ExcelPage.xlsx"), heards,
					tests,11);
	 }

	@Override
	public void generateValueColum(
			Map valueStyles, Row row, Object colValues) {
		// TODO Auto-generated method stub
		Cell cell= row.createCell(0);// 创建一列
//		如果有设置valueStyles，注意列的数据类型和解析对象colValues时一致
		cell.setCellValue(((AbstractExcelTest)colValues).getDoub());
		cell.setCellStyle((CellStyle) valueStyles.get(ExcelUtil.CELLSTYLE_DOUBLE));
		cell= row.createCell(1);
		cell.setCellValue(((AbstractExcelTest)colValues).getDat());
		cell.setCellStyle((CellStyle) valueStyles.get(ExcelUtil.CELLSTYLE_DATE));
		cell= row.createCell(2);
		cell.setCellValue(((AbstractExcelTest)colValues).getStr());
	}
	@Override
	public List<Object> generateList(List<Object> objectValues,int page,int sumPage) {
		System.out.println(new Date().toLocaleString()+"...in page:"+page);
		//应该知道自己每页导出了多少条数据，不再回传了
		if(page <= sumPage-1){
			//TODO 调用方实现调用数据库分页的逻辑
			AbstractExcelTest abstractExcelTest;
			for(int i=0;i<30000;i++){
				abstractExcelTest = new AbstractExcelTest();
				abstractExcelTest.setDat(new Date());
				abstractExcelTest.setDoub(new Double("123.456"));
				abstractExcelTest.setStr("我是String");
				objectValues.add(abstractExcelTest);
			}
		}else if(page == sumPage){
			AbstractExcelTest abstractExcelTest;
			for(int i=0;i<1000;i++){
				abstractExcelTest = new AbstractExcelTest();
				abstractExcelTest.setDat(new Date());
				abstractExcelTest.setDoub(new Double("123.456"));
				abstractExcelTest.setStr("我是String");
				objectValues.add(abstractExcelTest);
			}
		}
		return objectValues;
	}

	public double getDoub() {
		return doub;
	}

	public void setDoub(double doub) {
		this.doub = doub;
	}

	public Date getDat() {
		return dat;
	}

	public void setDat(Date dat) {
		this.dat = dat;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}


}
