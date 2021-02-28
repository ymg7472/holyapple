package designPattern.builderPattern;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * <pre>
 * builderPattern 
 * HTMLBuilder.java
 *
 * 설명 :HTML파일을 사용하여 문서를 만드는 클래스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class HTMLBuilder extends Builder {
	private String filename;
	private PrintWriter writer;
	// FileInputStream, FileOutpotStream
	public void makeTitle(String title){
		filename = title + ".html";
		try{
			writer = new PrintWriter(new FileWriter(filename));
		}catch(IOException e){
			e.printStackTrace();
		}
		writer.println("<html><head><title>" + title + "</title></head><body>");
		writer.println(("<h1>" + title + "</h1>"));
	}
	public void makeString(String str){
		writer.println("<p>" + str + "</p>");                       
    }
    public void makeItems(String[] items) {                    
        writer.println("<ul>");                                     
        for (int i = 0; i < items.length; i++) {
            writer.println("<li>" + items[i] + "</li>");
        }
        writer.println("</ul>");
    }
    public Object getResult() {                                
        writer.println("</body></html>");                          
        writer.close();                                             
        return filename;                                           
    }
}