package network;

import java.net.InetAddress;

public class IpDisplay {
    public static void main(String[] args) {
        try{
            // 홈페이지 IP 정보를 출력
            InetAddress [] ia = InetAddress.getAllByName("swhacademy.gq");
            for(InetAddress imsi : ia){
                System.out.println("SWH IP : "+imsi.getHostAddress());
            }
            // 자신의 IP 출력
            InetAddress local = InetAddress.getLocalHost();
            System.out.println("My PC IP :" + local.getHostAddress());
        }catch(Exception e){
            // 예외가 발생했을 때 예외의 내용을 출력
            System.out.println(e.getMessage());
        }
    }
}