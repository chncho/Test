package json.jsonhelper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*******************************************************************************
 * JSON工具类
 * 
 * @author cobra
 * @see com.hysoft.commons.xml.JSONHelper
 * @since jdk1.6
 * @version version1.0
 * @date 2010/09/03
 * 
 ******************************************************************************/
public class JSONHelper {

	/**
	 *
	 * @param list
	 * @return 
	 *		返回解析过后的JSON格式例如[["abc","edf","xyz"],["abc","edf","xyz"],["abc","edf","xyz"]]
	 * @author 陈超
	 * @since Mar 12, 2013 12:07:38 PM
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getJSONArrayByListList(List<List<Object>> list) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for (int i = 0; i < list.size(); i++) {
			buffer.append("\"").append(
					list.get(i) != null ? getJSONArrayByList(list.get(i)) : "").append(
					"\",");
		}
		return buffer.substring(0, buffer.length() - 1).concat("]");
	}
	/**
	 *
	 * @param list
	 * @return 
	 *		返回解析过后的JSON格式例如["abc","edf","xyz"]
	 * @author 陈超
	 * @since Mar 12, 2013 12:07:38 PM
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getJSONArrayByList(List<Object> list) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for (int i = 0; i < list.size(); i++) {
			buffer.append("\"").append(
					list.get(i) != null ? list.get(i).toString() : "").append(
					"\",");
		}
		return buffer.substring(0, buffer.length() - 1).concat("]");
	}
	/**
	 * 
	 * @param list
	 * @return 返回解析过后的JSON格式例如[{"abc":"abc","edf":"abc","xyz":"abc"}]
	 * @author 陈超
	 * @since Apr 1, 2013 1:04:06 PM
	 * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
	 */
	public static String getJSONByListM(List<Map<String,Object>> list) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		for (int i = 0; i < list.size(); i++) {
			buffer.append("").append(
					list.get(i) != null ? exeGetJSONByMap(list.get(i)) : "").append(
					",");
		}
		return buffer.substring(0, buffer.length() - 1).concat("]");
	}
	/**
	 * 传入一个简单的List经过解析返回JSON
	 * 
	 * @param list
	 *            传入参数list
	 * 
	 * @return String 返回解析过后的JSON格式例如[{"abc"},{"edf"},{"xyz"}]
	 */
	public static String getJSONByList(List<Map<String,Object>> list) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[{");
		for (int i = 0; i < list.size(); i++) {
			buffer.append("").append(
					list.get(i) != null ? list.get(i).toString() : "").append(
					",");
		}
		return buffer.substring(0, buffer.length() - 1).concat("}]");
	}

	/**
	 * 传入一个Map经过解析返回JSON
	 * 
	 * @param map
	 *            传入参数map
	 * 
	 * @return String 返回解析过后的JSON格式例如{"age","24","name","ty"}
	 */
	@SuppressWarnings("unchecked")
	public static String getJSONByMap(Map<String, Object> map) {
		return exeGetJSONByMap(map);
	}
	/**
	 * 传入一个Map经过解析返回JSON
	 * 
	 * @param map
	 *            传入参数map
	 * 
	 * @return String 返回解析过后的JSON格式例如[{"age","24","name","ty"}]
	 */
	@SuppressWarnings("unchecked")
	private static String exeGetJSONByMap(Map<String, Object> map) {
		Iterator<?> iter = map.entrySet().iterator();
		StringBuffer buffer = new StringBuffer();
		buffer.append("{");
		while (iter.hasNext()) {
			Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iter
					.next();
			buffer.append("\"").append(
					entry.getKey() != null ? entry.getKey() : "").append("\":")
					.append("\"").append(
							entry.getValue() != null ? entry.getValue() : "")
					.append("\",");
		}
		return buffer.substring(0, buffer.length() - 1).concat("}");
	}

	/**
	 * 传入一个List经过解析返回JSON
	 * 
	 * @param list
	 *            传入参数list
	 * 
	 * @return String
	 *         返回解析过后的JSON格式例如[{"age","24","name","ty"},{"age","20","name","tt"}]
	 */
	public static String getJSONByBeanList(List<?> list) {
		if(null==list||list.size()==0){
			return "[]";
		}
		StringBuffer buffer = new StringBuffer();
		try {
			Class<?> clazz = list.get(0).getClass();
			Field fields[] = clazz.getDeclaredFields();
			buffer.append("[");
			for (int i = 0; i < list.size(); i++) {
				buffer.append("{");
				for (int j = 0; j < fields.length; j++) {
					String fieldName = fields[j].getName();
					String firstLetter = fieldName.substring(0, 1)
							.toUpperCase(); // 获得和属性对应的getXXX()方法的名字
					String getMethodName = "get" + firstLetter
							+ fieldName.substring(1); // 获得和属性对应的getXXX()方法的名字
					Method getMethod = clazz.getMethod(getMethodName,
							new Class[] {}); // 获得和属性对应的getXXX()方法
					Object value = getMethod.invoke(list.get(i),
							new Object[] {});
					buffer.append("\"" + fieldName + "\":\"" + (null==value? "":value) + "\",");
				}
				buffer = new StringBuffer(buffer.substring(0,
						buffer.length() - 1));
				buffer.append("},");
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return buffer.substring(0, buffer.length() - 1).concat("]");
	}
	
	/**
	 * 传入一个List经过解析返回JSON
	 * 
	 * @param list
	 *            传入参数list
	 * @param fieldName
	 *            属性名，首字母大写
	 * @return String
	 *         返回解析过后的JSON格式例如[{"age","24","name","ty"},{"age","20","name","tt"}]
	 * @author 陈超
	 */
	public static String getJSONByBeanList(List<?> list,String[] fieldNames) {
		if(null==list||list.size()==0){
			return "[]";
		}
		StringBuffer buffer = new StringBuffer();
		try {
			Class<?> clazz = list.get(0).getClass();
			buffer.append("[");
			for (int i = 0; i < list.size(); i++) {
				buffer.append("{");
				for (String fieldName : fieldNames) {
					Method getMethod = clazz.getMethod("get"+fieldName.substring(0, 1)
							.toUpperCase()+fieldName.substring(1)); // 获得和属性对应的getXXX()方法
					Object value = getMethod.invoke(list.get(i));
					buffer.append("\"" + fieldName + "\":\"" + (null==value? "":value) + "\",");
				}
				buffer = new StringBuffer(buffer.substring(0,
						buffer.length() - 1));
				buffer.append("},");
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return buffer.substring(0, buffer.length() - 1).concat("]");
	}

	/**
	 * 传入一个Bean经过解析返回JSON
	 * 
	 * @param object
	 *            传入参数Bean
	 * 
	 * @return String 返回解析过后的JSON格式例如[{"age","24","name","ty"}]
	 */
	public static String getJSONByBean(Object object) {
		StringBuffer buffer = new StringBuffer();
		try {
			Class<?> clazz = object.getClass();
			Field fields[] = clazz.getDeclaredFields();
			buffer.append("[{");
			for (int j = 0; j < fields.length; j++) {
				String fieldName = fields[j].getName();
				String firstLetter = fieldName.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter
						+ fieldName.substring(1);
				Method getMethod = clazz.getMethod(getMethodName,
						new Class[] {});
				Object value = getMethod.invoke(object, new Object[] {});
				buffer.append("\"" + fieldName + "\":\"" + value + "\",");
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return buffer.substring(0, buffer.length() - 1).concat("}]");
	}

	public static String getXMLByJSON(Object object) {
		return null;
	}

	public static void main(String[] args) {
	 	new JSONHelper().testGetJsonByList();
	}
	private void testGetJsonByList(){
		List<List<Object>> listlist = new ArrayList<List<Object>>();
		List<Object> list = new ArrayList<Object>();
		list.add("a");
		list.add("b");
		listlist.add(list);
		list.add("aa");
		list.add("bb");
		listlist.add(list);
		System.out.println(getJSONArrayByList(list));
		System.out.println(getJSONArrayByListList(listlist));
	}
	
}
