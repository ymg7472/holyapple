package naver_news_spark;

import java.io.IOException;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import naver_news_spark.models.C_news;

public class NaverNewsCrawl{
	// get count, divide 20, if result > 1 --> skip page 1 and start from page 2 끼에엑
	private String wantSub;
	private String wantDate;
	private String choose;
	
	public NaverNewsCrawl(String wantSub, String wantDate, String choose) {
		this.choose = choose;
		this.wantDate = wantDate;
		this.wantSub = wantSub;
	}
	
	public int getDiff(String num1, String num2) throws SQLException {
		int totalPage = getTotalPage(num1, num2);
		int pageCount = getPageCount();
		if(((totalPage-pageCount)/20) == 0) {
			return 1;
		}else {
			return ((totalPage-pageCount)/20)+1;
		}
		
		
	}
	public int getPageCount() throws SQLException {
		
		int res = 0;
		ResultSet rs = t1.db.getStatement().executeQuery(String.valueOf("select count(*) from CrawlNews where CrawlNews.date="+wantDate+" and CrawlNews.subject= '"+wantSub+"'")); 
		while(rs.next()) {
			res = rs.getInt(1);
		}
		return res;

	}
	
	public int getTotalPage(String num1, String num2) {
		String articleURL = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2="+ num2 +"&sid1="+ num1 +"&mid=shm&date="+ wantDate +"&page=123123";
		Document doc1 = null;
		int i = 0;
		try {
			doc1 = Jsoup.connect(articleURL).timeout(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Elements ele1 = doc1.getElementsByClass("list_body newsflash_body");
		Elements news = ele1.select("dd > span.lede");
		
		Elements ele = doc1.select(".paging");
		for(Element e : ele) {
			i = Integer.valueOf(e.select("strong").text());
		}
		//(i-1)*20 + 
		return (i-1)*20 + news.size();
	}
	
	public String getArticleByPage(String articleURL1, String wantSub, String wantDate) {
		String sub = wantSub;
		C_news n = new C_news();
		n.setSubject(sub);
		String idVal = "";
		Pattern pattern = Pattern.compile("(?<=(&oid=|\\?oid=)|(&aid=|\\?aid=))[\\d]+");
		Matcher m = pattern.matcher(articleURL1);
		while(m.find()) {
			//group() 메소드를 호출하고 정규 표현에 일치된 문자열을 꺼냄
			idVal = idVal + m.group() +"-";
		}
		n.setId(idVal.substring(0,idVal.length()-1));
		
		
		Document doc1 = null;
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
		n.setDate(wantDate);

		String json = new Gson().toJson(n);
		return json;
	}
	
	private static final String EXCHANGE_NAME = "ymg7472";
	public void crawlNews() throws ParseException, IOException, TimeoutException, SQLException{
		PrintStream s = System.out;
		
		String num1="100";
		String num2="269";
		
		
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("dev-swh.gq");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String routingKey = "naver_news";
		
		
		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("http://localhost:4567/users");
		
		
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
			num1="105";
			num2="230";
		}
		ArrayList<String> links = new ArrayList<String>();
		ArrayList<String> id = new ArrayList<String>();
		int holy = getDiff(num1, num2);
		int h = getTotalPage(num1, num2);
		int t = getPageCount();
		int r = 0;
		if(!(h%20 == 0)) {
			r = (h/20)+1;
		}else {
			r = h/20;
		}
		int res = r - holy;
		System.out.println("total page = " + h);
		System.out.println("total loop = " + r);
		System.out.println("diff = " + holy);
		System.out.println("pageIhave = " + t);
		System.out.println("skipCount = " + res);
		int tem = 0;
		if(t == 0) {
			tem = r;
		}else {
			tem = holy;
		}
		for(int asd = 0; asd < tem; asd++) {
//			if(asd < res) {
//				continue;
//			}
			String articleURL = "https://news.naver.com/main/list.nhn?mode=LS2D&sid2="+ num2 +"&sid1="+ num1 +"&mid=shm&date="+ wantDate +"&page="+ (asd+1);
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
				String json = getArticleByPage(r1.get(i), wantSub, wantDate);
				
				
				if(choose == "post") {
					HttpEntity entity = new StringEntity(json, "UTF-8");
					request.setEntity(entity);
					HttpResponse response = client.execute(request);
					System.out.println(json);
					String result = EntityUtils.toString(response.getEntity());

				}
				else if(choose == "rabbit"){
					try {
						channel.basicPublish(EXCHANGE_NAME, routingKey, null, json.getBytes("UTF-8"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			
			client = HttpClients.createDefault();
			readylinks.clear();
			r1.clear();
			s.println(asd);
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	
}
