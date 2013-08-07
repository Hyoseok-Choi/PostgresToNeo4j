package hs.choi.convert.auto;

public class AutoMyTest {


	public static void main(String[] args) {


		// member
		String table1 = "member";
		AutoCreateNR test = new AutoCreateNR();
		test.clearDb();		// clear
		test.connectPostgres(table1);
		test.createDb();
		test.shutDown();
		
		// goods
		String table2 = "goods";
		AutoCreateNR test2 = new AutoCreateNR();
		test2.connectPostgres(table2);
		test2.createDb();
		test2.shutDown();
		
		// buy
		String table3 = "buy";
		AutoCreateNR test3 = new AutoCreateNR();
		test3.connectPostgres(table3);
		test3.createDb();
		test3.shutDown();
		


	}

}
