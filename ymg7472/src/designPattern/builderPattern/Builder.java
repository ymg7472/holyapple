package designPattern.builderPattern;

/**
 * <pre>
 * builderPattern 
 * Builder.java
 *
 * 설명 :문서를 구성하기 위해 메소드를 정의한 추상클래스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public abstract class Builder {
	public abstract void makeTitle(String title);
	public abstract void makeString(String str);
	public abstract void makeItems(String[] items);
	public abstract Object getResult();
}