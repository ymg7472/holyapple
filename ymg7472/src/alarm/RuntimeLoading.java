package alarm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import junit.framework.Test;

//interface PrintInterface {
//    public void print();
//}
//
//class ClassLoadingSample1 implements PrintInterface {
//    @Override
//    public void print() {
//        System.out.println("Sample1");
//    }
//}
//
//class ClassLoadingSample2 implements PrintInterface {
//
//    @Override
//    public void print() {
//        System.out.println("Sample2");
//    }
//}


public class RuntimeLoading {
	
	public Properties loadProp(String path) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		properties.load(inputStream);
		inputStream.close();
		return properties;
	}
    public static void main(String[] args) throws IOException {
        RuntimeLoading runtimeLoading = new RuntimeLoading();
        Properties properties = runtimeLoading.loadProp("test123.properties");
        try {
            Class<?> cls = Class.forName(properties.getProperty("test.class"));
            Object obj = cls.newInstance();
//            PrintInterface print = (PrintInterface)obj;
            System.out.println(obj);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}