package LinkedListNews; 



import java.util.ArrayList;
import java.util.Iterator;


public class LinkedList {
	private Node head;

	public LinkedList(){
		head = null;
	}
	public boolean isEmpty(){
		return head == null;
	}
	public Node get(int index) {
	    Node node = head;
	    for (int i = 0; i < index; i++)
	        node = node.next;
	    return node;
	}
	public void addFirst(String value){
		Node link = new Node(value);
		link.next = head;		//	새로 추가하는 노드의 next를 앞 노드로 지정
		head = link;				//	새로 추가하는 노드를 first로 지정
	}
	public void addLast(String value){
		Node link = new Node(value);

		//	마지막까지 보내는 구문
		Node tmpLink = head;
		Node lastLink = null;
		while(tmpLink != null) {
			lastLink = tmpLink;
			tmpLink = tmpLink.next;
		}
		if(lastLink == null) head = link;
		else lastLink.next = link;				//	새로 추가하는 노드를 first로 지정
	}
	public void add(int index, String value){
		// index가 0이면 첫번째 노드에 추가
		if(index == 0){
			addFirst(value);
		} else {
			Node previosNode = get(index-1);	//	추가할 인덱스 앞 요소(이전노드)
			Node nextNode = previosNode.next;	//	이전노드의 링크 노드는 새로운 노드의 링크가 되어야 함
			Node newNode = new Node(value);
			previosNode.next = newNode;		//	이전노드의 링크 노드는 새로운 노드
			newNode.next = nextNode;		//	새로운 노드의 링크는 이전노드가 가르켰던 노드
		}
	}
	public int size(){
		int count = 0;
		while(!isEmpty()) {
			deleteFirst();
			count++;
		}
		return count;
	}
	public Iterator<String> listIterator(){
		ArrayList<String> list = new ArrayList<String>();
		while(!isEmpty()) {
			Node delLink = deleteFirst();
			list.add(delLink.data);
		}
		return list.iterator();
	}
	public Node deleteFirst(){
		Node link = head;	//	제일 앞의 first부터 값을 리턴
		if(head == null){
			return null;
		}
		head = head.next;
		return link;
	}
	public Object delete(int index){
	    if(index == 0)
	        return deleteFirst();
	    Node previosNode = get(index-1);				//	삭제할 인덱스 앞 요소(이전노드)
	    Node deleteNode = previosNode.next;				//	이전노드의 링크 노드는 삭제할 노드, 지금 삭제하면 노드를 연결할 수 없다. 
	    previosNode.next = deleteNode.next;				//	삭제할 노드의 링크노드가 이전노드의 링크노드가 되어야 삭제할 노드와의 연결이 끊어진다.
	    Object returnValue = deleteNode.data; 			//	삭제할 노드의 값을 리턴하기 위해 저장
	    return returnValue;
	}
	public void print() {
		Node link = head;
		while(link != null) {
			link.print();
			link = link.next;
		}
		System.out.println();
	}
}