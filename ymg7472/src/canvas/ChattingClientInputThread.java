package canvas; 

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * <pre>
 * kr.co.swh.lecture.network.chat.client 
 * ChattingClientInputThread.java
 *
 * 설명 :클라이언트 입력 스레드
 * </pre>
 * 
 * @since : 2018. 12. 28.
 * @author : tobby48
 * @version : v1.0
 */
public class ChattingClientInputThread extends Thread{
	private Socket socket;

	public ChattingClientInputThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(socket.getInputStream());
			String line;
			while((line = dis.readUTF())!=null){
				System.out.println(line);
			}
			socket.close();
		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try{ dis.close(); }catch(Exception e) {}
		}
	}
}
