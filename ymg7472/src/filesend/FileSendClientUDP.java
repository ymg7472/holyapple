package filesend;

import java.io.File;
import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * <pre>
 * kr.co.swh.lecture.network.filetrans 
 * FileSendClientUDP.java
 *
 * 설명 :파일 전송 클라이언트
 * </pre>
 * 
 * @since : 2018. 12. 28.
 * @author : tobby48
 * @version : v1.0
 */
public class FileSendClientUDP {
	public static final int DEFAULT_BUFFER_SIZE = 10000;
	public static void main(String[] args) {
		String serverIP = "127.0.0.1";
		int port = 9999;
		String FileName = "C:\\Users\\ymg74\\asd.txt";

		File file = new File(FileName);
		DatagramSocket ds = null;
		if (!file.exists()) {
			System.out.println("File not Exist");
			System.exit(0);
		}
		long fileSize = file.length();
		long totalReadBytes = 0;

		double startTime = 0;  
		try {
			ds = new DatagramSocket();
			InetAddress serverAdd = InetAddress.getByName(serverIP);
			startTime = System.currentTimeMillis();
			
			//	start 신호 보내는 파트
			String str = "start";
			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAdd, port);
			ds.send(dp);	//	start 신호 전송

			//	파일의 크기 보내는 파트
			str = String.valueOf(fileSize);
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAdd, port);
			ds.send(dp);	//	파일의 크기 전송

			//	파일의 내용을 읽어 서버로 전송하는 파트
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			while (true) {
				Thread.sleep(10);
				//	파일 내용 읽어오기
				int readBytes = fis.read(buffer, 0, buffer.length);
				if (readBytes == -1)
					break;
				//	파일의 내용을 서버로 전송
				dp = new DatagramPacket(buffer, readBytes, serverAdd, port); // *
				ds.send(dp);
				
				//	진행률 출력
				totalReadBytes += readBytes;
				System.out.println("In progress: " + totalReadBytes + "/"
						+ fileSize + " Byte(s) ("
						+ (totalReadBytes * 100 / fileSize) + " %)");

			}
			double endTime = System.currentTimeMillis();
			double diffTime = (endTime - startTime)/ 1000;;
			double transferSpeed = (fileSize / 1000)/ diffTime;

			System.out.println("time: " + diffTime+ " second(s)");
			System.out.println("Average transfer speed: " + transferSpeed + " KB/s");

			str = "end";
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, serverAdd, port);
			ds.send(dp);
			Thread.sleep(5000);
			System.out.println("Process Close");
			fis.close();
			ds.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}