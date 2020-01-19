package Calculator;
import java.util.ArrayList;
import java.util.Scanner;
public class CalculatorMain {
	public static void main(String[] args) {
		int realnum1 = 0;
		int realnum2 = 0;
		int finalnum = 0;
		int dustks = 0;
		int dustks1 = 0;
		String yunsanza = "";
		String num = "";
		ArrayList<String> k = new ArrayList<>();
		k.add("+");
		k.add("-");
		k.add("*");
		k.add("/");
		while(true) {
			System.out.println("?");
			Scanner s = new Scanner(System.in);
			String answer = s.next();
			if(k.contains(answer)) {	
					realnum1 = Integer.parseInt(num);
					yunsanza = answer;
					num = "";
					dustks++;
			}
			else if(k.contains(answer) && dustks>0) {
				realnum1 = finalnum;
				yunsanza = answer;
				num = "";
			}
			else if (answer.equals("=") && dustks1>0) {
				realnum2 = Integer.parseInt(num);
				if (yunsanza.equals("+")) {
					finalnum = finalnum + realnum2;
					System.out.println(finalnum);
				}
				else if (yunsanza.equals("-")) {
					finalnum = finalnum - realnum2;
					System.out.println(finalnum);
				}
				else if (yunsanza.equals("*")) {
					finalnum = finalnum * realnum2;
					System.out.println(finalnum);
				}
				else if (yunsanza.equals("/")) {
					finalnum = finalnum / realnum2;
					System.out.println(finalnum);
				}
			}
			else if (answer.equals("=")) {
				realnum2 = Integer.parseInt(num);
				if (yunsanza.equals("+")) {
					finalnum = realnum1 + realnum2;
					System.out.println(finalnum);
				}
				else if (yunsanza.equals("-")) {
					finalnum = realnum1 - realnum2;
					System.out.println(finalnum);
				}
				else if (yunsanza.equals("*")) {
					finalnum = realnum1 * realnum2;
					System.out.println(finalnum);
				}
				else if (yunsanza.equals("/")) {
					finalnum = realnum1 / realnum2;
					System.out.println(finalnum);
				}
				dustks1++;
			}
			else if (answer.equals("d")) {
				num = num.substring(0, num.length()-1);
			}
			else {
				num = num + answer;
				System.out.println(num);
			}
			s.close();
		}
	}
}