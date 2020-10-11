package bus; 

import org.apache.log4j.Logger;

/**
 * <pre>
 * bus 
 * Bus.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 9. 20.
 * @author : ymg74
 * @version : v1.0
 */
public class Bus {

	private String districtCd;
	private String regionName;
	private String routeId;
	private String routeName;
	private String routeTypeCd;
	private String routeTypeName;
	public String getDistrictCd() {
		return districtCd;
	}
	public void setDistrictCd(String districtCd) {
		this.districtCd = districtCd;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getRouteId() {
		return routeId;
	}
	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getRouteTypeCd() {
		return routeTypeCd;
	}
	public void setRouteTypeCd(String routeTypeCd) {
		this.routeTypeCd = routeTypeCd;
	}
	public String getRouteTypeName() {
		return routeTypeName;
	}
	public void setRouteTypeName(String routeTypeName) {
		this.routeTypeName = routeTypeName;
	}
	@Override
	public String toString() {
		return "Bus [districtCd=" + districtCd + ", regionName=" + regionName + ", routeId=" + routeId + ", routeName="
				+ routeName + ", routeTypeCd=" + routeTypeCd + ", routeTypeName=" + routeTypeName + "]";
	}

}
