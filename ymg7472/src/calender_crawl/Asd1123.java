package calender_crawl;
import java.io.IOException;
public class Asd1123 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DatabaseUtil db = new DatabaseUtil("jdbc:mysql://dev-swh.ga/minkyu", "root", "swhacademy!");
		try {
			db.crawlNews("Á¤Ä¡", "20200523");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
