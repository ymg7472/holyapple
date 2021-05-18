package designPattern.factoryMethodPattern;

/**
 * <pre>
 * designPattern.factoryMethodPattern 
 * FactoryMain.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2021. 4. 12.
 * @author : ymg74
 * @version : v1.0
 */
public class FactoryMain {
	public static void main(String[] args) {
		Factory factory = new IDCardFactory();
		Product card1 = factory.create("코야");
		Product card2 = factory.create("레종");
		Product card3 = factory.create("유키");
		card1.use();
		card2.use();
		card3.use();
	}
}