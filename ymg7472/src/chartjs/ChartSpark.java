package chartjs;

import static spark.Spark.get;
import static spark.Spark.modelAndView;
import static spark.Spark.post;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONArray;

import com.google.gson.Gson;

import naver_news_spark.DatabaseUtil;
import naver_news_spark.models.WordCloud;

public class ChartSpark {

	public static void main(String[] args) {
		Analyzer.resetUserDict();
		DatabaseUtil db = new DatabaseUtil("jdbc:mysql://dev-swh.ga/minkyu", "root", "swhacademy!");
		get("/pie/:subject/:date", (request, response) -> {
			ArrayList <WordCloud> wordList = db.baba(request.params(":subject"), request.params(":date"));
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("wordList", wordList);
			attributes.put("date", request.params(":date"));
			return modelAndView(attributes, "pie.ftl");
		}, new FreeMarkerTemplateEngine());
	

	}

}