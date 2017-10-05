package bussiness.excelsplit;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;

public interface AbstractExcel {
	/***
	 *
	 * @param valueStyles 返回了日期和数字的样式，key分别为CELLSTYLE_DATE，CELLSTYLE_DOUBLE
	 * @param row  excel行对象
	 * @param colValues 某一行需要导出的数据
	 */
	public void generateValueColum( Map valueStyles, Row row,
			Object colValues);
	/***
	 * 返回第几页要导出的excel
	 * @param page 第几页
	 * @param sumPage 调用excel导出时传入的总页数
	 * @return
	 */
	public abstract List<Object> generateList(List<Object> objectValues,int page,int sumPage);
}
