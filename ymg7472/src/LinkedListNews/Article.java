package LinkedListNews; 

/**
 * <pre>
 * LinkedListNews 
 * Article.java
 *
 * ���� :
 * </pre>
 * 
 * @since : 2020. 7. 19.
 * @author : ymg74
 * @version : v1.0
 */
public class Article{

	public LinkedList sentenceList;

	public LinkedList getSentence() {
		return sentenceList;
	}

	public void setSentence(LinkedList sentenceList) {
		this.sentenceList = sentenceList;
	}
}
