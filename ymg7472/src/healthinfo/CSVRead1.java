package healthinfo; 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import mask.HibernateUtil7;

/**
 * <pre>
 * healthinfo 
 * asd.java
 *
 * 설명 :
 * </pre>
 * 
 * @since : 2020. 8. 30.
 * @author : ymg74
 * @version : v1.0
 */
public class CSVRead1 {



	public CSVRead1() {}

	public List<String[]> readCsv(String filename) throws CsvValidationException {

		List<String[]> data = new ArrayList<String[]>();
		CSVReader reader = null;
		try {
			// CSVReader reader = new CSVReader(new FileReader(filename), '\t');
			// UTF-8
			reader = new CSVReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
			String[] s;

			while ((s = reader.readNext()) != null) {
				data.add(s);	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return data;
	}
	public static void main(String[] args) {
		CSVRead cs = new CSVRead();
		SessionFactory sessionFactory = HibernateUtil8.getSessionFactory();
		ArrayList<String[]> data = new ArrayList<String[]>();
		ArrayList<String[]> data1 = new ArrayList<String[]>();
		ArrayList<String[]> data2 = new ArrayList<String[]>();
		ArrayList<String[]> data3 = new ArrayList<String[]>();
		ArrayList<FluStat> s = new ArrayList<FluStat>();
		ArrayList<BigCityCode> bc1 = new ArrayList<BigCityCode>();
		ArrayList<SmallCityCode> sc = new ArrayList<SmallCityCode>();

		try {
			data = (ArrayList<String[]>) cs.readCsv("C:\\Users\\ymg74\\Desktop\\건강\\실제진료정보_감기_시군구.csv");
			data1 = (ArrayList<String[]>) cs.readCsv("C:\\Users\\ymg74\\Desktop\\건강\\시군구 지역코드.csv");
			data2 = (ArrayList<String[]>) cs.readCsv("C:\\Users\\ymg74\\Desktop\\건강\\시도 지역코드.csv");
			data3 = (ArrayList<String[]>) cs.readCsv("C:\\Users\\ymg74\\Desktop\\건강\\실제진료정보_눈병_시군구.csv");
			
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FluStat sm = null;
		SmallCityCode sc1 = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		BigCityCode bc = null;
		for(int i = 1; i<data2.size(); i++) {
			bc = new BigCityCode();
			String[] tem = data2.get(i);
			bc.setCode(tem[0]);
			bc.setLocation(tem[1]);
			bc1.add(bc);
		}
		SmallCityCode sc2 = null;
		for(int i = 1; i<data1.size(); i++) {
			sc2 = new SmallCityCode();
			String[] tem = data1.get(i);
			for(int j = 0; j < bc1.size(); j++) {
				if(bc1.get(j).getCode().equals(tem[0])){
					sc2.setBcc(bc1.get(j));
				}
			}
			sc2.setSmallCityCode(tem[1]);
			sc2.setLocation(tem[2]);
			sc.add(sc2);
		}
		int id = 1;
		for(int i = 1; i<data.size(); i++) {
			sm = new FluStat();
			String[] tem = data.get(i);
			for(int j = 0; j < sc.size(); j++) {
				if(sc.get(j).getSmallCityCode().equals(tem[1])){
					sm.setScc(sc.get(j));
				}
			}
			sm.setDate(tem[0]);
			sm.setCase1(Integer.valueOf(tem[2]));
			for(int j = 0; j < sc.size(); j++) {
				if(sc.get(j).getSmallCityCode().equals(tem[1])){
					sm.setScc(sc.get(j));
				}
			}
			Disease dis = null;
			sm.setDis(dis);
			s.add(sm);
			id++;
		}
		for(int i = 1; i<data3.size(); i++) {
			sm = new FluStat();
			String[] tem = data3.get(i);
			for(int j = 0; j < sc.size(); j++) {
				if(sc.get(j).getSmallCityCode().equals(tem[1])){
					sm.setScc(sc.get(j));
				}
			}
			sm.setDate(tem[0]);
			sm.setCase1(Integer.valueOf(tem[2]));
			for(int j = 0; j < sc.size(); j++) {
				if(sc.get(j).getSmallCityCode().equals(tem[1])){
					sm.setScc(sc.get(j));
				}
			}
			Disease dis = null;
			sm.setDis(dis);
			s.add(sm);
			id++;
		}
//		System.out.println(s.get(0));
		for(int i = 0; i<s.size(); i++) {
			session.saveOrUpdate(s.get(i));
			if(i != 0 && i%10000 == 0) {
				session.getTransaction().commit();
				session.beginTransaction();
			}
		}
		session.getTransaction().commit();	
		session.close();
		sessionFactory.close();
	}
}
