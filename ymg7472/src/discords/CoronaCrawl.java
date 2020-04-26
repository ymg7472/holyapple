package discords;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class CoronaCrawl {
	public String korona() {
		Document doc= null;
		String url = "https://coronamap.site/";
		try {
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Elements ele = doc.select("div.wa");
		String k = ele.text();
		String k1 = k.substring(0, k.length()-3);
		Elements ele1 = doc.select("div.wa1");
		String k2 = ele1.text();
		k1 = k1.substring(0, k1.length()-48);
		String fin = "**" + k1 + "\n" + k2 + "**";
		return fin;
	}

}
