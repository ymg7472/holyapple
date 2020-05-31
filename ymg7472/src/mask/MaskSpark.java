package mask;
import static spark.Spark.get;
import static spark.Spark.modelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.GsonBuilder;

import mask.model.MaskInfo;
import mask.model.Sales;
import mask.model.Stores;
/**
 * <pre>
 * mask 
 * MaskSpark.java
 *
 * 설명 : 마스크에 관한 정보 spark
 * </pre>
 * 
 * @since : 2020. 5. 31.
 * @author : ymg74
 * @version : v1.0
 */
public class MaskSpark {

	public static void main(String[] args) {
		DatabaseUtil db = new DatabaseUtil("jdbc:mysql://dev-swh.ga/minkyu", "root", "swhacademy!");
		get("/stores/json", (request, response) -> {
			response.type("application/json");
			String ki = new GsonBuilder().serializeNulls().create().toJson(db.getStore());
			
			
			return ki;
		});
		get("/sales/json", (request, response) -> {
			response.type("application/json");
			String ki = new GsonBuilder().serializeNulls().create().toJson(db.getSales());
			
			
			return ki;
		});
		get("/sales/table", (request, response) -> {
			ArrayList<Sales> hihi = db.getSales();
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("sales", hihi);
			return modelAndView(attributes, "masksales.ftl");
		}, new FreeMarkerTemplateEngine1());
		
		get("/stores/table", (request, response) -> {
			ArrayList<Stores> hihi = db.getStore();
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("stores", hihi);
			return modelAndView(attributes, "maskstores.ftl");
		}, new FreeMarkerTemplateEngine1());
		
		get("/maskinfo/table", (request, response) -> {
			ArrayList<MaskInfo> hihi = db.getMaskInfo();
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("Info", hihi);
			return modelAndView(attributes, "maskinfo.ftl");
		}, new FreeMarkerTemplateEngine1());
		
		get("/map", (request, response) -> {
			ArrayList<MaskInfo> hihi = db.getMaskInfo();
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("sss", hihi);
			return modelAndView(attributes, "maskmap.ftl");
		}, new FreeMarkerTemplateEngine1());
		
		get("/mapByName/:name", (request, response) -> {
			ArrayList<MaskInfo> hihi = db.getMaskInfoByName(request.params(":name"));
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("sss", hihi);
			return modelAndView(attributes, "maskmap.ftl");
		}, new FreeMarkerTemplateEngine1());
		
		get("/", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("sss", "sd");
			return modelAndView(attributes, "redirect.ftl");
		}, new FreeMarkerTemplateEngine1());
		
	}
}
