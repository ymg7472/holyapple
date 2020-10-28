package canvas; 

import org.apache.log4j.Logger;

import lombok.Data;

/**
 * <pre>
 * canvas 
 * sendPoint.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 10. 28.
 * @author : ymg74
 * @version : v1.0
 */
@Data
public class SendPoint {

	private String who;
	private String x;
	private String y;
	private String content_Type;
}
