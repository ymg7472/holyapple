package httpclient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientPostExample {
    public static void main(String[] args) {
        HttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://localhost:4567/users");
        try {
            //  추가할 데이터
            HttpEntity entity = new StringEntity("{\r\n" + 
            		"    \"name\": \"sans\", \r\n" + 
            		"    \"age\": 19\r\n" + 
            		"}", "UTF-8");

//            request.setEntity(entity);
            
            //  전송
            request.setEntity(entity);
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