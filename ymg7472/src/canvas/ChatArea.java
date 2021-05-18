package canvas; 

import javax.swing.*;


import org.apache.log4j.Logger;

/**
 * <pre>
 * canvas 
 * ChatArea.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 11. 1.
 * @author : ymg74
 * @version : v1.0
 */
public class ChatArea extends JPanel{

	
	public static void main (String[] args) {
		JFrame f = new JFrame();
		ChatArea ca = new ChatArea();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(800, 600);
		f.setVisible(true);
	}
}
