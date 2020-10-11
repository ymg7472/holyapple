package bus; 

import org.apache.log4j.Logger;

import lombok.Data;

/**
 * <pre>
 * bus 
 * BusLoc.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 9. 27.
 * @author : ymg74
 * @version : v1.0
 */
@Data
public class BusLoc {

	private String busLocationList;
	private String endBus;
	private String lowPlate;
	private String plateNo;
	private String plateType;
	private String remainSeatCnt;
	private String routeId;
	private String stationId;
	private String stationSeq;
	

}
