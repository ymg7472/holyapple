package twitchTest; 

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;


import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 * <pre>
 * twitchTest 
 * TwitchGetUtil.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 8. 13.
 * @author : ymg74
 * @version : v1.0
 */
public class TwitchGetUtil {
	HttpClient client = HttpClients.createDefault();
	HttpGet request = null;
	public Streamer getStreamer(String key, String what) {
		String result = null;
		String url = "";
		if(what.equals("id")) {
			url = "https://api.twitch.tv/helix/users?id=" + key;
		}else if (what.equals("login")) {
			url = "https://api.twitch.tv/helix/users?login=" + key;
		}
		request = new HttpGet(url);
		try {
			request.setHeader("Client-ID", "usvbyybygjuffukw3b6ak9pvjdev15");
			request.setHeader("Authorization", "Bearer z9xv7zytvo439zuhogzuxd12aduugr");

			HttpResponse response = client.execute(request);
			result = EntityUtils.toString(response.getEntity());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		JsonParser parser = new JsonParser();
		JsonElement sa = parser.parse(result).getAsJsonObject().get("data").getAsJsonArray().get(0);	
		Gson gson = new Gson();
        Streamer s = gson.fromJson(sa.toString(), Streamer.class);
		return s;
	}
	public ArrayList<String> getClips(String name) {
		request = new HttpGet("https://api.twitch.tv/kraken/clips/top?channel=" + name + "&period=all&trending=false&limit=10");
		String result = "";
		try {
			request.setHeader("Client-ID", "usvbyybygjuffukw3b6ak9pvjdev15");
			request.setHeader("Authorization", "Bearer z9xv7zytvo439zuhogzuxd12aduugr");
			request.setHeader("Accept", "application/vnd.twitchtv.v5+json");
			HttpResponse response = client.execute(request);
			result = EntityUtils.toString(response.getEntity());
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		JsonParser Parser = new JsonParser();
		JsonObject jsonObj = (JsonObject) Parser.parse(result);
		JsonArray memberArray = (JsonArray) jsonObj.get("clips");
		ArrayList<String> asd = new ArrayList<String>();
		for (int i = 0; i < memberArray.size(); i++) {
			JsonObject object = (JsonObject) memberArray.get(i);
			String t = object.get("slug").toString();
			asd.add(t.substring(1, t.length()-1));
		}
		return asd;
	}
	public ArrayList<Stream> getStreams() {
		request = new HttpGet("https://api.twitch.tv/helix/streams");
		String result = null;
		try {
			request.setHeader("Client-ID", "usvbyybygjuffukw3b6ak9pvjdev15");
			request.setHeader("Authorization", "Bearer z9xv7zytvo439zuhogzuxd12aduugr");
			request.setHeader("Accept", "application/vnd.twitchtv.v5+json");
			HttpResponse response = client.execute(request);

			//  응답
			result = EntityUtils.toString(response.getEntity());
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		JsonParser Parser = new JsonParser();
		JsonObject jsonObj = (JsonObject) Parser.parse(result);
		JsonArray memberArray = (JsonArray) jsonObj.get("data");
		Gson gson = new Gson();
		Type type = new TypeToken<ArrayList<Stream>>(){}.getType();
		ArrayList<Stream> asd = gson.fromJson(memberArray.toString(), type);
		return asd;
	}
	public static void main(String[] args) {

		TwitchGetUtil tw = new TwitchGetUtil();
		Streamer asd = tw.getStreamer("tmxk319", "login");
		System.out.println(asd);
//		for(int i = 0; i<asd.size(); i++) {
//			System.out.println(asd.get(i));
//		}
		
	}

}
