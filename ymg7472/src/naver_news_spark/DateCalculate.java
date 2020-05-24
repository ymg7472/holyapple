package naver_news_spark;
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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.Data;

@Data
class DateCalculate {
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


	public static void main(String[] args) throws IOException {
		DatabaseUtil db = new DatabaseUtil("jdbc:mysql://dev-swh.ga/minkyu", "root", "swhacademy!");
		Scanner sc = new Scanner(System.in);
		System.out.println("원하는 날짜?");
		String wantDate = sc.nextLine();
		System.out.println("정치, 경제, 사회, 문화 중 택일");
		String wantSub = sc.nextLine();
		db.crawlNews(wantSub, wantDate);
	}
		
	//		SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
	//		SlackAttachment attach = new SlackAttachment();
	//		attach.setTimestamp(new java.util.Date());
	//		attach.setTitle("저장 완료");
	//		attach.setImageUrl("이미지경로");
	//		attach.setFallback("점검이 필요합니다.");
	//		attach.setText(how + "건이 성공적으로 저장되었습니다.");
	//		attach.setColor("#323330");
	//		api.call(new SlackMessage(wantDate+"자 뉴스").addAttachments(attach));
	//
	//			



}

