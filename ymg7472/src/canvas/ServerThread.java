package canvas; 

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerThread extends Thread{
	private Socket socket;
	private ChattingServer server;
	private OutputStream os;
	static String answer;
	private MessageType type;
	public static String getInitial(String fullStr){
		String resultStr="";
		for (int i = 0; i < fullStr.length(); i++) {
			char comVal = (char) (fullStr.charAt(i)-0xAC00);
			if (comVal >= 0 && comVal <= 11172){
				char uniVal = (char)comVal;
				char cho = (char) ((((uniVal - (uniVal % 28)) / 28) / 21) + 0x1100);
				if(cho!=4519){
					resultStr =resultStr + cho;
				}
			} else {
				comVal = (char) (comVal+0xAC00);
				resultStr =resultStr + comVal;			
			}
		}
		return resultStr;
	}
	public ServerThread(ChattingServer server, Socket s, MessageType type){
		this.server = server;
		this.socket = s;
		this.type = type;
		System.out.println("connected : " + s.getInetAddress().getHostAddress());
	}
	ArrayList<String> us = new ArrayList<String>();
	Gson gson = new Gson();
	WordPicker wp = new WordPicker();
	ArrayList<String> word = wp.getWordList();
	public void run(){
		InputStream is = null;
		DataInputStream dis = null;
		try{
			is = socket.getInputStream();
			dis = new DataInputStream(is);
			String line;
			while(true){
				line = dis.readUTF();  
				Content content = gson.fromJson(line, Content.class);
				if(content.getType().equals("connected")) {
					ChattingServer.userList.add(content.getContent());
					Content send = new Content("connected", gson.toJson(ChattingServer.userList));
					System.out.println(gson.toJson(send));
					server.broadcast(gson.toJson(send));
				}
				else if(content.getType().equals("msg")){
					server.broadcast(line);
					if(content.getContent().split(":")[1].trim().equals(answer)) {
						server.answer();
					}

				}
				else if(content.getType().equals("quiz")) {
					answer = word.remove(0);
					content = new Content("quiz", answer);
					Content noDraw = new Content("quiz", getInitial(answer).substring(0, answer.length()));
					server.announce(gson.toJson(content), gson.toJson(noDraw));
				}
				else {
					server.broadcast(content.getContent());
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}finally{


		}
	}

	public void sendMessage(String message){
		try {
			os = socket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeUTF(message);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
}
