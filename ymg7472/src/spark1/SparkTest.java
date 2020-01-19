package spark1;

import org.eclipse.jetty.websocket.common.frames.DataFrame;

public class SparkTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SparkConf sparkConf = new SparkConf();
		SparkContext sc = new SparkContext("local", "spark-mysql-test", sparkConf);
		SQLContext sqlContext = new SQLContext(sc);

		// here you can run sql query
		String sql = "(select * from table1 join table2 on table1.id=table2.table1_id) as test_table";
		// or use an existed table directly
		// String sql = "table1";
		DataFrame dataFrame = sqlContext
		    .read()
		    .format("jdbc")
		    .option("url", "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true")
		    .option("user", "root")
		    .option("password", "password")
		    .option("dbtable", sql)
		    .load();

		// continue your logical code
		......
	}

}
