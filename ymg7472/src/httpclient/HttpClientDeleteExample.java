package httpclient;

import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientDeleteExample {
    public static void main(String[] args) {
        HttpClient client = HttpClients.createDefault();
        HttpDelete request = new HttpDelete("林家");
        try {
            //  昏力 夸没
            HttpResponse response = client.execute(request);
            
            //  览翠
            String result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        } 
    }
}