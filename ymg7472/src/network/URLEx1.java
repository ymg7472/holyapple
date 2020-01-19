package network;

import java.net.MalformedURLException;
import java.net.URL;
public class URLEx1 {
    public static void main(String[] args) {
        URL opinion = null;
        URL homepage = null;
        try {
            homepage = new URL("http://swhacademy.ga:8081");    //  절대 경로
            opinion = new URL(homepage, "sw_newtork/index.html");   //  상대 경로
        } catch (MalformedURLException e) {
            System.out.println("잘못된 URL입니다.");
        }
        System.out.println("Protocol = " + opinion.getProtocol());  //  프로토콜
        System.out.println("host =" + opinion.getHost());   //  호스트 이름
        System.out.println("port =" + opinion.getPort());   //  포트번호
        System.out.println("path =" + opinion.getPath());   //  경로
        System.out.println("filename =" + opinion.getFile());   //  파일이름
    }
}