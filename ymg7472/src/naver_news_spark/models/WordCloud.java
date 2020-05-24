package naver_news_spark.models;

/**
 * <pre>
 * calender_crawl 
 * WordCloud.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 5. 24.
 * @author : ymg74
 * @version : v1.0
 */
public class WordCloud {
	
	 private String text;
	 private int size;
	 
	public WordCloud(String key, Integer value) {
		// TODO Auto-generated constructor stub
		this.text = key;
		this.size = value;
		
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
