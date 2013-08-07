package hs.choi.neo4j.convert.test;

public class myTest {


	public static void main(String[] args) {

		// member
		
		CreateNR test = new CreateNR();
//		test.clearDb();
		test.connectPostgres();
		test.createDb();
		test.shutDown();
		
		CreateNR2 test2 = new CreateNR2();
//		test.clearDb();
		test2.connectPostgres();
		test2.createDb();
		test2.shutDown();
		
		CreateNR3 test3 = new CreateNR3();
//		test.clearDb();
		test3.connectPostgres();
		test3.createDb();
		test3.shutDown();

	}

}
