package canvas;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

//객체출력을 위해 Sirializable을 구현해서 객체직렬화시키는 인터페이스 (WriteObject)를 하기위해서 필수적인 부분
abstract class Shape implements Serializable{
   Color color;
   int lastX;
   int lastY;
   int startX;
   int startY;

   void draw(Graphics g){}

   void draw(Graphics g, int i, int j, int k, int l){}

   void setCoordinate(int x, int y, int x2, int y2) {
      startX = x;
      startY = y;
      lastX = x2;
      lastY = y2;
   }

   void setColor(Color c) {
      color = c;
   }

   static Shape create(String shapeName)  
   {
      if(shapeName.equals("Line"))
         return new Line();
      if(shapeName.equals("Oval"))
         return new Oval();
      if(shapeName.equals("Rect"))
         return new Rect();
      else
         return null;
   }
}

class Line extends Shape implements Serializable 
{
   void draw(Graphics g) 
   {
      g.setColor(color);
      g.drawLine(startX, startY, lastX, lastY);
   }

   void draw(Graphics g, int x, int y, int x1, int y1) 
   {
      g.setColor(color);
      g.drawLine(x, y, x1, y1);
   }
}

class Oval extends Shape implements Serializable
{
   void draw(Graphics g) 
   {
      g.setColor(color);
      g.drawOval(Math.min(startX, lastX), Math.min(startY, lastY), Math.abs(lastX - startX), Math.abs(lastY - startY));
   }

   void draw(Graphics g, int x, int y, int x1, int y1) 
   {
      g.setColor(color);
      g.drawOval(Math.min(x, x1), Math.min(y, y1), Math.abs(x - x1), Math.abs(y - y1));
   }
}

class Rect extends Shape implements Serializable
{
   void draw(Graphics g)
   {
      g.setColor(color);
      g.drawRect(Math.min(startX, lastX), Math.min(startY, lastY), Math.abs(lastX - startX), Math.abs(lastY - startY));
   }

   void draw(Graphics g, int x, int y, int x1, int y1)
   {
      g.setColor(color);
      g.drawRect(Math.min(x, x1), Math.min(y, y1), Math.abs(x - x1), Math.abs(y - y1));
   }
}

public class MyJFrame extends JFrame
{
   Container contentPane;
   int x,y;
   int startX;
   int startY;
   int lastX;
   int lastY;
   JButton fgButton;
   JButton bgButton;
   MyCanvas canvas;
   Shape currentShape;
   JLabel lblState;
   ArrayList<Shape> shapeList;
   String choice;

   MyJFrame(){
      choice = "Line";
      x = 0;
      y = 0;
      shapeList = new ArrayList<Shape>();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      contentPane = getContentPane();
      setBounds(100, 100, 750, 549);

      //create MenuBar
      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      JMenu mnFile = new JMenu("File");
      menuBar.add(mnFile);
      JMenuItem mntmSave = new JMenuItem("Save");
      mntmSave.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try
        		{
        			String resultStr;
        			resultStr = JOptionPane.showInputDialog(null,
  						"저장할 파일의 이름을 지정하세요.\r\n(확장자포함)", "저장",
  						JOptionPane.INFORMATION_MESSAGE);
        			String a = resultStr;
  				FileOutputStream f = new FileOutputStream(a); //a이름을 가지는 outputStream 생성
  				ObjectOutputStream of = new ObjectOutputStream(f); //ObjectOutputStream 생성
  				Color c = canvas.getBackground();
  				of.writeObject(c);
  				for(int i = 0 ;i < shapeList.size(); i++)
  				{
  					Shape s = shapeList.get(i);
  					of.writeObject(s);
  				}
  				of.close();
  			} 
  			catch(IOException e1)
        	{ //파일 오류에 관한 예외처리
  				System.out.println(e1.getMessage());
        	}
        }
      });
      mnFile.add(mntmSave);
      JMenuItem mntmLoad = new JMenuItem("Load");
      mntmLoad.addActionListener(new ActionListener()
      {
      	public void actionPerformed(ActionEvent e) 
      	{
      		for(int q = 0 ; q < shapeList.size();)
      		{
      			shapeList.remove(q);
      		}//이전에있는 객체들 모두 초기화 ( 그림판 화면 초기화 시키는 부분 )
    		Graphics g = (Graphics)canvas.getGraphics();
      		ArrayList<Shape> temp = new ArrayList<Shape>();
      		int i = 0;
			JFileChooser jfc = new JFileChooser("C://Users//김문섭//workspace//그래픽");
			jfc.showDialog(null, "확인");
			File file = jfc.getSelectedFile(); //파일 선택창 선언
			try  
			{
				FileInputStream f = new FileInputStream(file);					
				ObjectInputStream of = new ObjectInputStream(f);
				
				Color c = (Color) of.readObject(); //읽은 파일을 ArrayList 타입으로 casting
				canvas.setBackground(c);
				Shape li = (Shape) of.readObject();
				while(li != null)
				{
					Shape s = li;
					shapeList.add(i, li);
					li = (Shape) of.readObject();
				}
				of.close();
			} 
	        catch(IOException e1)
	        {
	            for(int a = 0; a<shapeList.size(); a++)
	            {
	            	canvas.paint(g); //읽어온 데이터들을 그림판에 그려주는 부분
	            }
	        	return;
	        } catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
      	}
      });
      mnFile.add(mntmLoad);
      JMenuItem mntmQuit = new JMenuItem("Quit");
      mnFile.add(mntmQuit);
      JMenu mnEdit = new JMenu("Edit");
      menuBar.add(mnEdit);
      JMenuItem mntmLine = new JMenuItem("Line");
      mnEdit.add(mntmLine);
      JMenuItem mntmOval = new JMenuItem("Oval");
      mnEdit.add(mntmOval);
      JMenuItem mntmRect = new JMenuItem("Rect");
      mnEdit.add(mntmRect);
      JMenu mnHelp = new JMenu("Help");
      menuBar.add(mnHelp);
      JMenuItem mntmAbout = new JMenuItem("About");
      mnHelp.add(mntmAbout);
      contentPane = new JPanel();
      ((JComponent) contentPane).setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);

      canvas = new MyCanvas();
      contentPane.add(canvas, "Center");
      CanvasMouseListener l = new CanvasMouseListener();
      canvas.addMouseListener(l);
      canvas.addMouseMotionListener(l);
      canvas.setBackground(Color.white);
      canvas.setForeground(Color.black);
      lblState = new JLabel();
      contentPane.add(lblState, "South");

      //create ToolBar
      JToolBar toolBar = new JToolBar();
      contentPane.add(toolBar, "North");
      JButton btnLine = new JButton("Line");
      btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JButton b = (JButton)arg0.getSource();
				choice = b.getText();
				xydisplay();
			}
		});
      toolBar.add(btnLine);
      JButton btnOval = new JButton("Oval");
		btnOval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JButton b = (JButton)arg0.getSource();
				choice = b.getText();
				xydisplay();
			}
		});
      toolBar.add(btnOval);
      JButton btnRect = new JButton("Rect");
      btnRect.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
      		JButton b = (JButton)e.getSource();
			choice = b.getText();
			xydisplay();
      	}
      });
      toolBar.add(btnRect);
      JSeparator separator = new JSeparator();
      separator.setOrientation(1);
      toolBar.add(separator);
      JLabel lblForeground = new JLabel(" ForeGround ");
      toolBar.add(lblForeground);
      fgButton = new JButton("     ");
      fgButton.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
			JButton b = (JButton)e.getSource();
			Color c = JColorChooser.showDialog(b, "Foreground 선택", canvas.getForeground());
		    fgButton.setBackground(c);
		    canvas.setForeground(c);
      	}
      });
      fgButton.setBackground(canvas.getForeground());
      toolBar.add(fgButton);
      JLabel lblBackground = new JLabel(" BackGround ");
      toolBar.add(lblBackground);
      bgButton = new JButton("     ");
      bgButton.addMouseListener(new MouseAdapter() {
      	@Override
      	public void mouseClicked(MouseEvent e) {
			JButton b = (JButton)e.getSource();
			Color c = JColorChooser.showDialog(b, "Background 선택", canvas.getBackground());
		    bgButton.setBackground(c);
		    canvas.setBackground(c);
      	}
      });
      bgButton.setBackground(canvas.getBackground());
      toolBar.add(bgButton);
      JSeparator separator_1 = new JSeparator();
      separator_1.setOrientation(SwingConstants.VERTICAL);
      toolBar.add(separator_1);

      setVisible(true);
   }

   class MyCanvas extends Canvas
   {
      public void paint(Graphics g) 
      {
         super.paint(g);
         for(int i = 0; i < shapeList.size(); i++)
         {
            Shape s = (Shape)shapeList.get(i);
            s.draw(g);
         }
      }
   }
   
   class CanvasMouseListener implements MouseListener, MouseMotionListener{
      public void mouseEntered(MouseEvent e) {}
      public void mouseExited(MouseEvent e) {}
      public void mousePressed(MouseEvent e) {
         currentShape = Shape.create(choice);
         currentShape.color = canvas.getForeground();

         x=startX = lastX = e.getX();
         y=startY = lastY = e.getY();
         
         Graphics g = ((Canvas) e.getSource()).getGraphics();
         g.setXORMode(Color.lightGray);
         currentShape.draw(g, startX, startY, lastX, lastY);
         xydisplay();
      }
      public void mouseReleased(MouseEvent e) {
         Graphics g = ((Canvas) e.getSource()).getGraphics();
         g.setColor(canvas.getForeground());
         
         x = lastX = e.getX();
         y = lastY = e.getY();
         
         currentShape.draw(g, startX, startY, lastX, lastY);
         currentShape.setCoordinate(startX, startY, lastX, lastY);
         shapeList.add(currentShape);
      }
      public void mouseClicked(MouseEvent e) {}
      public void mouseDragged(MouseEvent e) {
         x = e.getX();
         y = e.getY();
         xydisplay();

         Graphics g = ((Canvas) e.getSource()).getGraphics();
         g.setXORMode(Color.lightGray);
         
         currentShape.draw(g, startX, startY, lastX, lastY);
         currentShape.draw(g, startX, startY, x, y);
         
         lastX = x;
         lastY = y;
      }
      public void mouseMoved(MouseEvent e) {
         lastX = x = e.getX();
         lastY = y = e.getY();
         xydisplay();
      }
   }

   private void xydisplay() {
      lblState.setText("Selected : " + choice + " [ X : " + x + " y : " + y +"]" + " ,color : " + canvas.getForeground().toString());
   }

   public static void main(String[] args) {
      new MyJFrame();
   }
}