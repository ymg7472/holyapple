package schoolfood;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.Scanner;
import java.io.IOException;
import java.io.PrintStream;
public class MenuCrawl {
	public String menu(String s) {
		PrintStream q = System.out;
		Document doc= null;
		String date = s;
		String url = "http://www.jpo.ms.kr/lunch.view?date=" + date;
		try {
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Elements ele = doc.select("div.menuName span");
		String final1 = ele.text().replace(" ", "\n > ");
		if(ele.eachText().toString().equals("[]")) final1 = "없노";
		return final1;
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PrintStream q = System.out;
		Document doc= null;
		Scanner s = new Scanner(System.in);
		q.println("원하는 날짜?");
		String date = s.nextLine();
		String url = "http://www.jpo.ms.kr/lunch.view?date=" + date;
		try {
			doc = Jsoup.connect(url).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Elements ele = doc.select("div.menuName span");
		System.out.println(ele.eachText());
		String menu = "> " + ele.text();
		q.println(menu.replace(" ", "\n > "));
	}

}
