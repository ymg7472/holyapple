package healthinfo;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.ibm.icu.util.CodePointTrie.Small;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import mask.HibernateUtil7;
import mask.model.Stores;

public class CSVRead {



	public CSVRead() {}

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
		SessionFactory sessionFactory = HibernateUtil7.getSessionFactory();
		ArrayList<String[]> data = new ArrayList<String[]>();
		ArrayList<String[]> data1 = new ArrayList<String[]>();
//		ArrayList<FluStat> s = new ArrayList<FluStat>();
		ArrayList<BigCityCode> bc1 = new ArrayList<BigCityCode>();
		ArrayList<SmallCityCode> sc1 = new ArrayList<SmallCityCode>();
		try {
			data = (ArrayList<String[]>) cs.readCsv("C:\\Users\\ymg74\\Desktop\\건강\\시도 지역코드.csv");
			data1 = (ArrayList<String[]>) cs.readCsv("C:\\Users\\ymg74\\Desktop\\건강\\시군구 지역코드.csv");
		} catch (CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigCityCode bc = null;
		SmallCityCode sc = null;
//		FluStat sm = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		for(int i = 1; i<data.size(); i++) {
			bc = new BigCityCode();
			String[] tem = data.get(i);
			bc.setCode(tem[0]);
			bc.setLocation(tem[1]);
			bc1.add(bc);
		}
		for(int i = 1; i<data1.size(); i++) {
			sc = new SmallCityCode();
			String[] tem = data1.get(i);
			for(int j = 0; j < bc1.size(); j++) {
				if(bc1.get(j).getCode().equals(tem[0])){
					sc.setBcc(bc1.get(j));
				}
			}
			sc.setSmallCityCode(tem[1]);
			sc.setLocation(tem[2]);
			sc1.add(sc);
		}
		for(BigCityCode c:bc1) {
			session.saveOrUpdate(c);
		}
		session.getTransaction().commit();
		session.beginTransaction();
		for(SmallCityCode c:sc1) {
			session.saveOrUpdate(c);
		}
		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}