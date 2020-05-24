package jsoup_example;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;


@Data
public class JsoupCrawlerMain {
	public static void main(String[] args) throws IOException {
		News[] newslist = new News[10];
		String url = "https://news.naver.com/";
		Document doc = Jsoup.connect(url).get();
		Element titles = doc.select("ul.section_list_ranking").get(0);
		Elements news = titles.select("li");
		Elements news1 = titles.select("a");
		int i = 1;
		for(Element e : news1){
			News n = new News();
			n.setTitle(e.text()); 
			n.setRank(i);
			String holy = new Gson().toJson(n);
	        System.out.println(holy);

			i++;
		    
		}

	}
}