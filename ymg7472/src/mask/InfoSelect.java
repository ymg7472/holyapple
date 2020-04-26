package mask;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.google.gson.GsonBuilder;

import mask.model.Stores;


public class InfoSelect {

	public static void main(String[] args) {
		DatabaseUtil db = new DatabaseUtil("jdbc:mysql://dev-swh.ga/minkyu", "root", "swhacademy!");
		db.getStore();
		for(Stores m : db.getStore()) {
			System.out.println(m);
		}
		db.close();
	}

}
