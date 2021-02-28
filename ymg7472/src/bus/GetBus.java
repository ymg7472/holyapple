package bus; 

import org.apache.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
/**
 * <pre>
 * bus 
 * GetBus.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 9. 20.
 * @author : ymg74
 * @version : v1.0
 */
public class GetBus {
	String[] cd = {"서울", "경기", "인천"};
	@SuppressWarnings("null")
	public ArrayList<Bus> getBusByNum(String num){
		ArrayList<Bus> bs = new ArrayList<Bus>();
		Bus bus = null;
		try {
			Connection conn = Jsoup.connect("http://openapi.gbis.go.kr/ws/rest/busrouteservice?serviceKey=I7Xj24QYwjxud5XK32dfP6iU%2BOtpvPlLNQZWdOrbdJI32hbJx61Y%2FqIG%2BESoQnch9TruGV02jZ9EI%2B8r2t3cZw%3D%3D&keyword="+num);
			Document doc = conn.get();
			Elements eles = doc.select("busRouteList");
			for (Element ele : eles) {
				bus = new Bus();
				bus.setDistrictCd(cd[Integer.valueOf(ele.select("districtCd").text())-1]);
				bus.setRegionName(ele.select("regionName").text());
				bus.setRouteId(ele.select("routeId").text());
				bus.setRouteName(ele.select("routeName").text());
				bus.setRouteTypeCd(ele.select("routeTypeCd").text());
				bus.setRouteTypeName(ele.select("routeTypeName").text());
				bs.add(bus);

			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return bs;
	}
	public ArrayList<BusRoute> getRouteByBus(String routeId){
		BusRoute bus = null;
		ArrayList<BusRoute> bs = new ArrayList<BusRoute>();
		try {
			Connection conn = Jsoup.connect("http://openapi.gbis.go.kr/ws/rest/busrouteservice/station?serviceKey=1234567890&routeId="+routeId);
			Document doc = conn.get();
			Elements eles = doc.select("busRouteStationList");
			for (Element ele : eles) {
				bus = new BusRoute();
				bus.setCenterYn(ele.select("centerYn").text());
				bus.setDistrictCd(cd[Integer.valueOf(ele.select("districtCd").text())-1]);
				bus.setMobileNo(ele.select("mobileNo").text());
				bus.setRegionName(ele.select("regionName").text());
				bus.setStationId(ele.select("stationId").text());
				bus.setStationName(ele.select("stationName").text());
				bus.setX(ele.select("x").text());
				bus.setY(ele.select("y").text());
				bus.setStationSeq(ele.select("stationSeq").text());
				bus.setTurnYn(ele.select("turnYn").text());
				bs.add(bus);

			}
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return bs;
	}
	@SuppressWarnings("null")
	public ArrayList<Object> getBusLoc(String routeId){
		ArrayList<Object> sd = new ArrayList<Object>();
		ArrayList<String> bs = new ArrayList<String>();
		ArrayList<String> sv = new ArrayList<String>();
		try {
			Connection conn = Jsoup.connect("http://openapi.gbis.go.kr/ws/rest/buslocationservice?serviceKey=1234567890&routeId="+routeId);
			Document doc = conn.get();
			Elements eles = doc.select("busLocationList");
			for (Element ele : eles) {
				bs.add(ele.select("stationSeq").text());
				sv.add(ele.select("plateNo").text());
			}
			sd.add(bs);
			sd.add(sv);
			
		} catch (Throwable e) {
			e.printStackTrace();
		}	
		return sd;
	}
	public ArrayList<Station> getArrivalById(String stationId, String routeId){
		Station st = null;
		ArrayList<Station> stList = new ArrayList<Station>();
		try {
			Connection conn = Jsoup.connect("http://openapi.gbis.go.kr/ws/rest/busarrivalservice/station?serviceKey=1234567890&stationId="+stationId);
			Document doc = conn.get();
			Elements eles = doc.select("busArrivalList");
			for (Element ele : eles) {
				if(ele.select("routeId").text().equals(routeId)) {
					st = new Station();
					st.setFlag(ele.select("flag").text());
					st.setLocationNo1(ele.select("locationNo1").text());
					st.setLocationNo2(ele.select("locationNo2").text());
					st.setLowPlate1(ele.select("lowPlate1").text());
					st.setLowPlate2(ele.select("lowPlate2").text());
					st.setPlateNo1(ele.select("plateNo1").text());
					st.setPlateNo2(ele.select("plateNo2").text());
					st.setPredictTime1(ele.select("predictTime1").text());
					st.setPredictTime2(ele.select("predictTime2").text());
					if(ele.select("remainSeatCnt1").text().equals("-1")) {
						st.setRemainSeatCnt1("정보없음");
					} else {
						st.setRemainSeatCnt1(ele.select("remainSeatCnt1").text());
					}
					if(ele.select("remainSeatCnt2").text().equals("-1")) {
						st.setRemainSeatCnt2("정보없음");
					} else {
						st.setRemainSeatCnt2(ele.select("remainSeatCnt2").text());
					}
					stList.add(st);
				}
			}

			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return stList;
	}


}
