package bussiness.textbuild;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SQLInsertTest {

//    private static final String SPLIT_CHAR = ",";
    private static final String SPLIT_CHAR = "\t";

	public static void main(String[] args) {
//    	String str1 = ",11,22,33";
//    	String str2 = "11,22,33,";
//    	String str3 = ",11,22,33,";
//    	System.out.println(Arrays.toString(str1.split(","))+">>"+str1.split(",").length);
//    	System.out.println(Arrays.toString(str2.split(","))+">>"+str2.split(",").length);
//    	System.out.println(Arrays.toString(str3.split(","))+">>"+str3.split(",").length);
//    	
    	produceSql();
	}
    private static void produceSql(){
    	File file = new File("F:\\TEMPS\\test\\input.txt");
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            StringBuffer sqlSearchParam = new StringBuffer();
            String tempString = null;
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
//            	String[] values = tempString.split("\t", 0);
//            	String param = values[0].toString();	// 为null直接报错
            	
            	appendSql(sqlSearchParam,tempString);

            	line++;
            }
			sqlSearchParam.deleteCharAt(sqlSearchParam.length()-1);
            System.out.println(sqlSearchParam + "");
            System.out.println("总计：" + line + "个。");
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
		} finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

//	private static void appendSql(StringBuffer sqlSearchParam, String param) {
//		String[] colsInRow = param.split(",");
//		
//		sqlSearchParam.append("insert into boss_check.temp_for_check c (c.batch_code,c.a,c.b) "
//    			+ "values ('20170228heguiY16OPEN','"+colsInRow[0]+"','');\n");
//	}

	private static void appendSql(StringBuffer sqlSearchParam, String param) {
		String[] colsInRow = param.split(SPLIT_CHAR);
		
//		sqlSearchParam.append("delete from posp_boss.thirdparty_settle_info where customer_no='"+colsInRow[0]+"' and card_no<>'"+colsInRow[1]+"'");
//		sqlSearchParam.append("delete from posp_boss.thirdparty_settle_info "
//				+ " where customer_no='"+colsInRow[0]+"' and card_no<>'"+colsInRow[1]+"' "
////						+ "and lbank_no<>'"+colsInRow[4]+"'"
//								+ ";\r\n");
//		sqlSearchParam.append("select \'"+colsInRow[0]+"\' a,"+colsInRow[1]+" b from dual union all \r\n");
		
		sqlSearchParam.append(
				"update posp_boss.bank_terminal bt set bt.pos_tusn='"+colsInRow[3]+"',bt.optimistic=bt.optimistic+1,bt.submit_sign_time=sysdate"
				+"where bt.bank_customer_no='"+colsInRow[0]+"' and bt.bank_cati='"+colsInRow[1]+"' and bt.pos_sn='"+colsInRow[2]+"'"
				+"\r\n");
	}

}