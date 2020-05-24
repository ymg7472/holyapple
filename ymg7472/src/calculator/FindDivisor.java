package calculator;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * <pre>
 * algorithm 
 * FindDivisor.java
 *
 * 설명 : 약수 찾기
 * </pre>
 * 
 * @since : 2020. 5. 24.
 * @author : ymg74
 * @version : v1.0
 */
public class FindDivisor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int tar = s.nextInt();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		for(int i = 1; i<tar; i++) {
			if(tar % i == 0) arr.add(i);
		}
		arr.add(tar);
		int temp = arr.size()-1;
		if(arr.size() % 2 == 0) {
			for(int i = 0; i<arr.size()/2; i++) {
				System.out.println(arr.get(i) + " * " + arr.get(temp));
				temp = temp - 1;
			}
		}else {
			int i = 0;
			for(i = 0; i<temp/2; i++) {
				System.out.println(arr.get(i) + " * " + arr.get(temp));
				temp = temp - 1;
			}
			System.out.println(arr.get(i) + " * " + arr.get(i));
		}
	}

}
