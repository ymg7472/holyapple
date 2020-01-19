package algorithm;
import java.util.ArrayList;
import java.util.Scanner;
public class Algo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		int ing_num = s.nextInt();
		int st3 = 0;
		s.nextLine();
		String st1 = s.nextLine();
		String[] stock = st1.split(" ");
		String st2 = s.nextLine();
		String[] need = st2.split(" ");
		for(int i=0; i<ing_num-1; i++) {
			st3 = Integer.valueOf(stock[i])/Integer.valueOf(need[i]);
			if(Integer.valueOf(stock[i])/Integer.valueOf(need[i]) > Integer.valueOf(stock[i+1])/Integer.valueOf(need[i+1])) {
				st3 = Integer.valueOf(stock[i+1])/Integer.valueOf(need[i+1]);
			}
		}
		System.out.println(st3);
		s.close();
		
	}

}
