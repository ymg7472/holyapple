package algorithm;
import java.util.*;
public class HashMapTest {
	public static void main(String args[]) {
		HashMap<Integer, String> s = new HashMap<Integer, String>();
		s.put(1, "È«±æµ¿");
		s.put(2, "±èÃ¶¼ö");
		s.put(3, "±è¿µÈñ");
		s.put(3, "sds");
		if(s.containsKey(3)){
			System.out.println(s.get(3));
		}
		int index=0;
		while(!s.isEmpty()){
			String value = s.remove(++index);
			System.out.println(value);
		}
	}	
}