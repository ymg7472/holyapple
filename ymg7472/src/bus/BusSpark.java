package bus; 

import static spark.Spark.get;
import static spark.Spark.modelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import chartjs.FreeMarkerTemplateEngine;
import twitchTest.Stream;
import twitchTest.Streamer;
import twitchTest.TwitchSpark;

/**
 * <pre>
 * healthinfo 
 * HealthSpark.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 8. 30.
 * @author : ymg74
 * @version : v1.0
 */
public class BusSpark {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetBus gb = new GetBus();
		get("/bus/:num", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			ArrayList<Bus> busInfo = gb.getBusByNum(request.params(":num")) ;
			attributes.put("busInfo", busInfo);
			attributes.put("num", request.params(":num"));
			return modelAndView(attributes, "bus.ftl");
		}, new FreeMarkerTemplateEngine());
		get("/route/:num", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			ArrayList<BusRoute> busInfo = gb.getRouteByBus(request.params(":num"));
			ArrayList<Object> busLocInfo = gb.getBusLoc(request.params(":num"));
			attributes.put("busRouteInfo", busInfo);
			attributes.put("num", request.params(":num"));
			attributes.put("where", busLocInfo.get(0));
			attributes.put("name", busLocInfo.get(1));
			return modelAndView(attributes, "busRoute.ftl");
		}, new FreeMarkerTemplateEngine());
		
		get("/station/:routeId/:stationId", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			ArrayList<Station> busInfo = gb.getArrivalById(request.params(":stationId"), request.params(":routeId"));
			attributes.put("stationArrivalInfo", busInfo);
			return modelAndView(attributes, "station.ftl");
		}, new FreeMarkerTemplateEngine());

	}

}
