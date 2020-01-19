package network;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
public class URLEx2 {
    public static void main(String[] args) {
        try {
            URL url= new URL("https://www.naver.com");
            //  Case1 : URL방식
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            
            //  Case2 : URLConnection방식 (HTTP POST방식으로 서버에 데이터를 전송)
//            URLConnection uc = url.openConnection();
//            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            
            String inputLine;
            while ((inputLine = in.readLine()) != null) //  한 행씩 읽기
                System.out.println(inputLine);
            in.close();
        } catch (IOException e) {
            System.out.println("URL에서 데이터를 읽는 중 오류가 발생 했습니다.");
        }
    }
}