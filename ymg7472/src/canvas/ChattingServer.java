package canvas; 

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.google.gson.Gson;

public class ChattingServer extends Thread{
    Set<ServerThread> sockets = new HashSet<>();
    static ArrayList<String> userList = new ArrayList<String>();
    int port;
    Gson gson = new Gson();
    MessageType type;
    public ChattingServer(int port, MessageType type) {
    	this.port = port;
    	this.type = type;
    }
    public void run(){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(port);
            while(true){
                Socket s = serverSocket.accept();
                ServerThread t = new ServerThread(this, s, type);
                t.start();
                sockets.add(t);
            }
        }catch(IOException e){
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
    public void announce(String drawer, String not_drawer){
    	Iterator<ServerThread> it = sockets.iterator();
    	ServerThread peer1 = it.next();
        peer1.sendMessage(drawer);
        while(it.hasNext()){
            ServerThread peer2 = it.next();
            peer2.sendMessage(not_drawer);
        }
    }
    public void answer() {
    	Iterator<ServerThread> it = sockets.iterator();
    	Content cont = new Content("ans", "---------Right Answer------------");
    	while(it.hasNext()){
            ServerThread peer = it.next();
            peer.sendMessage(gson.toJson(cont));
        }
    }
    public static void main(String[] args) {
    	ChattingServer server1 = new ChattingServer(1234, MessageType.DRAW);
    	server1.start();
    	ChattingServer server2 = new ChattingServer(5678,  MessageType.CHAT);
    	server2.start();
    }
}
