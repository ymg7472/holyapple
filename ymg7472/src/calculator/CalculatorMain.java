package calculator;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * <pre>
 * calculator 
 * CalculatorMain.java
 *
 * 설명 : 간단한 계산기 였던것 같은데 이상하다
 * (수정필요)
 * </pre> 
 * 
 * @since : 2020. 5. 24.
 * @author : ymg74
 * @version : v1.0
 */
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
		Scanner s = new Scanner(System.in);
		System.out.println("?");
		String answer = s.next();
		while(true) {
			System.out.println("?");	
			answer = s.next();
			if(k.contains(answer) && dustks>0) {
				realnum1 = finalnum;
				yunsanza = answer;
				num = "";
			}
			else if(k.contains(answer)) {	
					realnum1 = Integer.parseInt(num);
					yunsanza = answer;
					num = "";
					dustks++;
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