package network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
public class URLEx3 {
    public static void main(String[] args) {
        try {
            URL url= new URL("http://httpbin.org/post");
            
            URLConnection uc = url.openConnection();
            uc.setDoOutput(true);   //  POST, PUT인 경우에는 true
            OutputStreamWriter out = new OutputStreamWriter(uc.getOutputStream());
            out.write("name=tobby48&academy=SWH");
            out.close();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            
            String inputLine;
            while((inputLine = in.readLine()) != null)
                System.out.println(inputLine);
            in.close();
        } catch(IOException e){
            System.out.println("URL에 데이터를 입출력 중에 오류가 발생했습니다.");
        }
    }
}