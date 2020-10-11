package twitchTest; 

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
public class TwitchPost {

	private Logger log = Logger.getLogger(this.getClass());

	public static void main(String[] args) {
		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("https://api.twitch.tv/helix/clips?broadcaster_id=49045679&scope=clips:edit");
		try {
			
//			request.setHeader("Accept", "application/vnd.twitchtv.v5+json");
			request.setHeader("Client-ID", "usvbyybygjuffukw3b6ak9pvjdev15");	
			request.setHeader("Authorization", "Bearer m0cb7yl1wevibhuvxow57vq7mi4h5q");

//			HttpResponse response = client.execute(request);
	
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
