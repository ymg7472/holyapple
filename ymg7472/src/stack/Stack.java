package stack; 


/**
 * <pre>
 * stack 
 * Stack.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 6. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class Stack {
	private final int MAX_SIZE = 50;
	private int top = -1;
	private int[] stack;
	
	public Stack(){
		 stack= new int[MAX_SIZE];
	}
	
	public void push(int i) {
		top++;
		stack[top] = i;
	}
	public boolean isEmpty() {
		if(top == -1) return true;
		return false;
	}
	public int pop() {
		int result = stack[top];
		top--;
		return result;
	}
	public int size() {
		return top+1;
	}
	



}
