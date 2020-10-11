package rabbitMqPost; 
import lombok.Data;

/**
 * <pre>
 * filesend 
 * Payload.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 10. 11.
 * @author : ymg74
 * @version : v1.0
 */
@Data
public class Payload {

	public Payload(String msg, byte[] content) {
		this.msg = msg;
		this.content = content;
	}
	
	private String msg;
	private byte[] content;

}
