package properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesDemo {
	/**
	 * 프로퍼티 파일을 스트림으로 읽음
	 *
	 * @param path
	 * @throws IOException
	 */
	public Properties loadProp(String path) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		properties.load(inputStream);
		inputStream.close();
		return properties;
	}

	public static void main(String[] args) throws IOException {
		PropertiesDemo propertiesDemo = new PropertiesDemo();

		// 프로퍼티 파일을 읽어들이고 해당 값을 출력
		Properties properties = propertiesDemo.loadProp("application.properties");
		properties.list(System.out);

		// 같은 키를 준 경우 오버라이드 하는 것을 확인
		Properties staticProp = propertiesDemo.loadProp("application-prod.properties");
		properties.putAll(staticProp);
		properties.list(System.out);

		// 프로퍼티간의 결합
		Properties dummy = new Properties();
		dummy.put("demo.type", "dummy");
		dummy.put("demo.temp", "temp"); 	// 새로운 키를 추가
		properties.putAll(dummy); 			// 기존 프로퍼티에 더미 프로퍼티를 결합
		properties.list(System.out);

		// 키를 주어 값을 리턴
		Object type = properties.get("demo.type");
		System.out.println(type);

		// 프로퍼티 키들을 순회
		// stringPropertyNames() 대신 다른 메소드 사용 가능
		for (String key : properties.stringPropertyNames()) {
			Object value = properties.getProperty(key);
			System.out.println(value);
		}

		// 해당 키가 있는지 여부를 판별합니다.
		System.out.println(properties.containsKey("demo.type"));

		// 해당 값이 있는지 여부를 판별합니다.
		System.out.println(properties.containsValue("dummy"));
	}
}
