package designPattern.builderPattern;

/**
 * <pre>
 * builderPattern 
 * BuilderMain.java
 *
 * 설명 : 일반 텍스트 파일과 HTML파일을 만드는 클래스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class BuilderMain {
	public static void main(String[] args){
		Director director;
		director = new Director(new TextBuilder());
		String result = (String)director.construct();
		System.out.println(result);

		director = new Director(new HTMLBuilder());
		String filename = (String)director.construct();
		System.out.println(filename + "이 작성되었습니다.");
	}
}