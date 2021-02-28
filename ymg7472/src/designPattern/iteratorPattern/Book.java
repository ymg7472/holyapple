package designPattern.iteratorPattern;

/**
 * <pre>
 * iteratorPattern 
 * Book.java
 *
 * 설명 : 책을 나타내는 클래스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class Book {
	private String name = "";

	public Book(String name) {
		this.name = name;
	}
	public String getName(){
		return name;
	}
}