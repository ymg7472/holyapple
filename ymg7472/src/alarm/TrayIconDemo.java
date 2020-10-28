package alarm; 

import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;

public class TrayIconDemo {

       public static void main(String[] args) throws AWTException, MalformedURLException {
           if (SystemTray.isSupported()) {
               TrayIconDemo td = new TrayIconDemo();
               td.displayTray();
           } else {
               System.err.println("System tray not supported!");
           }
       }

       public void displayTray() throws AWTException, MalformedURLException {
           //Obtain only one instance of the SystemTray object
           SystemTray tray = SystemTray.getSystemTray();

           //If the icon is a file
//           Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
           //Alternative (if the icon is on the classpath):
           Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("1600460558.jpg"));

           TrayIcon trayIcon = new TrayIcon(image, "asd");
           //Let the system resize the image if needed
           trayIcon.setImageAutoSize(true);
           //Set tooltip text for the tray icon
           trayIcon.setToolTip("System tray icon demo");
           tray.add(trayIcon);

           trayIcon.displayMessage("수업 시작 알림", "a", MessageType.INFO);
       }
}