package bussiness.textbuild;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SQLSearchParamTest {

    public static void main(String[] args) {
    	File file = new File("F://TEMPS/test/input.txt");
        BufferedReader reader = null;
        int countLimitPerLine = 1000;
        try {
            reader = new BufferedReader(new FileReader(file));
            StringBuffer sqlSearchParam = new StringBuffer();
            String tempString = null;
            String value = "bt.pos_sn";
            int line = 0;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
            	String[] values = tempString.split("\t", 0);
            	String param = values[0].toString();	// 为null直接报错

            	if (line % countLimitPerLine == 0) {
            		if (line != 0) {
            			sqlSearchParam.deleteCharAt(sqlSearchParam.length()-1);
            			sqlSearchParam.append( ") \nor ");
            		}
            		sqlSearchParam.append(value + " in \n(");
            	}
            	sqlSearchParam.append("'" + param + "',");

            	line++;
            }
			sqlSearchParam.deleteCharAt(sqlSearchParam.length()-1);
            System.out.println(sqlSearchParam + ")");
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

}