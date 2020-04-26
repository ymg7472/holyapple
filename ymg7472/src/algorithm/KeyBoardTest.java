package algorithm;


import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

import java.util.*;



public class KeyBoardTest extends JPanel

{

	private Toolkit toolkit;

	private int x, y;

	private Image img;

	private int delay, frame;





	private KeyBoardTestListenter KeyL;



	public KeyBoardTest(){

		setPreferredSize(new Dimension(630, 300));

		setBackground(Color.BLUE);

		setLayout(null);

		//setBounds(10, 10);

		KeyL = new KeyBoardTestListenter();

		addKeyListener(KeyL);

		setFocusable(true);



		x = 100;

		y = 100;



		toolkit = this.getToolkit();



		img = toolkit.getImage("Images/frame1.gif");



		//repaint();

	}



	public void paintComponent(Graphics page)

	{

		super.paintComponent(page);



		page.drawImage(img, x, y,40,40, this);

	}







	private class KeyBoardTestListenter implements KeyListener

	{



		public void keyReleased(KeyEvent key)

		{



		}



		public void keyTyped(KeyEvent key)

		{



		}





		public void keyPressed(KeyEvent key)

		{

			int keyCode = key.getKeyCode();

			String strKey = KeyEvent.getKeyText(keyCode);



			System.out.println("Pressed Up");



			if( strKey.equals("Up") )

			{

				System.out.println("Pressed Up");

				if( y > 0 )

				{

					y -= 10;

				}



				repaint();

			}

			else if( strKey.equals("Down") )

			{

				System.out.println("Pressed Down");

				if( y < 260 )

				{

					y += 10;

				}

				repaint();

			}

			else if( strKey.equals("Left") )

			{

				System.out.println("Pressed Left");

				if( x > 0)

				{

					x -= 10;

				}

				repaint();

			}

			else if( strKey.equals("Right") )

			{

				System.out.println("Pressed Right");

				if( x < 580 )

				{

					x += 10;

				}



				repaint();

			}

		}

	}

}
