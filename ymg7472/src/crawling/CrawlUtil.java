package crawling; 

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * <pre>
 * crawling 
 * CrawlUtill.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 6. 28.
 * @author : ymg74
 * @version : v1.0
 */
public class CrawlUtil {
	
	private Document doc= null;
	CrawlUtil cr = new CrawlUtil();
	
	public String menu(String s) {
		String date = s;
		String url = "http://www.jpo.ms.kr/lunch.view?date=" + date;
		doc = cr.crawl(url);
		Elements ele = doc.select("div.menuName span");
		String final1 = ele.text().replace(" ", "\n > ");
		if(ele.eachText().toString().equals("[]")) final1 = "없노";
		return final1;
		
	}
	public String korona() {
		String url = "https://coronamap.site/";
		doc = cr.crawl(url);
		Elements ele = doc.select("div.wa");
		String k = ele.text();
		String k1 = k.substring(0, k.length()-3);
		Elements ele1 = doc.select("div.wa1");
		String k2 = ele1.text();
		k1 = k1.substring(0, k1.length()-48);
		String fin = "**" + k1 + "\n" + k2 + "**";
		return fin;
	}
	
	public Document crawl(String url) {
		try {
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

}
