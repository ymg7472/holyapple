package healthinfo; 

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
public class HealthSpark {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sessionFactory = HibernateUtil8.getSessionFactory();
		Session session = sessionFactory.openSession();
		FluDbutil fd = new FluDbutil(sessionFactory, session);
		get("/:date/:location/:disease", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			ArrayList<FluStat> fluStat = fd.getFluStatByDate(request.params(":date"), request.params(":location"), request.params(":disease")) ;
			attributes.put("fluStat", fluStat);
			attributes.put("diss", request.params(":disease"));
			return modelAndView(attributes, "fluStat.ftl");
		}, new FreeMarkerTemplateEngine());
;
	}

}
