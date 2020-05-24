package calender_crawl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class HttpClientGetExample {
    public static void main(String[] args) {
        HttpClient client = HttpClients.createDefault();
        int s = 0;
        HttpGet request = new HttpGet("http://localhost:4567/news/%EA%B2%BD%EC%A0%9C/20200223");
        String result = "";
        try {
            //  필요에 따라서는 헤더 추가
//          request.addHeader("Content-type", "application/json");
            
            //  요청
            HttpResponse response = client.execute(request);
            
            //  응답
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        Gson gson = new Gson(); 
        
        Type userListType = new TypeToken<ArrayList<C_news>>(){}.getType();
         
        ArrayList<C_news> userArray = gson.fromJson(result, userListType);
        for(C_news news : userArray) {
            System.out.println(news);
            s++;
        }
        System.out.println(s);
    }
}