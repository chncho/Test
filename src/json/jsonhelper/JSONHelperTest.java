package json.jsonhelper;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONHelperTest {

	public static void main(String[] args) {
		String jsonStr = "{'productlist': "
								+"["
									+"{'productoid':'11039741'"//商品唯一标识
									+",'productid':'321420010023',"//商品eshang编码
									+"'productname':'Apple iPhone 4 (8G) 行货正品 全新未拆封（黑色）',"//商品名称
									+"'sumprice':'800.0'"//总价
									+",'num':'1'"//数量
									+",'price':'800.0'}"//单价
									+",{'productoid':'222'"
									+",'productid':'222',"
									+"'productname':'BBB',"
									+"'sumprice':'444.0'"
									+",'num':'2'"
									+",'price':'222.0'}"
								+"]"
							+"}";
		jsonStr = "{productlist:" +
				"[" +
					"{\"isbom\":\"0\",\"productcode\":\"320200644010\"," +
						"\"productname\":\"中兴U288-白\",\"price\":\"2000\"," +
						"\"productprice3\":\"1\",\"spcific\":\"15\"," +
						"\"quantity\":\"1\",\"amount\":\"2000\"," +
						"\"memo\":\"中兴U288-白()\"" +
					"}," +
					"{\"isbom\":\"0\",\"productcode\":\"300790035731\"," +
						"\"productname\":\"洛克三星i9152 雅系列侧翻皮套 香橙色\"," +
						"\"price\":\"10\",\"productprice3\":\"2\"," +
						"\"spcific\":\"0\",\"quantity\":\"1\"," +
						"\"amount\":\"10\",\"memo\":\"洛克三星i9152 雅系列侧翻皮套 香橙色\"" +
					"}," +
					"{\"isbom\":\"0\",\"productcode\":\"300800004112\"," +
						"\"productname\":\"非尼膜属手机膜 华为P6 控油王（欣科创）\"," +
						"\"price\":\"20\",\"productprice3\":\"2\"," +
						"\"spcific\":\"0\",\"quantity\":\"1\",\"amount\":\"20\"," +
						"\"memo\":\"非尼膜属手机膜 华为P6 控油王（欣科创）\"}," +
					"{\"isbom\":\"0\",\"productcode\":\"300799996621\"," +
						"\"productname\":\"酷凡移动电源S300（3000mah）（白）(上海博翌)\"," +
						"\"price\":\"50\",\"productprice3\":\"2\",\"spcific\":\"0\"," +
						"\"quantity\":\"1\",\"amount\":\"50\"," +
						"\"memo\":\"酷凡移动电源S300（3000mah）（白）(上海博翌)\"" +
					"}" +
				"]" +
			"} ";
		String[] strarr = {"isbom","productcode","productname",
				"price","productprice3","spcific","quantity",
				"amount","memo"};
		List<ProductEntity> plst = new ArrayList<ProductEntity>();
		ProductEntity pdct = null;
		
		JSONObject jobj = JSONObject.fromObject(jsonStr);
		JSONArray jarr = jobj.getJSONArray("productlist");
		for(Object o : jarr){
			JSONObject j = (JSONObject)o;
			pdct = new ProductEntity();
			pdct.setNum(j.getInt("num"));
			pdct.setPrice(j.getDouble("price"));
			pdct.setProductid(j.getString("productid"));
			pdct.setProductname(j.getString("productname"));
			pdct.setProductoid(j.getString("productoid"));
			pdct.setSumprice(j.getDouble("sumprice"));
			plst.add(pdct);
		}
		
		System.out.println(plst);
	}
}
