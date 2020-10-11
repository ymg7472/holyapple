package rabbitMqPost;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <pre>
 * rabbitMqPost 
 * TestAsd.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 10. 9.
 * @author : ymg74
 * @version : v1.0
 */
public class TestAsd {
	public static void main(String[] args) throws IOException {
		String asd1 = "asdasdasd";
		byte[] asd2 = asd1.getBytes();
		System.out.println(new String(asd2));

	}

}
