package algorithm; 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;


/**
 * <pre>
 * algorithm 
 * ForTest.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 5. 31.
 * @author : ymg74
 * @version : v1.0
 */
public class ForTest {


	public static void main(String[] args) throws Exception {
		int c = 0, n = new Scanner(System.in).nextInt();
		for (int i=1; i<=n; c+=i++) System.out.printf("%d%c", i, i<n?'+':'=');
		System.out.print(c);
	}

}
