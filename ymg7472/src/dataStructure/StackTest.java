package dataStructure; 

/**
 * <pre>
 * stack 
 * StackTest.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 6. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class StackTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Stack s = new Stack();
		for(int i = 0; i<10; i++) {
			s.push(i);
		}
		System.out.println(s.size());
		System.out.println(s.isEmpty());
		while(!s.isEmpty()) {
			System.out.println(s.pop());
		}
		System.out.println(s.size());
		System.out.println(s.isEmpty());

	}

}
