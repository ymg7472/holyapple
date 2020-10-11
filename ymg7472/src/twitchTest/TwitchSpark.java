package twitchTest; 

import static spark.Spark.get;
import static spark.Spark.modelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import chartjs.FreeMarkerTemplateEngine;

/**
 * <pre>
 * twitchTest 
 * TwitchSpark.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2020. 8. 13.
 * @author : ymg74
 * @version : v1.0
 */
public class TwitchSpark {
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }
	    return true;
	}
	public static void main(String[] args) {
		TwitchGetUtil tw = new TwitchGetUtil();
		get("/streamer/:name", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			Streamer st = null;
			if(TwitchSpark.isInteger(request.params(":name"))) {
				st = tw.getStreamer(request.params(":name"), "id");
			}else {
				st = tw.getStreamer(request.params(":name"), "login");
			}
			ArrayList<String> asd = tw.getClips(st.getLogin());
			String views = String.valueOf(st.getView_count());
			attributes.put("id", st.getId());
			attributes.put("login", st.getLogin());
			attributes.put("display_name", st.getDisplay_name());
			attributes.put("description", st.getDescription());
			attributes.put("profileimg", st.getProfile_image_url());
			attributes.put("views", views);
			attributes.put("key", asd);
			return modelAndView(attributes, "streamer.ftl");
		}, new FreeMarkerTemplateEngine());
		
		get("/topStreams", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			ArrayList<Stream> asd = tw.getStreams();
			attributes.put("StreamList", asd);
			attributes.put("id", asd);
			return modelAndView(attributes, "Stream.ftl");
		}, new FreeMarkerTemplateEngine());
	}
}