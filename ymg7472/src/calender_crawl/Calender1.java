package calender_crawl;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackAttachment;
import net.gpedro.integrations.slack.SlackMessage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

@Data
class Calender1 {
//	입력한 달이 몇일까지 있는지를 계산
	public static int endDayFromTotalDay(int year, int month){
			int lastday;
			switch (month) {
			case 2:
				lastday = 28;
				if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0)
					lastday = 29;
				else
					lastday = 28;
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				lastday = 30;
				break;
			default:
				lastday = 31;
			}
			return lastday;
	}
	
	public static int totalDayFromCalendar(int year, int month, int day){
		int totaldays;
		totaldays = 365 * (year - 1);
		for (int i = 1; i < year; i++) {
			if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)
				totaldays++;
		}
		// totaldays = 365 * (year-1) + (year-1)/4 - (year-1)/100 + (year-1)/400
		int premonth = month - 1;
		if (premonth >= 1)
			totaldays += 31;
		if (premonth >= 2)
			totaldays += 28;
		if (premonth >= 3)
			totaldays += 31;
		if (premonth >= 4)
			totaldays += 30;
		if (premonth >= 5)
			totaldays += 31;
		if (premonth >= 6)
			totaldays += 30;
		if (premonth >= 7)
			totaldays += 31;
		if (premonth >= 8)
			totaldays += 31;
		if (premonth >= 9)
			totaldays += 30;
		if (premonth >= 10)
			totaldays += 31;
		if (premonth >= 11)
			totaldays += 30;
		if (month > 2 && (year % 4 == 0 && year % 100 != 0 || year % 400 == 0))
			totaldays++;
		totaldays++;

//		int day = totaldays % 7;
		
		totaldays = totaldays + day;
		return totaldays;
}
	public static ArrayList<Integer> getDate(String num_1, String num_2) {
		int num1y =Integer.parseInt(num_1.substring(0,4));
		int num1m =Integer.parseInt(num_1.substring(4,6));
		int num1d =Integer.parseInt(num_1.substring(6));
		int num2y =Integer.parseInt(num_2.substring(0,4));
		int num2m =Integer.parseInt(num_2.substring(4,6));
		int num2d =Integer.parseInt(num_2.substring(6));
		int dif = (totalDayFromCalendar(num2y, num2m, num2d)) - (totalDayFromCalendar(num1y, num1m, num1d));
		//System.out.println(totalDayFromCalendar(num1y, num1m, num1d));
		//System.out.println(totalDayFromCalendar(num2y, num2m, num2d));
		ArrayList<Integer> dateList = new ArrayList<Integer>();
		int i = 0;
		while(i<dif) {
			if(num1d>endDayFromTotalDay(num1y,num1m)) {
				num1m += 1;
				num1d = 1;
			}
			if(num1m>12) {
				num1y += 1;
				num1m = 1;
			}
			dateList.add(num1y*10000+num1m*100+num1d);
			num1d++;
			i += 1;
		}
		dateList.add(Integer.parseInt(num_2));
		return (dateList);
	}
	
	
	public static void main(String[] args) {
		PrintStream s = System.out;
		String num1="100";
		String num2="269";
		int how = 0;
		Scanner sc = new Scanner(System.in);
		s.println("원하는 날짜?");
		String wantDate = sc.nextLine();
		s.println("정치, 경제, 사회, 문화 중 택일");
		String wantSub = sc.nextLine();
		if(wantSub.equals("정치")) {
			num1="100";
			num2="269";
		}else if(wantSub.equals("경제")){
			num1="101";
			num2="263";
		}else if(wantSub.equals("사회")) {
			num1="102";
			num2="257";
		}else {
			num1="102";
			num2="257";

		}
		sc.close();
		ArrayList<String> links = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		ArrayList<C_news> news1 = new ArrayList<C_news>();
		int holy = 1;
		while(true) {
			InetAddress ia = null;		// 접속할 서버의 주소를 저장할 변수
			Socket sock = null;		// 서버에 접속할 소켓 변수
			PrintWriter out = null;		// 테이터를 전송할  Write 변수
			String articleURL = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2="+ num2 +"&sid1="+ num1 +"&mid=shm&date="+ wantDate +"&page="+ holy;
			Document doc= null;
			try {
				doc = Jsoup.connect(articleURL).timeout(0).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			Elements ele = doc.getElementsByClass("list_body newsflash_body");
			Elements news = ele.select("a");
	//		s.println(news.eachAttr("href"));
			ArrayList<String> readylinks = (ArrayList<String>) news.eachAttr("href");
			ArrayList<String> r1 = new ArrayList<String>();
			if (links.contains(readylinks.get(1))) {
				
				break;	
			}else {
				for(int i = 0; i < readylinks.size(); i++) {
					if(!r1.contains(readylinks.get(i))) {
						r1.add(readylinks.get(i));
					}
				}
				for(int i = 0; i < r1.size(); i++) {
					links.add(r1.get(i));
				}
			}
			
			
			for(int i = 0; i < r1.size(); i++) {
				String g = r1.get(i);
				id.add(g.substring(g.length()-10, g.length()));
			}
			
			for(int i = 0; i < r1.size(); i++) {
				String sub = wantSub;
				C_news n = new C_news();
				String idVal = "";
				Pattern pattern = Pattern.compile("(?<=(&oid=|\\?oid=)|(&aid=|\\?aid=))[\\d]+");
				String articleURL1 = r1.get(i);
				Matcher m = pattern.matcher(articleURL1);
				while(m.find()) {
					//group() 메소드를 호출하고 정규 표현에 일치된 문자열을 꺼냄
					idVal = idVal + m.group() +"-";
					
				}
//				String aid = articleURL1.substring(articleURL1.length()-10, articleURL1.length()); 
//				String oid = articleURL1.substring(articleURL1.length()-18, articleURL1.length()-15);
				n.setId(idVal.substring(0,idVal.length()-1));
//				System.out.println(n.getId());
//				n.setId(oid+"-"+aid);
				Document doc1=null;
				try {
					doc1 = Jsoup.connect(articleURL1).timeout(0).get();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Elements t1 = doc1.select("title");
				String tit = t1.text().toString();
				n.setTitle(tit);
				t1 = doc1.getElementsByClass("_article_body_contents");
				n.setContents(t1.text());
				t1 = doc1.getElementsByClass("t11");
				n.setDate(t1.text());
				n.setSubject("정치");
				s.println(n);
				
				String json = new Gson().toJson(n);
				try {
					ia = InetAddress.getByName("127.0.0.1");
					// 서버 연결
					sock = new Socket(ia, 9999);
					// 서버에 메세지 전송
					out = new PrintWriter(sock.getOutputStream());
					s.println(json);
					how++;
					out.flush();
					// 접속 끊기
					sock.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//s.println(json);
				news1.add(n);
			}
			
			readylinks.clear();
			r1.clear();
			s.println(holy);
			holy++;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
		SlackAttachment attach = new SlackAttachment();
		attach.setTimestamp(new java.util.Date());
		attach.setTitle("저장 완료");
		attach.setImageUrl("이미지경로");
		attach.setFallback("점검이 필요합니다.");
		attach.setText(how + "건이 성공적으로 저장되었습니다.");
		attach.setColor("#323330");
		api.call(new SlackMessage(wantDate+"자 뉴스").addAttachments(attach));

			



	}
}
