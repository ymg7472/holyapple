package twitchTest; 

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import selenium.SeleniumTest;

/**
 * <pre>
 * twitchTest 
 * Clipcrawler.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 8. 16.
 * @author : ymg74
 * @version : v1.0
 */
public class ClipCrawler {
	public ArrayList<String> getLinks(String s){
		SeleniumTest selTest = new SeleniumTest(s);
		Document doc = Jsoup.parse(selTest.crawl());
		Elements element = doc.getElementsByClass("tw-hover-accent-effect tw-relative");
		Elements element1 = element.select("a");  
		//		System.out.println(element.toString());
		ArrayList<String> asd = (ArrayList<String>) element1.eachAttr("href");
		ArrayList<String> result = new ArrayList<String>();
		Pattern pattern = Pattern.compile("([A-Z])\\w+");
		for(int i=0; i<asd.size(); i++) {	
			Matcher m = pattern.matcher(asd.get(i));
			while(m.find()) {
				result.add(m.group());
			}
		}
		return result;
	}
	public static void main(String[] args) {

//		ClipCrawler cw = new ClipCrawler();
//		ArrayList<String> asd = cw.getLinks();
//		for(int i=0; i<asd.size(); i++) {
//			System.out.println(asd.get(i));
//		}

	}

}
