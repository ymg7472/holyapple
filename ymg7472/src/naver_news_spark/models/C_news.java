package naver_news_spark.models;

//import lombok.Data;
//import lombok.EqualsAndHashCode;




public class C_news {

	private String id;	
	private String subject;
	private String title;
	private String contents;
	private String date;
	@Override
	public String toString() {
		return ("(id : " + id + ", subject : " + subject + ", title : " + title + ", contents :" + contents + ", date :" + date + ")");
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
}
