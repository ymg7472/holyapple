package dataStructure; 

import org.apache.log4j.Logger;

/**
 * <pre>
 * dataStructure 
 * QueueTest.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 6. 28.
 * @author : ymg74
 * @version : v1.0
 */
public class QueueTest {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue q = new Queue();
		
		for(int i = 0; i<10; i++) {
			q.enqueue(i);
		}
		System.out.println(q.isEmpty());
		System.out.println(q.isFull());
		for(int i = 0; i<10; i++) {
			System.out.println(q.dequeue());
		}
		System.out.println(q.isEmpty());
		System.out.println(q.isFull());
		
		
		System.out.println("-----------------------------");
		
		
		for(int i = 0; i<10; i++) {
			q.enqueue(i);
		}
		System.out.println(q.isEmpty());
		System.out.println(q.isFull());
		for(int i = 0; i<10; i++) {
			System.out.println(q.dequeue());
		}
		System.out.println(q.isEmpty());
		System.out.println(q.isFull());

	}

}
