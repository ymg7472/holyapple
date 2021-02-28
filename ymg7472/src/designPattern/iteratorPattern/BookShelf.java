package designPattern.iteratorPattern;
/**
 * <pre>
 * iteratorPattern 
 * BookShelf.java
 *
 * 설명 :서가를 나타내는 클래스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
class BookShelf implements Aggregate {
	private Book[] books;
	private int last = 0;

	public BookShelf(int maxsize){
		this.books = new Book[maxsize];
	}
	public Book getBook(int index){
		return books[index];
	}
	public void addBook(Book book){
		this.books[last] = book;
		last++;
	}
	public int getLength(){
		return last;
	}
	public Iterator iterator(){
		return new BookShelfIterator(this);
	}
}