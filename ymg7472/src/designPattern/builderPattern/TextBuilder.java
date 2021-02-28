package designPattern.builderPattern;

/**
 * <pre>
 * builderPattern 
 * TextBuilder.java
 *
 * 설명 :일반 텍스트 파일을 사용하여 문서를 만드는 클래스
 * </pre>
 * 
 * @since : 2021. 2. 21.
 * @author : ymg74
 * @version : v1.0
 */
public class TextBuilder extends Builder {
	private StringBuffer buffer = new StringBuffer();
	
	public void makeTitle(String title){
		buffer.append("==============================\n");          
        buffer.append("『" + title + "』\n");                      
        buffer.append("\n");                                        
    }
	public void makeString(String str) {                       
    	buffer.append('■' + str + "\n");                           
    	buffer.append("\n");                                        
	}
    public void makeItems(String[] items) {                     
    	for(int i = 0; i < items.length; i++) {
    		buffer.append("●" + items[i] + "\n");                
    	}
    	buffer.append("\n");                                        
    }
    public Object getResult(){                                 
    	buffer.append("==============================\n");          
        return buffer.toString();                                   
    }
}