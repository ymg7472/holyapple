package log;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * <pre>
 * kr.co.swh.lecture.opensource.log4j
 * LoggingService.java
 *
 * 설명 :Log4j2 예제
 * </pre>
 * 
 * @since : 2018. 1. 31.
 * @author : tobby48
 * @version : v1.0
 */
public class LoggingService {
	static final Logger logger = LogManager.getLogger(LoggingService.class.getName());

	public boolean check() {
		
		if(logger.isErrorEnabled()){
			logger.error("에러가 발생했어요.");
		}
		if(logger.isDebugEnabled()){
			logger.debug("버그가 발생했어요.");
		}
		return false;
	}
}