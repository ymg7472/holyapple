package kakao;

import static spark.Spark.get;
import static spark.Spark.modelAndView;

import static spark.Spark.get;
import static spark.Spark.modelAndView;
import static spark.Spark.post;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import naver_news_spark.FreeMarkerTemplateEngine;

/**
 * <pre>
 * kakao 
 * KakaoRequest.java
 *
 * 설명 : 키 가져오는 spark
 * </pre>
 * 
 * @since : 2020. 7. 12.
 * @author : ymg74
 * @version : v1.0
 */
public class KakaoRequest {
	HttpClient client = HttpClients.createDefault();
	public String getAccessToken() {
		HttpGet request = new HttpGet("http://127.0.0.1:5000/");
		String result = "";
		try {
            HttpResponse response = client.execute(request);
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
		return result;
		
	}

	public static void main(String[] args) {
		 HttpPost request = new HttpPost("https://kapi.kakao.com/v2/api/talk/memo/default/send");
//		 String token = "iMQP2At-VD37KR4q593x0txnqg1qRp5uSRsMxQo9dVsAAAFzQgxjXg";
//		 JSON h = {"Authorization": "Bearer " + token};
//		 request.setHeader(header);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		KakaoRequest k = new KakaoRequest();
//		System.out.println(k.getAccessToken());
//		get("/", (request, response) -> {
//			return modelAndView(null, "index.ftl");
//		}, new FreeMarkerTemplateEngine());
//		
//		get("/ouath", (request, response) -> {
//			return modelAndView(null, "test.ftl");
//		}, new FreeMarkerTemplateEngine());

	}

}
