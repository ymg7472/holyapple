package canvas; 

import java.awt.List;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

/**
 * <pre>
 * canvas 
 * ChattingClientInputThread1.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 11. 1.
 * @author : ymg74
 * @version : v1.0
 */
public class ChattingClientInputThread1  extends Thread{
	private Socket socket;
	private MyPanel myPanel;
	
	public ChattingClientInputThread1(Socket socket, MyPanel myPanel) {
		this.socket = socket;
		this.myPanel = myPanel;
	}	
	ArrayList<String> us = new ArrayList<String>();
	
	public void run() {
		Gson gson = new Gson();
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(socket.getInputStream());
			String line;
			while((line = dis.readUTF())!=null){
				if(line.contains(":")) {
					myPanel.getChat_area().append(line + "\n" );
				}
				else {
					myPanel.getChat_area().append(line+ "has connected.");	
					myPanel.setUserList(us);
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try{ dis.close(); }catch(Exception e) {}
		}
	}
}