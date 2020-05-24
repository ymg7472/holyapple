package calender_crawl;

import static spark.Spark.get;
import static spark.Spark.modelAndView;
import static spark.Spark.post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;

import com.google.gson.Gson;

public class t1 {

	public static void main(String[] args) {
		IdSelect i = new IdSelect();
		DateSelect d = new DateSelect();
		Morpheme1 m = new Morpheme1();
		DatabaseUtil db = new DatabaseUtil("jdbc:mysql://dev-swh.ga/minkyu", "root", "swhacademy!");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
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
			C_news jnews = new Gson().fromJson(request.body(), C_news.class);
			try {
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				session.save(jnews);
				session.getTransaction().commit();
				session.close();
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			} 
			
			return "success " + jnews.toString();


		});

	}

}