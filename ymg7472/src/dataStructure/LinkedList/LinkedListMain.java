package dataStructure.LinkedList; 

public class LinkedListMain {
	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		linkedList.addLast(1);
		linkedList.addLast(2);
		linkedList.addLast(3);
		linkedList.addLast(4);
		linkedList.addLast(5);
		linkedList.add(2, 8);
		linkedList.delete(4);
		linkedList.addFirst(0);
		while(!linkedList.isEmpty()) {
			Node delLink = linkedList.deleteFirst();
			delLink.print();
			System.out.println();
		}
	}
}
