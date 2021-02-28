package designPattern.compositePattern;

/**
 * <pre>
 * kr.co.swh.lecture.engineering.scene3.composite
 * File.java
 *
 * 설명 :내용물에 해당하는 클래스
 * </pre>
 * 
 * @since : 2017. 10. 3.
 * @author : tobby48
 * @version : v1.0
 */
public class File extends Entry {
	private String name;
	private int size;

	public File(String name, int size){
		this.name = name;
		this.size = size;
	}
	public String getName(){
		return name;
	}
	public int getSize(){
		return size;
	}
	protected void printList(String prefix){
		System.out.println(prefix + "/" + this);
	}
}