package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * <pre>
 * kr.co.swh.lecture.network.udp
 * UdpClient.java
 *
 * 설명 :UDP 클라이언트
 * </pre>
 * 
 * @since : 2018. 6. 23.
 * @author : tobby48
 * @version : v1.0
 */
public class UdpClient {
	public static void main(String[] args) {
		try{
			// 전송할 수 있는 UDP 소켓 생성
			DatagramSocket ds = new DatagramSocket();

			// 받을 곳의 주소 생성
			InetAddress ia = InetAddress.getByName("127.0.0.1");

			// 전송할 데이터 생성
			for(int i = 0; i<10; i++) {
				String t = String.valueOf(i);
				DatagramPacket dp = new DatagramPacket(t.getBytes(),t.getBytes().length,ia, 7777);
				ds.send(dp);
			}
			ds.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}