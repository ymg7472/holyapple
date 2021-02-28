package canvas; 

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import com.google.gson.Gson;

public class ChattingRecvThread  extends Thread{
	private Socket socket;
	private MyPanel myPanel;
	public ChattingRecvThread(Socket socket, MyPanel myPanel) {
		this.socket = socket;
		this.myPanel = myPanel;
	}	
	ArrayList<String> us = new ArrayList<String>();
	public static boolean isJson(String str) {
		Gson gson = new Gson();
		try {
			gson.fromJson(str, Object.class);
			return true;
		} catch(com.google.gson.JsonSyntaxException ex) { 
			return false;
		}
	}
	public void run() {
		Gson gson = new Gson();
		DataInputStream dis = null;
		try {
			dis = new DataInputStream(socket.getInputStream());
			String line;
			while((line = dis.readUTF())!=null){
				Content content = gson.fromJson(line, Content.class);
				if(content.getType().equals("connected")) {
					String[] us = gson.fromJson(content.getContent(), String[].class);
					myPanel.getChat_area().append(us[us.length-1]+ " has connected. \n");	
					myPanel.login(us);
				}
				else if(content.getType().equals("quiz")) {
					myPanel.getWord().setText(content.getContent());
				}
				else {
					myPanel.getChat_area().append(content.getContent() + "\n" );
					myPanel.getChat_area().setCaretPosition(myPanel.getChat_area().getDocument().getLength());
				}
			}

		}catch(IOException e) {
			e.printStackTrace();
		}finally{
			try{ dis.close(); }catch(Exception e) {}
		}
	}
}