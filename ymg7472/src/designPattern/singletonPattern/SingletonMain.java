package designPattern.singletonPattern;



/** code template
 * <pre>
 * singletonPattern 
 * Main.java
 *
 * 설명 : 싱글톤 패턴 메인 
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class SingletonMain {
	public static void main(String[] args) {
		System.out.println("Start");
		Singleton obj1 = Singleton.getInstance();
		Singleton obj2 = Singleton.getInstance();
		if(obj1 == obj2){
			System.out.println("obj1 과 obj2 는 같은 인스턴스입니다.");
		}else{
			System.out.println("obj1 과 obj2 는 다른 인스턴스입니다.");
		}
		System.out.println("End");
	}
}