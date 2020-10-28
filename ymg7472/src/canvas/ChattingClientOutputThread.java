package canvas; 

import org.apache.log4j.Logger;

/**
 * <pre>
 * chat 
 * asdg.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 10. 28.
 * @author : ymg74
 * @version : v1.0
 */
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * <pre>
 * kr.co.swh.lecture.network.chat.client 
 * ChattingClientOutputThread.java
 *
 * 설명 :클라이언트 출력 스레드
 * </pre>
 * 
 * @since : 2018. 12. 28.
 * @author : tobby48
 * @version : v1.0
 */
public class ChattingClientOutputThread extends Thread{
	private Socket socket;
	private String nick;
	
	public ChattingClientOutputThread(Socket socket, String nick) {
		this.socket = socket;
		this.nick = String.format("[%s] ", nick);
	}
	
	public void run() {
		DataOutputStream dos = null;
		Scanner scn = null;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
			scn = new Scanner(System.in);
			String line;
			while((line = scn.nextLine())!=null) {
				dos.writeUTF(nick + line);
			}
			socket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try{ dos.close(); scn.close(); }catch(Exception e) {}
		}
	}
}
