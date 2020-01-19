package discords;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.login.LoginException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;

import discord_sentimental.TListener1;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

public class SendJDA {

	public static JDA jda;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<SentimentalDic> dicList = initDatas();
		JDABuilder jb = new JDABuilder(AccountType.BOT);
		jb.setAutoReconnect(true);
		jb.setStatus(OnlineStatus.DO_NOT_DISTURB);
		jb.setToken("NjU4Mjc0Mjk4MDg3MzQyMDkx.Xf9Yag.O31K5Q6AGY58mEd805O5NjV_0sU");
		jb.addEventListeners(new TListener(dicList));
		
		try {
			jda = jb.build();
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<SentimentalDic> initDatas() {
		Gson gson = new Gson();
		List<SentimentalDic> dicList = new ArrayList<SentimentalDic>();
		CloseableHttpClient client = HttpClients.createDefault();
		HttpResponse response = null;
		String responseString = "";
		HttpGet requestGet = new HttpGet("https://raw.githubusercontent.com/park1200656/KnuSentiLex/master/data/SentiWord_info.json");
		try {
			response = client.execute(requestGet);
			responseString = EntityUtils.toString(response.getEntity());
			SentimentalDic[] value = gson.fromJson(responseString, SentimentalDic[].class);
			dicList.addAll(Arrays.asList(value));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return dicList;
	}
}