package naver_news_spark.models; 

import org.apache.log4j.Logger;

import lombok.Data;

/**
 * <pre>
 * crawling.model 
 * Weather.java
 *
 * Ό³Έν :
 * </pre>
 * 
 * @since : 2021. 1. 3.
 * @author : ymg74
 * @version : v1.0
 */
@Data
public class Weather {
	private String city;
	private String tmef;
	private String wf;
	private String tmn;
	private String tmx;

}
