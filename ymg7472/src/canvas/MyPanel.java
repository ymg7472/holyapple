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
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    private JTextArea list;
    private JTextArea word;
    private Paintbrush canvas;
    private JComboBox color;
    private JTextField tf;
    

    public void login(ArrayList<String> unameList) {
    	this.setUserList(unameList);
    	for(String name : userList) {
    		list.append(name + "\n");
    	}
    }
    DataOutputStream dos = null;

    public MyPanel(String uname, Socket soc2) throws Exception {
    	Socket soc1 = new Socket("127.0.0.1", 1234);
        try {
			dos = new DataOutputStream(soc2.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	class ChatListener implements ActionListener{
        	JTextField tf;
        	DataOutputStream dos;
        	String uname;
        	public ChatListener(JTextField tf, DataOutputStream dos,String uname){
        		this.tf = tf;
        		this.dos = dos;
        		this.uname = uname;
        	}
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String str  = tf.getText();
        		try {
					dos.writeUTF(uname+" : "+str);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        		tf.setText("");
        	}
        }

        String[] colorItems = {"r", "g", "b"};


        jcomp1 = new JButton ("Button 1");
        jcomp2 = new JButton ("Button 2");
        jcomp3 = new JButton ("Button 3");
        jcomp5 = new JTextField (5);
        jcomp6 = new JTextArea (5, 5);
        ok = new JButton ("ok");
        chat_area = new JTextArea (5, 5);
        chat_area.setEditable(false);
        chat_area.setAutoscrolls(true);
        list = new JTextArea (5, 5);
        word = new JTextArea (5, 5);
        tf = new JTextField (5);
   
        
        ChatListener l1 = new ChatListener(tf,dos,uname);
        ok.addActionListener(l1);
		tf.addActionListener(l1);

		canvas = new Paintbrush(soc1);
		try {
			canvas.connect(soc1);
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
        add (canvas);
        add (color);
        add (tf);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (-370, 100, 100, 20);
        jcomp2.setBounds (-390, 140, 100, 20);
        jcomp3.setBounds (-385, 135, 140, 20);
        ok.setBounds (860, 655, 65, 50);
        jcomp5.setBounds (-390, 290, 100, 25);
        jcomp6.setBounds (-280, 295, 100, 75);
        chat_area.setBounds (190, 505, 735, 150);
        list.setBounds (5, 505, 185, 200);
        word.setBounds (340, 5, 245, 55);
        canvas.setBounds (5, 55, 930, 420);
        canvas.setBackground(Color.WHITE);
        color.setBounds (5, 480, 130, 25);
        tf.setBounds (190, 655, 670, 50);
    }
    
    ChattingClientInputThread1 input = null;
	public void connect(Socket socket, String uname) throws UnknownHostException, IOException {
		//	입 출력 관련 스레드를 생성
		input = new ChattingClientInputThread1(socket, this);
		//	스레드 동작
		input.start();
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		dos.writeUTF(uname);
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
        Socket soc2 = new Socket("127.0.0.1", 5678);
        MyPanel mp = null;
		mp = new MyPanel(uname, soc2);
        mp.connect(soc2, uname);
		frame.getContentPane().add(mp);
        frame.pack();
        frame.setVisible (true);
    }
}
