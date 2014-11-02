package professional.lbs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import professional.fileAndStream.csv.CSVUtils;

import net.httpRequest.HttpRequest;
import net.sf.json.JSONObject;

public class BaiduLbsTest {

	public static void main(String[] args) {
//		GeocodingAPIv2_0Test();
		GeocodingAPIv2_0TestFillProvCitySti();
//		System.out.println("\"\"".replaceAll("\"", ""));
	}
	
	public static void GeocodingAPIv2_0TestFillProvCitySti(){
		List<String> listOut = new ArrayList<String>();
		
		List<String> dataList=CSVUtils.importCsv(new File("D:/test/ljq_out.csv"));
        if(dataList!=null && !dataList.isEmpty()){
        	int i=0;//进度
            for(String data : dataList){
            	String[] addr = data.split(",");
            	String[] loc = reqGeocodingAPIv2_0TestFillProvCitySti(addr[1].replaceAll("\"", ""),addr[0].replaceAll("\"", ""));
                listOut.add( "\""+loc[0]+"\""+
                			",\""+loc[1]+"\"" +
                			",\""+loc[2]+"\"" +
                			",\""+loc[3]+"\"" +
                			",\""+loc[4]+"\"" +
                			",\""+loc[5]+"\"" +
                			","+data);
                System.out.println("..."+i);//进度
                i++;
            }
            
            boolean isSuccess=CSVUtils.exportCsv(new File("D:/test/ljq_out_fill.csv"), listOut);
            System.out.println(isSuccess);
        }
	}
	public static String[] reqGeocodingAPIv2_0TestFillProvCitySti(String loc1,String loc2){
		String url = 
				"http://api.map.baidu.com/geocoder/v2/";
		String param =
				"ak=ZRfZxRCOpHQoAM7M7Vrt5ias" +
				"&callback=renderReverse" +
				"&location="+loc1+","+loc2 +
				"&output=json&pois=0" ;
				;
		String resp;
		try {
			resp = HttpRequest.sendGet(url, param);
			String jsonstr = resp.substring(resp.indexOf("renderReverse&&renderReverse(")+"renderReverse&&renderReverse(".length(),
					resp.length()-1);
			return parseGeocodingAPIv2_0TestFillProvCitySti(jsonstr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String[6];
	}
	
	private static String[] parseGeocodingAPIv2_0TestFillProvCitySti(String jsonstr){
		String[] res = new String[6]; 
		try{
			JSONObject jobj = JSONObject.fromObject(jsonstr);
			if(0==jobj.getInt("status")){
				res[0] = jobj.getJSONObject("result").getJSONObject("addressComponent").
						getString("province");
				res[1] = jobj.getJSONObject("result").getJSONObject("addressComponent").
						getString("city");
				res[2] = jobj.getJSONObject("result").getJSONObject("addressComponent").
						getString("district");
				res[3] = jobj.getJSONObject("result").getJSONObject("addressComponent").
						getString("street");
				res[4] = jobj.getJSONObject("result").getJSONObject("addressComponent").
						getString("street_number");
				res[5] = jobj.getJSONObject("result").getString("cityCode");
			}
		}catch(Exception e){
			System.out.println("jsonstr..."+jsonstr);
			e.printStackTrace();
		}
		return res;
	}
	
	//==========================================================
	/**
	 * 
	 * 功能描述：
	 *
	 *
	 * @author 陈超
	 *
	 * @since 2014-8-4
	 *
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static void GeocodingAPIv2_0Test(){
		List<String> listOut = new ArrayList<String>();
		
		List<String> dataList=CSVUtils.importCsv(new File("D:/test/ljq.csv"));
        if(dataList!=null && !dataList.isEmpty()){
        	int i=0;//进度
            for(String data : dataList){
            	String addr = data.split(",")[1];
            	double[] loc = reqGeocodingAPIv2_0Test(addr);
                listOut.add("\""+loc[0]+"\",\""+loc[1]+"\","+data);
                System.out.println("..."+i);//进度
                i++;
            }
            
            boolean isSuccess=CSVUtils.exportCsv(new File("D:/test/ljq_out.csv"), listOut);
            System.out.println(isSuccess);
        }
		
	}
	public static double[] reqGeocodingAPIv2_0Test(String addr){
		String url = 
				"http://api.map.baidu.com/geocoder/v2/" ;
		String param =
				"address="+addr+"&" +
				"output=json&" +
				"ak=ZRfZxRCOpHQoAM7M7Vrt5ias&" +
				"callback=showLocation"
				;
		String resp;
		try {
			resp = HttpRequest.sendGet(url, param);
			String jsonstr = resp.substring(resp.indexOf("showLocation&&showLocation(")+"showLocation&&showLocation(".length(),
					resp.length()-1);
			return parseGeocodingAPIv2_0Test(jsonstr);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new double[2];
	}
	
	private static double[] parseGeocodingAPIv2_0Test(String jsonstr){
		double[] res = new double[2]; 
		try{
			JSONObject jobj = JSONObject.fromObject(jsonstr);
			if(0==jobj.getInt("status")){
				res[0] = jobj.getJSONObject("result").getJSONObject("location").
						getDouble("lng");
				res[1] = jobj.getJSONObject("result").getJSONObject("location").
						getDouble("lat");
			}
		}catch(Exception e){
			System.out.println("jsonstr..."+jsonstr);
			e.printStackTrace();
		}
		return res;
	}
	
}
