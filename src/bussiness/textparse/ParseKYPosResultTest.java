package bussiness.textparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import professional.fileAndStream.file.FileUtils;
/**
 * 解析金控返回的异常文件，并按错误类型输出到不同文件中
 * 异常文件格式为文本，如：
 *
情况一：
"MERC_ID","C","TRM_NO","D","E","RMK"
"80126900763018A","哈尔滨好年景农业发展有限公司","80256344","赵春雨","23230219820301215X","MCC所属内部商户类型:mcc_sub_id与商户所属内部商户类型:typ_no不一致"
"80126100763049A","林口县东盛玉米种植专业合作社","80256292","李丽","220323198303126320","MCC所属内部商户类型:mcc_sub_id与商户所属内部商户类型:typ_no不一致"
"80149100763076A","滑县四化种植农民专业合作社","80256391","王四化","41072719800730691X","MCC所属内部商户类型:mcc_sub_id与商户所属内部商户类型:typ_no不一致"
"80146100763028A","莒南县朱芦供销合作社农资综合经营部","80256449","孙现花","370822197302154724","MCC所属内部商户类型:mcc_sub_id与商户所属内部商户类型:typ_no不一致"
"80188505331028A","新疆维吾尔族自治区昌吉回族自治州五家渠新凉菜世界","80256377","邓美荣","652301196912255528","商户名称长度应为9-20"
"80160205311864A","珠海市金鼎强记鞋店","80256494","周泽强","440582199109201717","已存在该商户名称"
情况二：
"MERC_ID","UUID","C","TRM_NO","D","E","RMK"
"80155404814031A","5994600603","衡阳市蒸湘区中辉通讯","82419239","刘丹枫","430421198209166133","手机号已存在"
"80165105722025B","5994602563","成华区瑞华青商贸部","82419256","刘川","510922198406022351","已存在该营业执照号"
"80165105331463E","5994602907","成都立智赢科技有限公司","82419258","辜俊","51310119670820997X","手机号已存在"
"80155105977218A","5994601623","长沙市天心区丽之源化妆品","82419248","李思红","430821198811084255","已存在该商户简称"
"80155105311310B","5994611741","长沙绿机源农副产品贸易有限公司","82419274","张会军","431222198810282422","手机号已存在"
"80165105045055A","5994618471","四川省成都市成都市冠维计算机","82419284","李楠","510112198211101838","手机号已存在"
"80155805331239A","5994618621","常德兴成投资管理中心(有限合伙)","82419285","董元珍","430703197105081185","手机号已存在"
 * @author chnch
 *
 */
public class ParseKYPosResultTest {
	private static final int errorTypeIndex = 5;
	private static final String idMode = "index1_4";
//	private static final int errorTypeIndex = 6;
//	private static final String idMode = "index1";
	//列之间的分隔符
	private static final String columnSplitChar = ",";
	//存放异常返回文件的文件夹，其下不能有文件夹，程序会自动扫描文件夹下的所有文件。荐于数据量的原因，未做针对内存的程序优化
	static String feedBackFileDir = "E:\\WORK_LEFU\\合规&检查\\续展相关\\卡友\\大POS批导失败的\\批导失败商户信息(1)\\批导失败商户信息\\nouuid";
	static String feedBackParseResFileDir = "E:\\WORK_LEFU\\合规&检查\\续展相关\\卡友\\大POS批导失败的\\批导失败商户信息(1)\\批导失败商户信息\\nouuid\\res";

	private static final String INPUT_FILE_ENCODE = "UTF-8";
	private static final String OUTPUT_FILE_ENCODE = "UTF-8";
	
	public static void main(String[] args) throws IOException {
//		System.out.println(convertFileName("输入内容非当前可填内容(餐饮类\\一般类\\...)（房车类）"));
		execute();
	}
	private static void execute() throws UnsupportedEncodingException, IOException{

		Map<String, List<String>> errormap = new HashMap<String, List<String>>();
		BufferedReader reader = null;
		File dirFile = new File(feedBackFileDir);
		File[] fileList = dirFile.listFiles();
		
		File mergedDirFile = new File(feedBackParseResFileDir);
		if(mergedDirFile.exists()){
			mergedDirFile.delete();
		}
		mergedDirFile.mkdirs();
		
		int count = 0;
		String headsInfo=null;
		
		for (File file2 : fileList) {
			if(file2.isDirectory()){
				System.out.println("it is a dir\t"+file2.getAbsolutePath());
				continue;
			}
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file2),INPUT_FILE_ENCODE));
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.length() > 0) {
					if(line.contains("MERC_ID")){
						continue;
					}
					String[] lineSplits =  line.split(columnSplitChar);
					String errorType = lineSplits[errorTypeIndex];
					List<String> list = errormap.get(errorType);
					if (list == null) {
						list = new ArrayList<String>();
						errormap.put(errorType, list);
					} 
					list.add(buildResultLine(lineSplits));
				}
			}
			System.out.println(file2.getName()+":headsInfo:"+headsInfo);
			printErrormapStatics(file2.getName(),errormap);
			System.out.println(file2.getName()+":"+count);
		}
	
	}
	//把错误汇总信息打印出来 ，把错误信息明细输出到文件中
	private static void printErrormapStatics(String filename,Map<String, List<String>> errormap) throws UnsupportedEncodingException, IOException {
		Iterator<String> it = errormap.keySet().iterator();
		while(it.hasNext()){
			String errorMsg = it.next();
			List<String> resList = errormap.get(errorMsg);
			long size = resList.size();
			System.out.println(filename+":"+errorMsg+"\t:\t"+size);
			
//			System.out.println("... ... ... "+convertFileName(errorMsg));
			
			File file = new File(feedBackParseResFileDir+"\\"+FileUtils.returnAFileName(errorMsg)+".txt");
			OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file),OUTPUT_FILE_ENCODE);
 			for(String res : resList){
 				String[] res2Arr = res.split(",");
 				if("index1".equals(idMode)){
 					out.write(res2Arr[1].replaceAll("\"", "")+"|"+res);
 				}else if("index1_4".equals(idMode)){
 					out.write(res2Arr[1].replaceAll("\"", "")+"_"+res2Arr[4].replaceAll("\"", "")+"|"+res);
 				}
			}
			
			out.flush();
			out.close();
		}
	}
	
	//从反馈文件中的错误信息中提取出原注册的文件信息来
	private static String buildResultLine(String[] lineSplits) {
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<lineSplits.length; i++){
			sb.append(lineSplits[i]+",");
		}
		
		try {
			return sb.substring(0, sb.length()-1)+"\r\n";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
