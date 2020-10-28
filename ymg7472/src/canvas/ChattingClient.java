package canvas; 


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <pre>
 * kr.co.swh.lecture.network.chat.client 
 * ChattingClient.java
 *
 * 설명 :채팅 클라이언트 프로그램
 * </pre>
 * 
 * @since : 2018. 12. 28.
 * @author : tobby48
 * @version : v1.0
 */
public class ChattingClient {
	
	private ChattingClientInputThread input;
	private ChattingClientOutputThread output;
	
	private String host;
	private int port;
	private String nick;
	
	public ChattingClient(String host, int port, String nick) {
		this.host = host;
		this.port = port;
		this.nick = nick;
	}
	
	public void connect() throws UnknownHostException, IOException {
		Socket socket = new Socket(host, port);
		//	입 출력 관련 스레드를 생성
		input = new ChattingClientInputThread(socket);
		output = new ChattingClientOutputThread(socket, nick);
		
		//	스레드 동작
		input.start();
		output.start();
	}
	
	public static void main(String[] args) {
		try {
			ChattingClient client = new ChattingClient("127.0.0.1", 1234, "asd");
			client.connect();
		}catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
