package canvas; 


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;

import lombok.Data;


@Data
public class MyPanel extends JPanel {
	private ArrayList<String> userList = new ArrayList<String>();
    private JButton jcomp1;
    private JButton jcomp2;
    private JButton jcomp3;
    private JButton ok;
    private JTextField jcomp5;
    private JTextArea jcomp6;
    private JTextArea chat_area;
    private JScrollPane scroll;
    private JTextArea list;
    private JTextArea word;
    private JButton quizButton;
    private Paintbrush canvas;
    private JComboBox color;	
    private JTextField tf;
    
    private String uname;
    private Socket chatSocket;
    private Socket drawSocket;
    private DataOutputStream chatOutputStream = null;
    private Gson gson = new Gson();
    ChattingRecvThread chatRecvThread = null;
    
    
    class ChatSender implements ActionListener{
    	JTextField tf;
    	DataOutputStream dos;
    	String uname;
    	public ChatSender(JTextField tf, DataOutputStream dos,String uname){
    		this.tf = tf;
    		this.dos = dos;
    		this.uname = uname;
    	}
    	@Override
    	public void actionPerformed(ActionEvent e) {
    		String str  = tf.getText();
    		Content con = new Content("msg", uname+" : "+str);
    		str = gson.toJson(con);
    		try {
				dos.writeUTF(str);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
  
    		tf.setText("");
    	}
    }
    
    public void login(String[] unameList) {
    	list.setText("");
    	for(String name : unameList) {
    		System.out.println(name);
    		list.append(name + "\n");
    	}
    }
    public MyPanel(String uname) throws Exception {
    	this.uname = uname;
    	this.chatSocket = new Socket("127.0.0.1", 5678);// 채팅 포트
    	this.drawSocket = new Socket("127.0.0.1", 1234);//캔버스 포트
    	
       
        String[] colorItems = {"r", "g", "b"};


        jcomp1 = new JButton ("Button 1");
        jcomp2 = new JButton ("Button 2");
        jcomp3 = new JButton ("Button 3");
        jcomp5 = new JTextField (5);
        jcomp6 = new JTextArea (5, 5);
        ok = new JButton ("ok");
        chat_area = new JTextArea (5, 5);
        chat_area.setEditable(false);
        list = new JTextArea (5, 5);
        list.setEditable(false);
        word = new JTextArea (5, 5);
        word.setEditable(false);
        quizButton = new JButton ("퀴즈!");
        tf = new JTextField (5);
   
        
		canvas = new Paintbrush(drawSocket);
		try {
			canvas.connect(drawSocket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        color = new JComboBox (colorItems);
        color.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox jcb =(JComboBox)e.getSource();
                int index = jcb.getSelectedIndex();
                canvas.setColor(index);
            }
        });

        setPreferredSize (new Dimension (944, 740));
        setLayout (null);

        add (jcomp1);
        add (jcomp2);
        add (jcomp3);
        add (ok);
        add (jcomp5);
        add (jcomp6);
        add (chat_area);
        add (list);
        add (word);
        add(quizButton);
        add (canvas);
        add (color);
        add (tf);


        jcomp1.setBounds (-370, 100, 100, 20);
        jcomp2.setBounds (-390, 140, 100, 20);
        jcomp3.setBounds (-385, 135, 140, 20);
        ok.setBounds (860, 655, 65, 50);
        jcomp5.setBounds (-390, 290, 100, 25);
        jcomp6.setBounds (-280, 295, 100, 75);
        chat_area.setBounds (190, 505, 735, 150);
        list.setBounds (5, 505, 185, 200);
        word.setBounds (340, 5, 245, 55);
        quizButton.setBounds(600, 5, 90, 50);
        canvas.setBounds (5, 55, 930, 420);
        canvas.setBackground(Color.WHITE);
        color.setBounds (5, 480, 130, 25);
        tf.setBounds (190, 655, 670, 50);
    }
    
    
	public void connect(String uname) throws UnknownHostException, IOException {
		chatRecvThread = new ChattingRecvThread(this.chatSocket, this);
		chatRecvThread.start();
		try {
			chatOutputStream = new DataOutputStream(chatSocket.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        ChatSender l1 = new ChatSender(tf,chatOutputStream,uname);
        ok.addActionListener(l1);
		tf.addActionListener(l1);
		
		quizButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Content cont = new Content("quiz", "please!");
				Paintbrush.point.clear();
				try {
					chatOutputStream.writeUTF(gson.toJson(cont));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	
		Content content = new Content("connected", uname);
		String json = gson.toJson(content);
		chatOutputStream.writeUTF(json);
	}

    public static void main (String[] args) throws Exception {
    	JFrame frame1 = new JFrame("Get Username");
    	String uname = new String();
		uname = JOptionPane.showInputDialog(frame1, "Enter your Username :");
		if (uname == null) {
			uname = "Unknown";
		}
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        
        MyPanel mp = null;
		mp = new MyPanel(uname);
        mp.connect(uname);
		frame.getContentPane().add(mp);
        frame.pack();
        frame.setVisible (true);
    }
}
