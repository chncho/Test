package getResourceFromWeb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
  
/**  
 * @author Der  
 * @date   05-01  
 * @E-mail uidin@163.com  
 * */   
/*
 * httpclient技术
 */
public   class  MyRSS  
{  
    /**  
     * 获取搜索结果的html源码  
     * */   
    public   static  String getHtmlSource(String url)  
    {  
          
        StringBuffer codeBuffer = null ;  
        BufferedReader in=null ;  
        try   
        {  
            URLConnection uc = new  URL(url).openConnection();  
  
            /**  
             * 为了限制客户端不通过网页直接读取网页内容,就限制只能从浏览器提交请求.  
             * 但是我们可以通过修改http头的User-Agent来伪装,这个代码就是这个作用  
             *   
             */   
            uc.setRequestProperty("User-Agent" ,  
                    "Mozilla/4.0 (compatible; MSIE 5.0; Windows XP; DigExt)" );  
  
            // 读取url流内容   
            in = new  BufferedReader( new  InputStreamReader(uc  
            		.getInputStream(), "utf-8" ));
                   // .getInputStream(), "gb2312" ));  
            codeBuffer = new  StringBuffer();  
            String tempCode = "" ;  
            // 把buffer内的值读取出来,保存到code中   
            while  ((tempCode = in.readLine()) !=  null )  
            {  
                codeBuffer.append(tempCode).append("\n" );  
            }  
            in.close();  
        }  
        catch  (MalformedURLException e)  
        {  
            e.printStackTrace();  
        }  
        catch  (IOException e)  
        {  
            e.printStackTrace();  
        }  
          
        return  codeBuffer.toString();  
    }  
  
    /**  
     * 正则表达式  
     * */   
    public   static  String regex()  
    {  
        String googleRegex = "<div class=g>(.*?)href=\"(.*?)\"(.*?)\">(.*?)</a>(.*?)<div class=std>(.*?)<br>" ;  
        return  googleRegex;  
    }  
  
    /**  
     * 测试用  
     * 在google中检索关键字，并抽取自己想要的内容  
     *   
     * */   
    public   static  List<String> GetNews()  
    {  
        List<String> newsList = new  ArrayList<String>();  
        String allHtmlSource = MyRSS  
                .getHtmlSource("http://www.google.cn/search?complete=1&hl=zh-CN&newwindow=1&client=aff-os-maxthon&hs=SUZ&q=%E8%A7%81%E9%BE%99%E5%8D%B8%E7%94%B2&meta=&aq=f" );  
        Pattern pattern = Pattern.compile(regex());  
        Matcher matcher = pattern.matcher(allHtmlSource);  
  
        while  (matcher.find())  
        {  
            String urlLink = matcher.group(2 );  
            String title = matcher.group(4 );  
            title = title.replaceAll("<font color=CC0033>" ,  "" );  
            title = title.replaceAll("</font>" ,  "" );  
            title = title.replaceAll("<b>...</b>" ,  "" );  
  
            String content = matcher.group(6 );  
            content = content.replaceAll("<font color=CC0033>" ,  "" );  
            content = content.replaceAll("</font>" ,  "" );  
            content = content.replaceAll("<b>...</b>" ,  "" );  
  
            newsList.add(urlLink);  
            newsList.add(title);  
            newsList.add(content);  
        }  
        return  newsList;  
    }  
  
    /**  
     * main方法  
     * */   
    public   static   void  main(String[] args)  
    {  
        System.out  
        .println(MyRSS  
                .getHtmlSource("http://main.house.sina.com.cn/news/zckb/index.html" ));  
    }  
} 
