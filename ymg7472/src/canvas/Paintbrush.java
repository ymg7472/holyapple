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
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Paintbrush extends JPanel{
	
	static List<List<Point>> point = new ArrayList<List<Point>>();
	private Color color = Color.RED;
	Gson g = new Gson();
	DataOutputStream dos = null;
	String[] colorItems = {"r", "g", "b"};
    public Color getColor() {
        return color;
    }
    public void setColor(int color) {
        if(color == 0) {
        	this.color = Color.RED;
        }
        else if(color == 1) {
        	this.color = Color.GREEN;
        }
        else {
        	this.color = Color.BLUE;
        }
    }
	public Paintbrush(Socket socket) {
		super();
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
				
				String t = g.toJson(point);
				Content con = new Content("draw", t);
				try {
					dos.writeUTF(g.toJson(con));
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
		g2.setColor(getColor());
		g2.setStroke(new BasicStroke(15f,
				BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		for(List<Point> l : point) {
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
		input = new ChattingClientInputThread(socket);
		input.start();
	}
	public static void main(String[] args) {
	}
}