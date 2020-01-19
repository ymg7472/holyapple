package calender_crawl;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data

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
}
