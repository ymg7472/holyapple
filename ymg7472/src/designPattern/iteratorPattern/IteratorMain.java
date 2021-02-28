package designPattern.iteratorPattern;

/**
 * <pre>
 * iteratorPattern 
 * IteratorMain.java
 *
 * 설명 : 책꽂이에 책을 넣은 후, 순서대로 하나씩 꺼내 책 이름을 출력하는 프로그램
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class IteratorMain {
	public static void main(String[] args) {
		BookShelf bookShelf = new BookShelf(4);
		bookShelf.addBook(new Book("자바스크립트"));
		bookShelf.addBook(new Book("파이썬"));
		bookShelf.addBook(new Book("디자인패턴"));
		bookShelf.addBook(new Book("자바"));
		Iterator it = bookShelf.iterator();
		while (it.hasNext()) {
			Book book = (Book)it.next();
			System.out.println(book.getName());
		}
	}
}