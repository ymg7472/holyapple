package bus; 


import lombok.Data;

/**
 * <pre>
 * bus 
 * Station.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 9. 27.
 * @author : ymg74
 * @version : v1.0
 */
@Data
public class Station {

	private String flag;
	private String locationNo1;	
	private String locationNo2;
	private String lowPlate1;
	private String lowPlate2;
	private String plateNo1;
	private String plateNo2;
	private String predictTime1;
	private String predictTime2;
	private String remainSeatCnt1;
	private String remainSeatCnt2;

}
