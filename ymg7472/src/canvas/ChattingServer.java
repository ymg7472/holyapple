package canvas; 

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gson.Gson;

/**
 * <pre>
 * kr.co.swh.lecture.network.chat 
 * ChattingServer.java
 *
 * 설명 :채팅 서버 프로그램, 소켓을 관리
 * </pre>
 * 
 * @since : 2018. 12. 28.
 * @author : tobby48
 * @version : v1.0
 */
public class ChattingServer extends Thread{
    Set<ServerThread> sockets = new HashSet<>();
    int port;
    public ChattingServer(int port) {
    	this.port = port;
    }
    public void run(){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            while(true){
                Socket s = serverSocket.accept();
                ServerThread t = new ServerThread(this, s);
                t.start();
                sockets.add(t);
            }
        }catch(IOException e){
            System.err.println("서버 에러");
            e.printStackTrace();
        }finally{
            try{ serverSocket.close(); } catch(Exception e) {}
        }
    }
    
    public void broadcast(String message){
        Iterator<ServerThread> it = sockets.iterator();
        while(it.hasNext()){
            ServerThread peer = it.next();
            peer.sendMessage(message);
        }
    }
    public void announce(ArrayList<String> us){
        Iterator<ServerThread> it = sockets.iterator();
        Gson gson= new Gson();
        while(it.hasNext()){
            ServerThread peer = it.next();
            
            peer.sendMessage(gson.toJson(us));
        }
    }
    public static void main(String[] args) {
    	ChattingServer server1 = new ChattingServer(1234);
    	server1.start();
    	ChattingServer server2 = new ChattingServer(5678);
    	server2.start();
    }
}
