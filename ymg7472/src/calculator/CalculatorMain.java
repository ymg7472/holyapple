package calculator;
import java.util.Scanner;;
public class CalculatorMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int realnum1 = 0;
		int realnum2 = 0;
		int finalnum = 0;
		int dustks = 0;
		int dustks1 = 0;
		String yunsanza = "";
		String num = "";
		String recipe = "";
		while(true) {
			System.out.println("?");
			Scanner s = new Scanner(System.in);
			String answer = s.next();
			if(answer.equals("+")  || answer.equals("-") || answer.equals("*") ||
					answer.equals("/")) {
				realnum1 = Integer.parseInt(num);
				yunsanza = answer;
				recipe = recipe + num;
				num = "";
				dustks++;
			}
			else if((answer.equals("+")  || answer.equals("-") ||
					answer.equals("*") || answer.equals("/")) && dustks>0) {
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
		}
	}

}