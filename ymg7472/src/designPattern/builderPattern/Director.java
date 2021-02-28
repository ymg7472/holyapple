package designPattern.builderPattern;

/**
 * <pre>
 * builderPattern 
 * Director.java
 *
 * 설명 :하나의 문서를 만드는 클래스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class Director {
	private Builder builder;
	
	public Director(Builder builder){
		this.builder = builder;
	}
	public Object construct(){
		this.builder.makeTitle("Greeting");
		this.builder.makeString("아침과 낮에");
		this.builder.makeItems(new String[]{"좋은 아침입니다.", "안녕하세요"});
		this.builder.makeString("밤에");
		this.builder.makeItems(new String[]{"안녕하세요", "안녕히 주무세요", "안녕히 계세요"});
		return this.builder.getResult();
	}
}