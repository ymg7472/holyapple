package twitchTest; 

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.http.Header;
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
 * TwitchGet.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 8. 9.
 * @author : ymg74
 * @version : v1.0
 */
public class TwitchOauthCode {

	public static void main(String[] args) {
        HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost("https://id.twitch.tv/oauth2/token?client_id=usvbyybygjuffukw3b6ak9pvjdev15&client_secret=ijdyfkv3co1biqfbyg3q56l7oy937o&grant_type=client_credentials");
        try {
            //  필요에 따라서는 헤더 추가
        	request.setHeader("Authorization", "usvbyybygjuffukw3b6ak9pvjdev15");
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
