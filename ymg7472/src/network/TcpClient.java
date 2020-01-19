package network;

import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
/**
 * <pre>
 * kr.co.swh.lecture.network.tcp
 * TcpClient.java
 *
 * 설명 :TCP 클라이언트
 * </pre>
 * 
 * @since : 2018. 6. 23.
 * @author : tobby48
 * @version : v1.0
 */
public class TcpClient {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		InetAddress ia = null;		// 접속할 서버의 주소를 저장할 변수
		Socket sock = null;		// 서버에 접속할 소켓 변수
		PrintWriter out = null;		// 테이터를 전송할  Write 변수
		try{
			// 서버 주소생성
			ia = InetAddress.getByName("127.0.0.1");
			// 서버 연결
			sock = new Socket(ia, 9999);
			// 서버에 메세지 전송
			out = new PrintWriter(sock.getOutputStream());
			System.out.println("보낼 메시지 입력");
			String msg = s.nextLine();
			out.println(msg+"\n");
			out.flush();
			// 접속 끊기
			sock.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
}
