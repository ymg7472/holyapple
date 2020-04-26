package news_crawl;

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
