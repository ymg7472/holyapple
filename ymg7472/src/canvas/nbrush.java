package canvas; 
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;
import java.applet.*;

public class nbrush extends JFrame implements ActionListener {

	JLabel jl;
	JButton bLine, bRect, bOval;
	Color c;
	int x1, y1, x2, y2;
	String msg = " ";
	int mouseX = 0, mouseY = 0;
	JFileChooser fc;
	File file;

	public nbrush() {
		super("hello");

		Container cont = new Container();
		jl = new JLabel(" ");
		JPanel pbuttons = new JPanel();
		DrawingPanel drp = new DrawingPanel();
		//JPanel pDraw = new JPanel();
		JPanel pStatus = new JPanel();

		fc = new JFileChooser();

		pbuttons.setLayout(new GridLayout(9, 2));
		GridLayout gl = new GridLayout(9, 2);

		bLine = new JButton("line");
		bRect = new JButton("rect");
		bOval = new JButton("oval");


		pbuttons.add(bLine);
		pbuttons.add(bRect);
		pbuttons.add(bOval);

		JMenuBar mbr = new JMenuBar();
		setJMenuBar(mbr);
		JMenu file = new JMenu("FILE");
		JMenu edit = new JMenu("EDIT");
		JMenu view = new JMenu("VIEW");
		JMenu image = new JMenu("IMAGE");
		JMenu colors = new JMenu("COLORS");
		JMenu help = new JMenu("HELP");

		JMenuItem mit, mit1, mit2, mit3, mit4;
		file.add(mit = new JMenuItem("new"));
		file.add(mit1 = new JMenuItem("open"));
		file.add(mit2 = new JMenuItem("save"));
		file.add(mit3 = new JMenuItem("save as"));
		file.add(mit4 = new JMenuItem("exit"));

		JMenuItem mit5, mit6, mit7, mit8, mit9;
		edit.add(mit5 = new JMenuItem("undo"));
		edit.add(mit6 = new JMenuItem("redo"));
		edit.add(mit7 = new JMenuItem("cut"));
		edit.add(mit8 = new JMenuItem("copy"));
		edit.add(mit9 = new JMenuItem("paste"));

		JMenuItem bc;
		colors.add(bc = new JMenuItem("setbackground"));
		bc.addActionListener(new MyAction());

		bLine.addActionListener(drp);
		bRect.addActionListener(drp);
		bOval.addActionListener(drp);

		mit.addActionListener(new MyAction());
		mit1.addActionListener(new MyAction());
		mit2.addActionListener(new MyAction());
		mit3.addActionListener(new MyAction());
		mit4.addActionListener(new MyAction());
		mit5.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				jl.setText("undo is clicked");
			}
		});

		mit6.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				jl.setText("redo is clicked");
			}
		});

		mit7.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				//jl.setText("cut is clicked");

			}
		});
		mit8.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				jl.setText("copy as is clicked");
			}
		});
		mit9.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent ae) {
				jl.setText("paste is clicked");
			}
		});


		mbr.add(file);
		mbr.add(edit);
		mbr.add(view);
		mbr.add(image);
		mbr.add(colors);
		mbr.add(help);
		cont = getContentPane();
		cont.setLayout(new BorderLayout());
		cont.add(mbr, BorderLayout.NORTH);
		cont.add(pbuttons, BorderLayout.WEST);
		cont.add(drp,BorderLayout.CENTER);
		cont.add(jl, BorderLayout.SOUTH);


		setSize(660, 490);
		this.setResizable(true);
		setVisible(true);
	} //.......end of nbrush constructor





	class DrawingPanel extends JPanel implements ActionListener
	{
		int chr=0;

		DrawingPanel()
		{
			MouseHandler handler = new MouseHandler();
			this.addMouseListener(handler);
			this.addMouseMotionListener(handler);

			/*--------------------------------*/
			addWindowListener (
					new WindowAdapter ()
					{
						public void windowClosing (WindowEvent e)
						{
							System.exit(0);
						}

					}
					);

			setBackground (Color.white);
			setForeground(Color.black);

		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			switch (chr)
			{
			case 1:

				g.drawLine(x1,y1,x2,y2);

				break;

			case 2:

				g.drawRect(x1,y1,x2,y2);
				break;

			case 3:

				g.drawOval(x1,y1,x2,y2);

				break;

			}
		} // end paintComponent

		void clear()
		{
			repaint();
		}


		public void actionPerformed(ActionEvent ae)
		{
			String command = ae.getActionCommand();
			if (command.equals("line"))
			{
				chr=1;

			}
			if (command.equals("rect"))
			{
				chr=2;
			}
			if (command.equals("oval"))
			{
				chr=3;
			}
		}

	}

	/*-----------------------------------------------------------------------giving event sfor file,edit,...etc--------------*/
	public class MyAction implements ActionListener {

		public void actionPerformed(ActionEvent ae) {
			String str = ae.getActionCommand();
			if (str.equals("new")) {

			}
			if (str.equals("open")) {
				int returnVal = fc.showOpenDialog(nbrush.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
				} else {
				}
			}
			if (str.equals("save")) {
				int returnVal = fc.showSaveDialog(nbrush.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
				} else {
				}
			}

			if (str.equals("save as")) {
				int returnVal = fc.showSaveDialog(nbrush.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
				} else {
				}
			}
			if (str.equals("setbackground")) {
				Color c = JColorChooser.showDialog(
						nbrush.this, "Choose a color...", getBackground());
				if (c != null) {
					getContentPane().setBackground(c);
				}
			}
			if (str.equals("exit")) {
				System.exit(0);

			}
		}
	}
	/*----------------------------------------------------------------------------*/
	private class MouseHandler implements MouseListener, MouseMotionListener
	{
		public void mouseClicked(MouseEvent me)
		{
			//mouseX=0;
			//mouseY=0;
			jl.setText("mouse clicked");
			//repaint();
		}
		public void mouseEntered(MouseEvent me)
		{
			mouseX=0;
			mouseY=0;
			jl.setText("mouse enetered");
			//repaint();
		}
		public void mouseExited(MouseEvent me)
		{
			mouseX=0;
			mouseY=0;
			jl.setText("mouse exited");
			repaint();
		}
		public void mousePressed(MouseEvent me)
		{
			mouseX=me.getX();
			x1=mouseX;
			mouseY=me.getY();
			y1=mouseY;
			jl.setText( mouseX + " , " + mouseY);
			repaint();
		}
		public void mouseReleased(MouseEvent me)
		{
			mouseX=me.getX();
			x2=mouseX;
			mouseY=me.getY();
			y2=mouseY;
			jl.setText(mouseX + " , " + mouseY);
			repaint();
		}
		public void mouseDragged(MouseEvent me)
		{
			mouseX=me.getX();
			mouseY=me.getY();
			msg="*";
			jl.setText("Draggingmouse at " + mouseX + " , " + mouseY);
			//repaint();
		}
		public void mouseMoved(MouseEvent me)
		{
			jl.setText("movingmouse at " + me.getX() + " , " + me.getY());
		}
		public void paint(Graphics g)
		{
			g.drawString(" ",mouseX,mouseY);
		}
	}


	public void actionPerformed(ActionEvent ae) {
	}


	public static void main(String args[]) {
		nbrush dp = new nbrush();
		dp.setVisible(true);
		dp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}