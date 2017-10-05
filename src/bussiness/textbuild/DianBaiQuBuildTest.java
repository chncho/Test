package bussiness.textbuild;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class DianBaiQuBuildTest {
	  private  static  String  sourceExcelFolder  =  "E:\\WORK_LEFU\\合规&检查\\续展相关\\点佰趣\\exp20170713gzt_dbq";
	  public  static  void  main(String[]  args)  throws  IOException  {
	  File  fileNew  ;
	  BufferedWriter  writter  =  null;
	  BufferedReader  reader  =  null;
	  HashSet  ids  =  new  HashSet();
	  File  dirFile  =  new  File(sourceExcelFolder);
	  File[]  fileList  =  dirFile.listFiles();
	  int  fileNum  =  1;
	  for  (File  file2  :  fileList)  {
	  DataInputStream  in=new  DataInputStream(new  FileInputStream(file2));
	  reader  =  new  BufferedReader(new  InputStreamReader(in,"UTF-8"));
	  fileNew  =  new  File(sourceExcelFolder+"/"+fileNum+"_customer.txt");
	  fileNew.createNewFile();
	  writter  =new  BufferedWriter(new  OutputStreamWriter(new  FileOutputStream(fileNew),  "GBK"))  ;
	                          String  line  =  null;
	                          long  lineCounts  =  0;
	                          try  {        
	                                    reader.readLine();
	                                    while((line=reader.readLine())!=null){
	                                  if(lineCounts<=10000){
	                                  System.out.println(line);
	                                  String  replace  =  line.replaceAll(",",  "|")  ;  
	                                  String[]  split  =  replace.split("|");
	                                  String  id  =  split[1];
	                                  int  recentSize  =  ids.size();
	                                  ids.add(id);
	                                  int  afterSize  =  ids.size();
	                                  if(afterSize>recentSize){
	                                  System.out.println(replace);
	                                          writter.write(replace);
	                                  }else{
	                                  continue;
	                                  }
	                                  }else{
	                                  lineCounts  =  0;
	                                  fileNum++;
	                                  fileNew  =  new  File(sourceExcelFolder+"/"+fileNum+"_customer.txt");
	                          fileNew.createNewFile();
	                          writter  =new  BufferedWriter(new  OutputStreamWriter(new  FileOutputStream(fileNew),  "GBK"))  ;
	                          String  replace  =  line.replaceAll(",",  "|")  ;
	                          String[]  split  =  replace.split("|");
	                                  String  id  =  split[0];
	                                  int  recentSize  =  ids.size();
	                                  ids.add(id);
	                                  int  afterSize  =  ids.size();
	                                  if(afterSize>recentSize){
	                                  System.out.println(replace);
	                                          writter.write(replace);
	                                  }else{
	                                  continue;
	                                  }
	                                  }
	                                  lineCounts++;
	                                  writter.flush();
	                                  writter.newLine();
	                                    }
	                                    reader.close();
	                                    writter.close();
	                          }  catch  (Exception  e)  {        
	                                      e.printStackTrace();        
	                          }
	                          
	  }
	  }
	         
}
