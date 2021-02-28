package designPattern.iteratorPattern;

/**
 * <pre>
 * iteratorPattern 
 * Iterator.java
 *
 * 설명 : 집합체의 요소를 하나씩 꺼내오기 위한 인터페이스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public interface Iterator {
	public abstract boolean hasNext();
	public abstract Object next();
}