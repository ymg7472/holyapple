package naver_news_spark;

import static spark.Spark.get;
import static spark.Spark.modelAndView;
import static spark.Spark.post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.json.JSONArray;

import com.google.gson.Gson;

import naver_news_spark.models.C_news;
import naver_news_spark.models.Weather;
import naver_news_spark.models.WordCloud;

public class t1 {
	static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	static GetFromDb db = new GetFromDb("jdbc:mysql://dev-swh.gq/minkyu", "root", "swhacademy!");
	public static void main(String[] args) {
		
		get("/", (request, response) -> {
			return modelAndView(null, "test.ftl");
		}, new FreeMarkerTemplateEngine());
		
		get("/news/:subject/:date", (request, response) -> {
			response.type("application/json");
			String ji = db.byDate(request.params(":subject"), request.params(":date"));
			if(ji != null) {
				return ji;
			}else {
				response.status(404);
				return "news not found";
			}
		});
		get("/findnews/:id", (request, response) -> {
			response.type("application/json");
			String ki = db.byId(request.params(":id"));
			if(ki != null) {
				return ki;
			}else {
				response.status(404);
				return "news not found";
			}
		});
		get("/table/:subject/:date", (request, response) -> {
			JSONArray j = new JSONArray(db.byDate(request.params(":subject"), request.params(":date")));
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("news", j);
			// The hello.ftl file is located in directory:
			// src/test/resources/spark/examples/templateview/freemarker
			return modelAndView(attributes, "hello.ftl");
		}, new FreeMarkerTemplateEngine());

		get("/wordcloud/:subject/:date", (request, response) -> {
			ArrayList <WordCloud> wordList = db.baba(request.params(":subject"), request.params(":date"));
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("wordList", wordList);
			return modelAndView(attributes, "test.ftl");
		}, new FreeMarkerTemplateEngine());
	
		post("/users", (request, response) -> {
			response.type("application/json");
			Session session = sessionFactory.openSession();
			C_news jnews = new Gson().fromJson(request.body(), C_news.class);
//			List dupList = session.createCriteria(C_news.class).add(Restrictions.eq("id", jnews.getId())).list();
					try {
						session.beginTransaction();
						session.saveOrUpdate(jnews);						
						session.getTransaction().commit();
						session.close();
					} catch (Exception e1) {
						System.out.println(e1.getMessage());
					} 
			
			return "";


		});
//		post("/weather", (request, response) -> {
//			response.type("application/json");
//			Weather weather = new Gson().fromJson(request.body(), Weather.class);
//			try {
//				session.beginTransaction();
//				session.saveOrUpdate(weather);
//				session.getTransaction().commit();
////				session.close();
//			} catch (Exception e1) {
//				System.out.println(e1.getMessage());
//			} 
//			
//			return "success ";
//
//
//		});
//		

	}

}