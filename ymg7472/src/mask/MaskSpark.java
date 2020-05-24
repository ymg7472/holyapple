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
public class MaskSpark {

	public static void main(String[] args) {
		StoreSelect st = new StoreSelect();
		SalesSelect sa = new SalesSelect();
		InfoSelect is = new InfoSelect();
		get("/stores/json", (request, response) -> {
			response.type("application/json");
			String ki = new GsonBuilder().serializeNulls().create().toJson(st.getStore());
			
			
			return ki;
		});
		get("/sales/json", (request, response) -> {
			response.type("application/json");
			String ki = new GsonBuilder().serializeNulls().create().toJson(sa.getSales());
			
			
			return ki;
		});
		get("/sales/table", (request, response) -> {
			ArrayList<Sales> hihi = sa.getSales();
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("sales", hihi);
			return modelAndView(attributes, "masksales.ftl");
		}, new FreeMarkerTemplateEngine1());
		
		get("/stores/table", (request, response) -> {
			ArrayList<Stores> hihi = st.getStore();
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("stores", hihi);
			return modelAndView(attributes, "maskstores.ftl");
		}, new FreeMarkerTemplateEngine1());
		
		get("/maskinfo/table", (request, response) -> {
			ArrayList<MaskInfo> hihi = is.getInfo();
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("Info", hihi);
			return modelAndView(attributes, "maskinfo.ftl");
		}, new FreeMarkerTemplateEngine1());
		
		get("/test", (request, response) -> {
			ArrayList<MaskInfo> hihi = is.getInfo();
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("sss", hihi);
			return modelAndView(attributes, "maskmap.ftl");
		}, new FreeMarkerTemplateEngine1());
		
	}
}
