package LinkedListNews;

import java.io.IOException;
import java.util.ArrayList;

import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.Eojeol;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.bitbucket.eunjeon.seunjeon.Morpheme;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * <pre>
 * LinkedListNews 
 * Test.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 7. 19.
 * @author : ymg74
 * @version : v1.0
 */
public class Test {
	public ArrayList<String> getLinks() {;
		String articleURL = "https://news.naver.com/main/list.nhn?mode=LS2D&mid=shm&sid1=100&sid2=269";
		Document doc= null;
		try {
			doc = Jsoup.connect(articleURL).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Elements ele = doc.getElementsByClass("list_body newsflash_body");
		Elements news = ele.select("a");
		ArrayList<String> readylinks = (ArrayList<String>) news.eachAttr("href");
		return readylinks;
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		LinkedList arList = new LinkedList();
		ArrayList<String> readylinks = t.getLinks();
		Morph morph1 = new Morph(null);
		Sentence sen = new Sentence();
		Article art = new Article();
		for(int i = 0; i<readylinks.size(); i++) {
			Article ar = null;
			Document doc1 = null;
			try {
				doc1 = Jsoup.connect(readylinks.get(i)).timeout(0).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Elements el = doc1.getElementsByClass("_article_body_contents");
			String sd = el.text();
			for (Eojeol eojeol: Analyzer.parseEojeolJava(sd)) {
				for (LNode node: eojeol.nodesJava()) {
					Morpheme m = node.morpheme();
					morph1.setData(m.surface());
					sen.morphList.addLast(morph1);
					if(m.feature().head().equals("SF")) {
						art.sentenceList.addLast(sen.morphList);
					}
					
				}
			}
			
		}
	}
	

}
