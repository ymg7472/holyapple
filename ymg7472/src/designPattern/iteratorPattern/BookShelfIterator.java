package designPattern.iteratorPattern;

/**
 * <pre>
 * iteratorPattern 
 * BookShelfIterator.java
 *
 * 설명 : 서가를 검색하는 클래스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class BookShelfIterator implements Iterator {
	private BookShelf bookShelf;
	private int index;

	public BookShelfIterator(BookShelf bookShelf) {
		this.bookShelf = bookShelf;
		this.index = 0;  
	}
	public boolean hasNext() {
		if (index < bookShelf.getLength()) {
			return true;
		} else {
			return false;
		}
	}
	public Object next() {
		Book book = bookShelf.getBook(index);
		index++;
		return book;
	}
}