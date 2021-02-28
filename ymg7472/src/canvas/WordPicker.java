package canvas; 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.bitbucket.eunjeon.seunjeon.Morpheme;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;

import edu.emory.mathcs.backport.java.util.Collections;
import naver_news_spark.models.WordCloud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
/**
 * <pre>
 * canvas 
 * WordPicker.java
 *
 * ���� :
 * </pre>
 * 
 * @since : 2020. 11. 4.
 * @author : ymg74
 * @version : v1.0
 */
public class WordPicker {
	public ArrayList<String> getWordList(){
		ArrayList<String> data = new ArrayList<String>();
		for(int i = 0; i<6; i++) {
			Document doc = null;
			try {
				doc = Jsoup.connect("https://news.naver.com/main/main.nhn?mode=LSD&mid=shm&sid1=10" + String.valueOf(i)).timeout(0).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Elements eles = doc.select("div.cluster");
			Elements ele = eles.select("div.cluster_text");
			String mor = "";
			for(Element e : ele) {
				for (LNode node : Analyzer.parseJava(e.text())) {
					Morpheme m = node.morpheme();
					mor = m.feature().head().toString();
					if(mor.equals("NNG") || mor.equals("NNP")) {
						if(!m.surface().equals("네이버") && !m.surface().contains("뉴스") ) {
							data.add(m.surface());
						}
					}
				}
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i = 0; i<data.size(); i++) {
			if(!map.containsKey(data.get(i))) {
				map.put(data.get(i), 1);
			}else {
				map.put(data.get(i), map.get(data.get(i))+1);
			}
		}
		List<String> keList = new ArrayList<>(map.keySet());
		List<Integer> vaList = new ArrayList<>(map.values());
		ArrayList<String> dl = new ArrayList<String>();
		for(int i = 0; i<map.size(); i++) {
			if(vaList.get(i)>=2 && keList.get(i).length()>1) {
				dl.add(keList.get(i));
			}
		}
		Collections.shuffle(dl);
		return dl;

	}

}
