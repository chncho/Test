package json.jsonhelper;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 解析json
 * 
 *
 * @author 陈超
 *
 * @version $Revision$
 *
 * @since 2013-6-25
 */
public class JSONParser {
	
	
	
	public static void main(String[] args) {
		String jsonstr= 
			"{\"status\":0," +
			 "\"result\":" +
					"{\"location\":" +
							"{\"lng\":119.04111775458,\"lat\":33.606120502435}," +
						"\"precise\":1," +
						"\"confidence\":80," +
						"\"level\":\"\u9053\u8def\"" +
					"}" +
			"}";
		JSONObject jobj = JSONObject.fromObject(jsonstr);
		if(0==jobj.getInt("status")){
			double lng = jobj.getJSONObject("result").getJSONObject("location").getDouble("lng");
			double lat = jobj.getJSONObject("result").getJSONObject("location").getDouble("lat");
			System.out.println(lng+"_"+lat);
		}
	}
	

	public static List<Object> parseLstBean(String jsonStr,Object objen){
		List<Object> lst = new ArrayList<Object>();
		
		JSONObject jobj = JSONObject.fromObject(jsonStr);
		JSONArray jarr = jobj.getJSONArray("productlist");
		System.out.println(jobj);
		System.out.println(jarr);
		for(Object o : jarr){
			JSONObject j = (JSONObject)o;
			System.out.println(j);
			System.out.println(j.getInt("num"));
		}
		
		return lst;
	}
}
