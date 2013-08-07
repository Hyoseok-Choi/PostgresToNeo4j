package hs.choi.convert.auto;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSetting;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.kernel.impl.util.FileUtils;

public class AutoCreateNR {
	private static final String DB_PATH = "C:/dev_convert/neo4j-community-2.0.0-M02/data/graph.db";

	// postgres
	private static Connection conn;
	
	ArrayList<String> array_column_name;
	ArrayList<ArrayList> all_column_arrary = new ArrayList<ArrayList>();
	
		
	// START SNIPPET: vars
	GraphDatabaseService graphDb;
	


	// END SNIPPET: vars
	
	
	// 메인!!
//	public static void main(final String[] args) {
//		CreateNR test = new CreateNR();
////		test.clearDb();
//		test.connectPostgres();
//		test.createDb();
//		test.shutDown();
//	}
	
	

	// START SNIPPET: createReltype
	private static enum RelTypes implements RelationshipType {
		SEX, HAS_A, BORROW, IS_LOCATED_IN
	}

	// END SNIPPET: createReltype

	

	
	
	// DB생성,	Transaction 있는부분
	void createDb() {
		
		// START SNIPPET: startDb
//		graphDb = new EmbeddedGraphDatabase(DB_PATH);
//		registerShutdownHook(graphDb);			// shutdown hook
		// END SNIPPET: startDb
		
		
		// auto indexing
		graphDb = new GraphDatabaseFactory().
	    newEmbeddedDatabaseBuilder( DB_PATH ).
	    setConfig( GraphDatabaseSettings.node_keys_indexable, "name" ).
	    setConfig( GraphDatabaseSettings.relationship_keys_indexable, "name" ).
	    setConfig( GraphDatabaseSettings.node_auto_indexing, GraphDatabaseSetting.TRUE ).
	    setConfig( GraphDatabaseSettings.relationship_auto_indexing, GraphDatabaseSetting.TRUE ).
	    newGraphDatabase();
		
				
		registerShutdownHook(graphDb);			// shutdown hook
		
		
		
//		IndexManager index = graphDb.index();		// indexing 1
//		Index<Node> person = index.forNodes( "person" );
//		Index<Node> book = index.forNodes( "book" );
//		RelationshipIndex bor = index.forRelationships( "bor" );
		
//		for ( int i = 0; i < array_name.size(); i++){
//			System.out.println(array_name.get(i));
//			
//			// 먼저 기존에 존재하는 노드인지 확인
//			String check_name = array_name.get(i);
//			
//			if (ti.hasNext() == false){
//				Node node = graphDb.createNode();
//				node.setProperty("name", array_name.get(i));
//				
//				
//			}
//		}
		
		// not using Cyher
//		Iterator<Node> allNode = graphDb.getAllNodes().iterator();
//		
//		while ( allNode.hasNext() )
//		{
//		    Node node = allNode.next();
//		    System.out.println(node.getProperty( "name" ) );
//		    System.out.println(node.getId() );
//		}
//		
		
				
		
		
		
		
		
		// START SNIPPET: transaction
		
				   
		   
		Transaction tx = graphDb.beginTx();
		try {
			
		
			
/////////////// create node - start
			
			ExecutionEngine engine = new ExecutionEngine(graphDb);

			for (int ai = 0; ai < all_column_arrary.size(); ai++){
				
				for (int i = 0; i < all_column_arrary.get(ai).size(); i++) {
					System.out.println(all_column_arrary.get(ai).get(i));

					// 먼저 기존에 존재하는 노드인지 확인
					String check_name = (String) all_column_arrary.get(ai).get(i);

					ExecutionResult result1 = engine
							.execute("start n=node(*) where n.name! = '"
									+ check_name + "' return n");

					Iterator<Node> ti = result1.columnAs("n");
					// System.out.println(ti.hasNext());

					// 중복된것 없으면 노드 생성
					if (ti.hasNext() == false) {

						Node node = graphDb.createNode();
						node.setProperty("name", check_name);
						
						System.out.println(check_name);
					}
				}
			}
			System.out.println("create node success");

/////////////// create node - end

	
/////////////////// create relationship
/////////////////// convert_start ----- 1
////	
////			
////			for (int i = 0; i < array_name.size(); i++) {
////
////				// start node
////				String start_node = array_name.get(i);
////
////				// end node
////				String end_node = array_sex.get(i);
////
////				
////
////				
////			}
////
/////////////////// convert_end ----- 1

			
			
///////////////// create relationship - start
// [0] --> [1]
// [0] --> [2]
// [0] --> [3]
			
			for (int ai = 1; ai < all_column_arrary.size(); ai++) {

				for (int i = 0; i < all_column_arrary.get(ai).size(); i++) {

					// start node [0]
					String start_node = (String) all_column_arrary.get(0)
							.get(i);

					// end node [1],[2],[3]
					String end_node = (String) all_column_arrary.get(ai).get(i);

					// rel type
					String rel_type = array_column_name.get(ai);

					ExecutionResult result3 = engine
						.execute("START a=node(*), b=node(*)  where a.name! = '"
								+ start_node
								+ "' and b.name! = '"
								+ end_node
								+ "'CREATE a-[r:" + rel_type + "]->b RETURN r");
				}

			}
			System.out.println("create relationship success");
///////////////// create relationship - end

			


			tx.success();
			System.out.println("success");
			
		} catch (Exception e){
			tx.failure();
			System.out.println("failure");
			
		} finally {
			tx.finish();
			System.out.println("finish");
		}
		// END SNIPPET: transaction
	}

	public void clearDb() {
		try {
			FileUtils.deleteRecursively(new File(DB_PATH));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	void removeData() {
		Transaction tx = graphDb.beginTx();
		try {
			// START SNIPPET: removingData
			// let's remove the data
//			person7.getSingleRelationship(RelTypes.BORROW, Direction.OUTGOING)
//					.delete();		// rel 지우고
//			person7.delete();		// 노드지우고
//			book142.delete();		// 노드지우고
			// END SNIPPET: removingData

			tx.success();
		} finally {
			tx.finish();
		}
	}

	void shutDown() {
		System.out.println();
		System.out.println("Shutting down database ...");
		// START SNIPPET: shutdownServer
		graphDb.shutdown();
		// END SNIPPET: shutdownServer
	}

	// START SNIPPET: shutdownHook
	private static void registerShutdownHook(final GraphDatabaseService graphDb) {
		// Registers a shutdown hook for the Neo4j instance so that it
		// shuts down nicely when the VM exits (even if you "Ctrl-C" the
		// running example before it's completed)
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				graphDb.shutdown();
			}
		});
	}
	// END SNIPPET: shutdownHook
	
	
	public void connectPostgres(String table) {
		
		array_column_name = new ArrayList<String>();
		
/////////////// get_column_name - start
		
		String url = "jdbc:postgresql://210.107.221.101/PostgresToNeo4j"; // jdbc:postgresql//[host]:[port]/[db_name]
		String user = "postgres";
		String pwd = "postgres";
		String sql = "SELECT column_name FROM information_schema.columns WHERE table_name ='"+ table +"';";

		String driver = "org.postgresql.Driver";
	
		try {

			Class.forName(driver);
			System.out.println("DB");

			conn = DriverManager.getConnection(url, user, pwd);
			Statement stmt = conn.createStatement();
	
			ResultSet rs = stmt.executeQuery(sql);
	
			while (rs.next()) {
	
				array_column_name.add(rs.getString("column_name"));
		
				System.out.println(rs.getString("column_name"));
			}
	
	//		rs.close();
			stmt.close();
			conn.close();
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB");
		}
		System.out.println("array_column_name size : " + array_column_name.size());
	
		
/////////////// get_column_name - end
		
		
		
		all_column_arrary = new ArrayList<ArrayList>();
		
/////////////// get_data - start
		
		for (int i = 0; i < array_column_name.size(); i++){
			
			ArrayList<String> column_arrary = new ArrayList<String>();
			
			sql = "select " + array_column_name.get(i) + " from " + table + ";";
						
			try {

				Class.forName(driver);
				System.out.println("DB");

				conn = DriverManager.getConnection(url, user, pwd);
				Statement stmt = conn.createStatement();

				ResultSet rs = stmt.executeQuery(sql);

				while (rs.next()) {

					column_arrary.add(rs.getString(array_column_name.get(i)));

					// System.out.println(id + " / " + name + " / " + sex +
					// " / " + job);
				}

				// rs.close();
				stmt.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DB");
			}
			System.out.println(column_arrary.size());
			System.out.println("add : ");
			
			all_column_arrary.add(column_arrary);

		}
		
/////////////// get_data - end
		
		// print test
		
//		for (int i = 0; i < 4; i++){
//			for (int j = 0; j < 6; j++){
//				System.out.println(all_column_arrary.get(i).get(j));
//			}
//		}
		
	}
	
	

	
}
