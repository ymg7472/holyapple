package network;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * <pre>
 * kr.co.swh.lecture.network.udp
 * UdpServer.java
 *
 * 설명 :UDP 서버
 * </pre>
 * 
 * @since : 2018. 6. 23.
 * @author : tobby48
 * @version : v1.0
 */
public class UdpServer {
	public static void main(String[] args) {
		DatagramSocket ds = null;
		try{
			ds = new DatagramSocket(7777);	// 상대방이 연결할수 있도록 UDP 소켓 생성
			byte [] date = new byte[66536];		// 전송받은 데이터를 지정할 바이트 배열선언

			// UDP 통신으로 전송을 받을 packet 객체생성
			DatagramPacket dp = new DatagramPacket(date, date.length);
			System.out.println("데이터 수신 준비 완료....");
			while(true){
				// 데이터 전송 받기
				ds.receive(dp);
				System.out.println(new String(dp.getData(),"UTF-8"));
			}
		} catch(Exception e){
			System.out.println(e.getMessage());
		} finally {
			ds.close();
		}
	}
}