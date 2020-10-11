package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;


import java.util.Comparator;
import java.util.List;
public class Nypc_1 {

	class type1 {
		int diff;
		int num;
		int my_stat;
		public void setDiff(int diff) {
			this.diff = diff;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public void setMy_stat(int my_stat) {
			this.my_stat = my_stat;
		}
		public int getNum() {
			return num;
		}
		public int getDiff() {
			return diff;
		}
		public int getMy_stat() {
			return my_stat;
		}

		@Override
		public String toString() {
			return String.valueOf(diff)+" , "+String.valueOf(num)+" , "+String.valueOf(my_stat);
		}
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int dumb = Integer.valueOf(sc.nextLine());
		String[] stat1 = sc.nextLine().split(" ");
		int how_many = Integer.valueOf(stat1[1]);
		int[] stat = Arrays.stream(stat1).mapToInt(Integer::parseInt).toArray();
		String[] users1 = sc.nextLine().split(" ");
		int[] users = Arrays.stream(users1).mapToInt(Integer::parseInt).toArray();
		sc.close();
		type1 tp = null;
		Nypc_1 np = null;
		ArrayList<type1> list1 = new ArrayList<type1>();
		List<type1> list2 = new ArrayList<type1>();
		type1[] list3 = new type1[dumb]; 
		for(int i = 0; i<users.length; i++) {
			np = new Nypc_1();
			tp = np.new type1();
			tp.setDiff(Math.abs(stat[0] - users[i]));
			tp.setNum(i+1);
			tp.setMy_stat(users[i]);
			list3[i] = tp;
		}
		
//		for(type1 s:list3) {
//			System.out.println(s);
//		}
//		System.out.println("-------------------");
		for(int i = 0; i<list3.length; i++) {
			for(int j = 0; j<list3.length-i-1; j++) {
				if(list3[j].getDiff() > list3[j+1].getDiff()) {
					type1 temp = list3[j+1];	
					list3[j+1] = list3[j];
					list3[j] = temp;
				}
			}
		}
		for(int i = 0; i<list3.length; i++) {
			for(int j = 0; j<list3.length-i-1; j++) {
				if(list3[j].getMy_stat() > list3[j+1].getMy_stat()) {
					if(list3[j].getDiff() == list3[j+1].getDiff()) {
						type1 temp = list3[j+1];	
						list3[j+1] = list3[j];
						list3[j] = temp;
					}
				}
			}
		}
		
		
//		Collections.reverse(list2);
		String result = "";
		for(int i = 0; i<how_many; i++) {
			result = result + String.valueOf(list3[i].getNum()) + " ";
		}
		System.out.println(result);
		


	}
}
