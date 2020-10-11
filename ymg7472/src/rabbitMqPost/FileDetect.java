package rabbitMqPost; 

import java.io.File;
import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * <pre>
 * rabbitMqPost 
 * FileDetect.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 10. 11.
 * @author : ymg74
 * @version : v1.0
 */
public class FileDetect {

	public ArrayList<String> getFiles(){
		File folder = new File("C:\\text");
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> list = new ArrayList<String>();
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        list.add(file.getName());
		    }
		}
		return list;
	}
	public ArrayList<String> getDiff(ArrayList<String> list1){
		FileDetect fd = new FileDetect();
		ArrayList<String> list2 = fd.getFiles();
		if(list1.size()<list2.size()) {
			list2.removeAll(list1);
		}
		return list2;
	}
	public static void main(String[] args) {
		FileDetect fd = new FileDetect();
		ArrayList<String> list1 = fd.getFiles();
		while(true) {
			ArrayList<String> list2 = fd.getDiff(list1);
			for(String e : list2) {
				System.out.println(e);
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
