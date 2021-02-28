package designPattern.singletonPattern;


/**
 * <pre>
 * singletonPattern 
 * Singleton.java
 *
 * 설명 : 인스턴스가 하나만 존재하는 클래스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class Singleton {

	private static Singleton singleton = new Singleton();

	private Singleton(){
		System.out.println("인스턴스를 생성했습니다.");
	}
	public static Singleton getInstance(){
		System.out.println("인스턴스를 반환합니다.");
		return singleton;
	}
}