package professional.dataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Tree3onetime {

	public static void main(String[] args) {
		tree2Maptest();
		
//		System.out.println(8957.0/(60*60));
	}
	
	
	public static void tree3test(){
		List<String> list = new ArrayList<String>();

		list.add("g1,p1,s1");
		list.add("g1,p1,s2");
		list.add("g1,p2,s3");
		list.add("g1,p2,s4");
		
		list.add("g2,p3,s5");
		list.add("g2,p3,s6");
		list.add("g2,p3,s7");
		list.add("g2,p4,s8");
		
		Map<String,Map<String,List<String>>> provmap = new HashMap<String,Map<String,List<String>>>(); 
		Map<String,List<String>> citymap = null;//= new HashMap<String,List<String>>();
		List<String> distlst = null;
		String lastcity = "";
		String lastprov = "";
		
		Iterator<String> it = list.iterator();
		
		while(it.hasNext()){
			String[] arr = it.next().split(",");
			System.out.println(Arrays.toString(arr));
			
			if(!lastprov.equals(arr[0])){//新PROV，初始化新的CITY，添加到PROV MAP中
				lastprov = arr[0];
				citymap = new HashMap<String,List<String>>();
				provmap.put(lastprov, citymap);
			}
			if(!lastcity.equals(arr[1])){//新CITY，初始化新的List，添加到CITY MAP中
				lastcity = arr[1];
				distlst = new ArrayList<String>();
				citymap.put(lastcity, distlst);
			}
			
			
			distlst.add(arr[2]);
		}

		System.out.println(provmap);
		//===========================
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization()  
                .create();
		String jsonStr = gson.toJson(provmap);
		System.out.println(jsonStr);
	}
	
	public static void tree2test(){
		List<String> list = new ArrayList<String>();

		list.add("g1,p1,s1");
		list.add("g1,p2,s2");
		list.add("g1,p3,s3");
		list.add("g1,p4,s4");
		
		list.add("g2,p5,s5");
		list.add("g2,p6,s6");
		list.add("g2,p7,s7");
		list.add("g2,p8,s8");
		
		List<List<List<String>>> baselst = new LinkedList<List<List<String>>>(); 
		List linelst = null;
		List<String> detaillst = null;
		String lastlineid = "";
		
		Iterator<String> it = list.iterator();
		
		while(it.hasNext()){
			String[] arr = it.next().split(",");
			System.out.println(Arrays.toString(arr));
			
			if(!lastlineid.equals(arr[0])){//新PROV，初始化新的CITY，添加到PROV MAP中
				lastlineid = arr[0];
				linelst = new LinkedList<List<String>>();
				baselst.add( linelst);
				linelst.add(arr[0]);
				detaillst = new LinkedList<String>();
				linelst.add(detaillst);
			}
			
			
			detaillst.add(arr[2]);
		}

		System.out.println(baselst);
		//===========================
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization()  
                .create();
		String jsonStr = gson.toJson(baselst);
		System.out.println(jsonStr);
	}
	
	public static void tree2Maptest(){
		List<String> list = new ArrayList<String>();

		list.add("g1,p1,s1");
		list.add("g1,p2,s2");
		list.add("g1,p3,s3");
		list.add("g1,p4,s4");
		
		list.add("g2,p5,s5");
		list.add("g2,p6,s6");
		list.add("g2,p7,s7");
		list.add("g2,p8,s8");
		
		List<Map> baselst = new LinkedList(); 
		Map lineMap = null;
		List<Map> detaillst = null;
		Map detailMap = null;
		String lastlineid = "";
		
		Iterator<String> it = list.iterator();
		
		while(it.hasNext()){
			String[] arr = it.next().split(",");
			System.out.println(Arrays.toString(arr));
			
			if(!lastlineid.equals(arr[0])){//新PROV，初始化新的CITY，添加到PROV MAP中
				lastlineid = arr[0];
				lineMap = new HashMap();
				baselst.add( lineMap);
				lineMap.put("linemapkey",arr[0]);
				detaillst = new LinkedList<Map>();
				lineMap.put("detaillst",detaillst);
			}
			
			detailMap = new HashMap();
			detailMap.put("detailitems1", arr[1]);
			detailMap.put("detailitems2", arr[2]);
			detaillst.add(detailMap);
		}

		System.out.println(baselst);
		//===========================
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization()  
                .create();
		String jsonStr = gson.toJson(baselst);
		System.out.println(jsonStr);
	}
}
