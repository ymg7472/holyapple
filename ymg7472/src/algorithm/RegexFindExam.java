package algorithm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <pre>
 * algorithm 
 * RegexFindExam.java
 *
 * 설명 : 정규표현식
 * 
 * </pre>
 * 
 * @since : 2020. 5. 24.
 * @author : ymg74
 * @version : v1.0
 */
public class RegexFindExam {

	public static void main(String[] args) {

		String content = "https://news.naver.com/main/read.nhn?mode=LS2D&mid=shm&sid1=100&sid2=269&oid=003&aid=0009650125";

		System.out.println(content);

		System.out.println("\n========== 정규표현으로 문자열 검색 ==================");

		// [name] = value 와 같은 패턴으로 되어 있는 문자열을 검색하는 정규표현
		Pattern pattern = Pattern.compile("(?<=(&oid=|\\?oid=)|(&aid=|\\?aid=))[\\d]+"); 
		Matcher matcher = pattern.matcher(content);

		// 정규 표현에 검색된 문자열 구하기
		// find() 메소드가 false 반환할 때까지 반복 
		while(matcher.find()) {
			//group() 메소드를 호출하고 정규 표현에 일치된 문자열을 꺼냄
			System.out.println(matcher.group());
			
		}
	}
}
