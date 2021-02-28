package designPattern.iteratorPattern;

/**
 * <pre>
 * iteratorPattern 
 * Aggregate.java
 *
 * 설명 : 집합체를 나타내는 인터페이스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public interface Aggregate {
	public abstract Iterator iterator();
}