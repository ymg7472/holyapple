package twitchTest; 

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * <pre>
 * twitchTest 
 * TwitchPost.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 8. 9.
 * @author : ymg74
 * @version : v1.0
 */
public class TwitchGet {



	public static void main(String[] args) {
		HttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet("https://api.twitch.tv/helix/streams");
		try {
			request.setHeader("Client-ID", "usvbyybygjuffukw3b6ak9pvjdev15");
			request.setHeader("Authorization", "Bearer z9xv7zytvo439zuhogzuxd12aduugr");
			request.setHeader("Accept", "application/vnd.twitchtv.v5+json");
			HttpResponse response = client.execute(request);

			//  응답
			String result = EntityUtils.toString(response.getEntity());
			System.out.println(result);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	}
}
