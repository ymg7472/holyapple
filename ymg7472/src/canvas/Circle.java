package canvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Circle extends JPanel{
	private List<List<Point>> point = new ArrayList<List<Point>>();
	DataOutputStream dos = null;
	public Circle(Socket socket) {
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent event) {
				List<Point> list = new ArrayList<Point>();
				point.add(list);
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent event) {
				point.get(point.size()-1).add(event.getPoint());
				Gson g = new Gson();
				String t = g.toJson(point);
				try {
					dos.writeUTF(t);

				}catch(IOException e) {
					e.printStackTrace();
				}finally {
//					try{ 
//						dos.close();  
//					}catch(Exception e) {}
				}
			}
		});
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(new Color(255, 0, 125));
		g2.setStroke(new BasicStroke(15f,
				BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		for (int i = 0; i < point.size(); i++) {
			List<Point> l = point.get(i);
			for (int j = 1; j < l.size(); j++) {
				g2.draw(new Line2D.Float(l.get(j-1), l.get(j)));
			}
		}	 	
	}
	class ChattingClientInputThread extends Thread{
		private Socket socket;

		public ChattingClientInputThread(Socket socket) {
			this.socket = socket;
		}
		
		public void run() {
			Gson gson = new Gson();
			DataInputStream dis = null;
			try {
				dis = new DataInputStream(socket.getInputStream());
				String line;
				while((line = dis.readUTF())!=null){
					Type type = new TypeToken<List<List<Point>>>(){}.getType();
					point = gson.fromJson(line, type);
				    repaint();
				}

			}catch(IOException e) {
				e.printStackTrace();
			}finally{
				try{ dis.close(); }catch(Exception e) {}
			}
		}
	}

	ChattingClientInputThread input = null;
	public void connect(Socket socket) throws UnknownHostException, IOException {
		//	입 출력 관련 스레드를 생성
		input = new ChattingClientInputThread(socket);
		//	스레드 동작
		input.start();
	}
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", 1234);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		Circle circle = new Circle(socket);
		try {
			circle.connect(socket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JFrame f = new JFrame();
		f.add(circle);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setVisible(true);
	
	}
}