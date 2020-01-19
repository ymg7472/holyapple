package weather;
import java.io.IOException;
import java.io.PrintStream;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

public class WeatherCrawl {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		PrintStream s = System.out;
		String url = "http://www.weather.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=109";
		Document doc = Jsoup.connect(url) .parser(Parser.xmlParser()).timeout(0).get();
		Elements e = doc.select("location");
		for (Element ele : e) {
			Elements subnode = ele.select("city");
			s.println(subnode.text());
			s.println("==============");
			Elements e1 = doc.select("data");
			for (Element elem : e1 ) {
				Elements sn1 = elem.select("tmef");
				s.print("날짜: "+sn1.text()+", ");
				sn1 = elem.select("wf");
				s.print("날씨: "+sn1.text()+", ");
				sn1 = elem.select("tmn");
				s.print("최저기온: "+sn1.text()+", ");
				sn1 = elem.select("tmx");
				s.println("최고기온: "+sn1.text());
			}
		}
	}

}
