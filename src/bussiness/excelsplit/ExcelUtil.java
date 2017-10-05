package bussiness.excelsplit;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.WorkbookUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
/***
 * 需要实现AbstractExcel的generateValueColum， 如果分页的话，还需要实现AbstractExcel的generateList
 *
 * @author f
 *
 */
public class ExcelUtil {
	public static final String CELLSTYLE_DATE = "CellStyleDate";// 日期表格样式
	public static final String CELLSTYLE_DOUBLE = "CellStyleDouble";// 数字表格样式
	public static final int PERSHEETMAX = 1000000;// 单个sheet页最多行数，不含表头
	public static final int FLUSHNUM = 100;// 多少行刷新到磁盘
	public static final String SHEETNAME = "sheet";// 默认的sheet名称

	/***
	 * 不分页导出
	 *
	 * @param util
	 *            实现AbstractExcel接口的具体类
	 * @param out
	 *            输出流
	 * @param headers
	 *            每列表头的名称
	 * @param objectValues
	 *            数据库查出的list对象
	 * @throws Exception
	 */
	public static <T> void createExcel(AbstractExcel util, OutputStream out,
			List<String> headers, List<Object> objectValues) throws Exception {
		createExcel(util, out, headers, objectValues, 1);
	}

	/***
	 * 分页导出
	 *
	 * @param util
	 *            实现AbstractExcel接口的具体类
	 * @param out
	 *            输出流
	 * @param headers
	 *            每列表头的名称
	 * @param objectValues
	 *            数据库查出的list对象
	 * @param sumPage
	 *            总共多少页，分页用
	 * @throws Exception
	 */
	public static <T> void createExcel(AbstractExcel util, OutputStream out,
			List<String> headers, List<Object> objectValues, int sumPage)
			throws Exception {
		// 1,New Workbook
		Workbook wb = new SXSSFWorkbook(ExcelUtil.FLUSHNUM);
		try {
			// 2,New Sheet
			String safeSheetName = WorkbookUtil.createSafeSheetName(SHEETNAME);// 处理不合法字符
			int sheetNo = 1; // 第几页
			Sheet sheet = wb.createSheet(safeSheetName);
			sheet.createFreezePane(0, 1, 0, 1);
			// 3,Creating Cells 表头
			CreationHelper createHelper = wb.getCreationHelper();
			generateExcelHeader(wb, createHelper, headers, sheet);

			// 4,Creating Date Cells 填充数据
			int sheetLength = 0; // 当前sheet中数据条数
			Row row;
			Map styles = new HashMap();
			styles = generateColStyle(wb, createHelper);// 提前设置列的样式
			for (int page = 1; page <= sumPage; page++) {
				if (page >= 2) {
					objectValues.clear();
					System.gc();
					objectValues = util.generateList(objectValues, page,
							sumPage);
				}
				Iterator it = objectValues.iterator();
				while (it.hasNext()) {
					// 加入超过单个Sheet的最大长度，则新生成一个新的sheet页
					// 2003单个sheet最大 65535，2007，一百万
					if (sheetLength >= PERSHEETMAX) {
						sheetLength = 0;
						sheet = wb.createSheet(SHEETNAME + "-" + sheetNo++);
						generateExcelHeader(wb, createHelper, headers, sheet);
					}
					sheetLength++;
					row = sheet.createRow(sheetLength);// 创建一行
					util.generateValueColum(styles, row, it.next());
					if (objectValues instanceof LinkedList)
						it.remove();
				}
			}

			wb.write(out);
			out.flush();

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			out.close();
			((SXSSFWorkbook) wb).dispose();
		}

	}

	private static Map generateColStyle(Workbook wb, CreationHelper createHelper) {
		Map styles = new HashMap();
		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
				"m/d/yy h:mm"));
		((Map) styles).put(ExcelUtil.CELLSTYLE_DATE, cellStyle);
		cellStyle = wb.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
				"#,##0.00"));
		((Map) styles).put(ExcelUtil.CELLSTYLE_DOUBLE, cellStyle);
		return styles;
	}

	private static void generateExcelHeader(Workbook wb,
			CreationHelper createHelper, List<String> headers, Sheet sheet)
			throws Exception {
		if (headers != null && headers.size() > 0) {
			Row row = sheet.createRow(0);
			// 列的话，一般几十行就够了，不用考虑对象过多
			CellStyle cellStyle = wb.createCellStyle();
			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 12);
			font.setFontName("Courier New");
			font.setItalic(true);
			font.setBold(true);
			cellStyle.setFont(font);
			cellStyle.setDataFormat(createHelper.createDataFormat().getFormat(
					"General"));

			for (int i = 0; i < headers.size(); i++) {
				Cell cell = row.createCell(i);// 创建一列
				cell.setCellValue(headers.get(i));// 默认按字符串处理
				cell.setCellStyle(cellStyle);// 走默认样式
			}
		} else {
			throw new Exception("表头不可为空");
		}

	}

}

