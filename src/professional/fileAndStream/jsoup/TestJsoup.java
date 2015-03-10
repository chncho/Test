package professional.fileAndStream.jsoup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import professional.fileAndStream.csv.CSVUtils;

public class TestJsoup {

	public static void main(String[] args) throws IOException {
		
		String baseurl = "http://www.dixintong.com/mendian.aspx?province=%E8%AF%B7%E9%80%89%E6%8B%A9%E7%9C%81%E4%BB%BD&city=%E8%AF%B7%E9%80%89%E6%8B%A9%E5%9F%8E%E5%B8%82&area=%E8%AF%B7%E9%80%89%E6%8B%A9%E5%9C%B0%E5%8C%BA"
				;
		List<String> dataList=new ArrayList<String>();

		for(int i=0;i<354;i++){
			strechData(baseurl+"&page="+(i+1),dataList);
			System.out.println("..."+i);
		}
		
		CSVUtils.exportCsv(new File("D:/test/ljq.csv"), dataList,"UTF-8");
		
	}
	public static void strechData(String url,List<String> dataList) throws IOException{
		
		Document doc = Jsoup.connect(url)
				  //.data("query", "Java")
				  .userAgent("Mozilla")
				  .cookie("auth", "token")
				  .timeout(3000)
				  .post();
		Elements lis = doc.select("div#right ul li");
		
		for (Element li : lis) {
			Elements a = li.getElementsByTag("a");
			dataList.add("\""+a.get(0).text()+"\",\""+a.get(1).text()+"\",\""+a.get(2).text()+"\"");
		}
		
	}
}
