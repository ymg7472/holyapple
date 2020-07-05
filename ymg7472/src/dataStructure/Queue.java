package dataStructure; 

import java.util.Arrays;

import org.apache.log4j.Logger;

/**
 * <pre>
 * dataStructure 
 * Queue.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 6. 28.
 * @author : ymg74
 * @version : v1.0
 */
public class Queue {
	private final static int MAX_SIZE = 10;
	int rear = -1;
	int front = MAX_SIZE;
	int[] queue;
	
	public Queue(){
		 queue = new int[MAX_SIZE];
	}
	
	public void enqueue(int num) {
		rear ++;
		front--;
		queue[rear] = num;
	}
	public int dequeue() {
		rear--;
		front++;
		return queue[front-1];
	}
	public boolean isEmpty() {
		if(rear == -1) return true; 
		return false;
	}
	public boolean isFull() {
		if(front == 0) return true;
		return false;
	}

	public int size() {
		return rear+1;
	}

	@Override
	public String toString() {
		return "Queue [rear=" + rear + ", front=" + front + ", queue=" + Arrays.toString(queue) + "]";
	}

}
