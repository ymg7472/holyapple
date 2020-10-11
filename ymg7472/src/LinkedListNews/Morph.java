package LinkedListNews; 

/**
 * <pre>
 * LinkedListNews 
 * Article.java
 *
 * ���� :
 * </pre>
 * 
 * @since : 2020. 7. 19.
 * @author : ymg74
 * @version : v1.0
 */
public class Morph extends Node{
	public String data;
	public Node next;

	public Morph(String data) {
		super(data);
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

//	public void print() {
//		System.out.print("{" + data + "}");
//	}
}
