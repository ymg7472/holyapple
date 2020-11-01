package canvas; 

import lombok.Data;

/**
 * <pre>
 * canvas 
 * asd.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 11. 1.
 * @author : ymg74
 * @version : v1.0
 */
@Data
public class Content {

	public Content(String type, String content) {
		this.type = type;
		this.content = content;
	}
	private String type;
	private String content;
}
