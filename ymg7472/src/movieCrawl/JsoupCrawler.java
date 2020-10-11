package movieCrawl;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import selenium.SeleniumTest;
public class JsoupCrawler {

	public static void main(String[] args) {

		SeleniumTest selTest = new SeleniumTest("https://www.twitch.tv/directory/all?sort=VIEWER_COUNT");
		Document doc = Jsoup.parse(selTest.crawl());        


		System.out.println(doc.toString());
		Elements element = doc.getElementsByClass("tw-media-card-meta__title");
		System.out.println(element.toString());
		Elements element1 = element.select("a");  
//		System.out.println(element.toString());
		ArrayList<String> asd = (ArrayList<String>) element1.eachAttr("href");
		for(int i=0; i<asd.size(); i++){
			System.out.println(asd.get(i));
		}

	}
}