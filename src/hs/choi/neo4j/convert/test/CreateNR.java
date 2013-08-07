package hs.choi.neo4j.convert.test;

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

public class CreateNR {
	private static final String DB_PATH = "C:/dev_convert/neo4j-community-2.0.0-M02/data/graph.db";

	// postgres
	private static Connection conn;
	
	ArrayList<String> array_id = new ArrayList<String>();
	ArrayList<String> array_name = new ArrayList<String>();
	ArrayList<String> array_sex = new ArrayList<String>();
	ArrayList<String> array_job = new ArrayList<String>();
	
		
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
			
		
			
// ///////////// create node
// ///////////// convert_start - name ----- 1

			ExecutionEngine engine = new ExecutionEngine(graphDb);

			for (int i = 0; i < array_name.size(); i++) {
				System.out.println(array_name.get(i));

				// 먼저 기존에 존재하는 노드인지 확인
				String check_name = array_name.get(i);

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
	
/////////////// convert_end - name ----- 1
			
/////////////// convert_start - sex ----- 2

			
			for (int i = 0; i < array_sex.size(); i++) {
				System.out.println(array_sex.get(i));

				// 먼저 기존에 존재하는 노드인지 확인
				String check_name = array_sex.get(i);

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
				
/////////////// convert_end - sex ----- 2
			
			
/////////////// convert_start - job ----- 3

			
			for (int i = 0; i < array_job.size(); i++) {
				System.out.println(array_job.get(i));

				// 먼저 기존에 존재하는 노드인지 확인
				String check_name = array_job.get(i);

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
				
/////////////// convert_end - job ----- 3
			
					
//			
///////////////// create relationship
///////////////// convert_start ----- 1
//	
//			
//			for (int i = 0; i < array_name.size(); i++) {
//
//				// start node
//				String start_node = array_name.get(i);
//
//				// end node
//				String end_node = array_sex.get(i);
//
//				
//
//				
//			}
//
///////////////// convert_end ----- 1
			
			
		
			
/////////////// create relationship
/////////////// convert_start
	
			
			for (int i = 0; i < array_name.size(); i++) {

				// start node
				String start_node = array_name.get(i);

				// end node
				String end_node = array_sex.get(i);

				// rel type
				String rel_type = "sex";

				ExecutionResult result3 = engine
						.execute("START a=node(*), b=node(*)  where a.name! = '"
								+ start_node
								+ "' and b.name! = '"
								+ end_node
								+ "'CREATE a-[r:" + rel_type + "]->b RETURN r");
			}

/////////////// convert_end
			
/////////////// convert_start
			
			
			for (int i = 0; i < array_name.size(); i++) {

				// start node
				String start_node = array_name.get(i);

				// end node
				String end_node = array_job.get(i);

				// rel type
				String rel_type = "job";

				ExecutionResult result3 = engine
						.execute("START a=node(*), b=node(*)  where a.name! = '"
								+ start_node
								+ "' and b.name! = '"
								+ end_node
								+ "'CREATE a-[r:" + rel_type + "]->b RETURN r");
			}

/////////////// convert_end
			
			

			
//			
			
			
			
			
			
			// Mutating operations go here
			// END SNIPPET: transaction
			// START SNIPPET: addData
			
		
		
			
			
			// END SNIPPET: addData

			
			
			
			
			// START SNIPPET: readData



			// END SNIPPET: readData


			
           
            
			// START SNIPPET: transaction
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

	private void clearDb() {
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
	
	
	public void connectPostgres() {
		
/////////////// postgres_start
		
	String url = "jdbc:postgresql://210.107.221.101/PostgresToNeo4j"; // jdbc:postgresql//[host]:[port]/[db_name]
	String user = "postgres";
	String pwd = "postgres";
	String sql = "select * from member;";
//	String sql = "create table my_test3 as select * from seoul limit;";

	String driver = "org.postgresql.Driver";
	
	try {

		Class.forName(driver);
		System.out.println("DB");

		conn = DriverManager.getConnection(url, user, pwd);
		

		Statement stmt = conn.createStatement();
		

//		stmt.executeUpdate(sql);
//		System.out.println("");
		

		ResultSet rs = stmt.executeQuery(sql);
		System.out.println("");
		


		while (rs.next()) {

			array_id.add(rs.getString("mem_id"));
			array_name.add(rs.getString("mem_name"));
			array_sex.add(rs.getString("mem_sex"));
			array_job.add(rs.getString("mem_job"));
	
			
//			System.out.println(id + " / " + name + " / " + sex + " / " + job);
		}


//		rs.close();
		stmt.close();
		conn.close();

	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("DB");
	}
	System.out.println(array_name.size());

	
/////////////// postgres_end
		
		
	}
	
}
