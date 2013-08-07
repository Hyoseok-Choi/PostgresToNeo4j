// using Cypher
package hs.choi.neo4j.convert.test;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import org.neo4j.kernel.impl.util.FileUtils;




public class ConvertTest {

//	private static final String DB_PATH = "C:/dev_convert/neo4j-community-1.8.2/data/graph.db";
//	private static final String DB_PATH = "C:/dev_convert/neo4j-community-1.9.RC2/data/graph.db";
	private static final String DB_PATH = "C:/dev_convert/neo4j-community-2.0.0-M02/data/graph.db";
	
	

    static GraphDatabaseService graphDb = new EmbeddedGraphDatabase( DB_PATH );
    
    private static Connection conn;
    
    public static void main( String[] args )
    {
        go_cypher(graphDb);
    }
    
    private static void go_cypher(GraphDatabaseService graphDb) {
		
	       

		
		// node, property 생성
//		CREATE n = {name : 'Andres', title : 'Developer'}
		
		// rel 생성
//		start a=node(6), b=node(9) create a-[r:REL { name : a.name + ' <->' + b.name }]->b return r
		
		// index를 이용한
//		ExecutionResult result = engine.execute("start n=node:node_auto_index(name='Hyoseok Choi') return n");
//		System.out.println(result);

//		
///////////////// postgres_start
//		
//		String url = "jdbc:postgresql://210.107.221.101/toys"; // jdbc:postgresql//[host]:[port]/[db_name]
//		String user = "postgres";
//		String pwd = "postgres";
//		String sql = "select * from member;";
////		String sql = "create table my_test3 as select * from seoul limit;";
//
//		String driver = "org.postgresql.Driver";
//		
//		ArrayList<String> array_id = new ArrayList<String>();
//		ArrayList<String> array_name = new ArrayList<String>();
//		ArrayList<String> array_sex = new ArrayList<String>();
//		ArrayList<String> array_job = new ArrayList<String>();
//
//		try {
//
//			Class.forName(driver);
//			System.out.println("DB");
//
//			conn = DriverManager.getConnection(url, user, pwd);
//			
//
//			Statement stmt = conn.createStatement();
//			
//
////			stmt.executeUpdate(sql);
////			System.out.println("");
//			
//
//			ResultSet rs = stmt.executeQuery(sql);
//			System.out.println("");
//			
//
//
//			while (rs.next()) {
//
//				array_id.add(rs.getString("mem_id"));
//				array_name.add(rs.getString("mem_name"));
//				array_sex.add(rs.getString("mem_sex"));
//				array_job.add(rs.getString("mem_job"));
//		
//				
////				System.out.println(id + " / " + name + " / " + sex + " / " + job);
//			}
//
//
////			rs.close();
//			stmt.close();
//			conn.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("DB");
//		}
//		System.out.println(array_name.size());
//
//		
///////////////// postgres_end
//    	
    	
    	
    	
    	
    	
//		ExecutionEngine engine = new ExecutionEngine(graphDb);
		
		// create node
//		ExecutionResult result = engine.execute("create n = { name : 'seok2', phone : '010 3048 3850'}");
//		System.out.println(result);
		
		// create node
//		ExecutionResult result = engine.execute("create n = { name : '"+ array_name.get(1) +"', phone : '010 3048 3850'}");
//		System.out.println(result);
		

		
		
		
		
//		
//		
///////////////// create node
///////////////// convert_start - name
//		
//		ExecutionEngine engine = new ExecutionEngine(graphDb);
//		
//		for ( int i = 0; i < array_name.size(); i++){
//			System.out.println(array_name.get(i));
//			
//			// 먼저 기존에 존재하는 노드인지 확인
//			String check_name = array_name.get(i);
//			
//			ExecutionResult result1 = engine.execute("start n=node(*) where n.name! = '" + check_name + "' return n");
//			
//			Iterator<Node> ti = result1.columnAs( "n" );
////			System.out.println(ti.hasNext());
//			
//			// 중복된것 없으면 노드 생성
//			if (ti.hasNext() == false){
////				ExecutionResult result2 = engine.execute("create n = { name : '"+ array_name.get(i) +"' }");
//				ExecutionResult result2 = engine.execute("create (n { name : '"+ array_name.get(i) +"' })");
//				System.out.print("in if, result2 : ");
//				System.out.println(result2);
//			}
//		}
//		
//		
//
///////////////// convert_end
//		
///////////////// convert_start - sex
//		
//		ExecutionEngine engine2 = new ExecutionEngine(graphDb);
//		
//	for ( int i = 0; i < array_sex.size(); i++){
//		System.out.println(array_sex.get(i));
//		
//		// 먼저 기존에 존재하는 노드인지 확인
//		String check_name = array_sex.get(i);
//		
//		ExecutionResult result3 = engine2.execute("start n=node(*) where n.name! = '" + check_name + "' return n");
//		
//		Iterator<Node> ti = result3.columnAs( "n" );
//				
//		// 중복된것 없으면 노드 생성
//		if (ti.hasNext() == false){
////			ExecutionResult result4 = engine.execute("create n = { name : '"+ array_sex.get(i) +"' }");
//			ExecutionResult result4 = engine2.execute("create (n { name : '"+ array_sex.get(i) +"' })");
//			System.out.print("in if, result4 : ");
//			System.out.println(result4);
//		}
//	}
//
///////////////// convert_end
//	
///////////////// convert_start - job
//	
//	ExecutionEngine engine3 = new ExecutionEngine(graphDb);
//	
//		for (int i = 0; i < array_job.size(); i++) {
//			System.out.println(array_job.get(i));
//
//			// 먼저 기존에 존재하는 노드인지 확인
//			String check_name = array_job.get(i);
//
//			ExecutionResult result5 = engine3
//					.execute("start n=node(*) where n.name! = '" + check_name
//							+ "' return n");
//
//			Iterator<Node> ti = result5.columnAs("n");
//			
//			// 중복된것 없으면 노드 생성
//			if (ti.hasNext() == false) {
////				ExecutionResult result6 = engine.execute("create n = { name : '" + array_job.get(i)	+ "' }");
//				ExecutionResult result6 = engine3.execute("create (n { name : '" + array_job.get(i)	+ "' })");
//				System.out.print("in if, result2 : ");
//				System.out.println(result6);
//			}
//		}
//
///////////////// convert_end
//			
//		
//		
//		
//		
///////////////// create relationship
///////////////// convert_start
//		
//		ExecutionEngine engine4 = new ExecutionEngine(graphDb);
//		
//	for ( int i = 0; i < array_name.size(); i++){
//		
//		// start node
//		System.out.println(array_name.get(i));
//		String start_node = array_name.get(i);
//		
//		// end node
//		System.out.println(array_sex.get(i));
//		String end_node = array_sex.get(i);
//		
//		// rel type
//		String rel_type = "sex";
//				
////		ExecutionResult result7 = engine.execute("START a=node(*), b=node(*) CREATE a-[r:" + rel_type + "]->b" +
////					" where a.name! = '" + start_node + "' and b.name! = '" + end_node + "'  RETURN r");
//		ExecutionResult result7 = engine4.execute("START a=node(*), b=node(*)  where a.name! = '" + start_node + "' and b.name! = '" + end_node +
//				"'CREATE a-[r:" + rel_type + "]->b RETURN r");
//		
//		System.out.println(result7.dumpToString());
//	}
//
///////////////// convert_end
//	
///////////////// convert_start
//	
//	ExecutionEngine engine5 = new ExecutionEngine(graphDb);
//	
//		for (int i = 0; i < array_name.size(); i++) {
//
//			// start node
//			System.out.println(array_name.get(i));
//			String start_node = array_name.get(i);
//
//			// end node
//			System.out.println(array_job.get(i));
//			String end_node = array_job.get(i);
//
//			// rel type
//			String rel_type = "job";
//
////			ExecutionResult result8 = engine
////					.execute("START a=node(*), b=node(*) CREATE a-[r:"
////							+ rel_type + "]->b" + " where a.name! = '"
////							+ start_node + "' and b.name! = '" + end_node
////							+ "'  RETURN r");
//			
//			ExecutionResult result8 = engine5
//					.execute("START a=node(*), b=node(*)" +
//							" where a.name! = '" + start_node + "' and b.name! = '" + end_node + "'" +
//							" CREATE a-[r:"	+ rel_type + "]->b RETURN r");
//
//			System.out.println(result8.dumpToString());
//		}
//
///////////////// convert_end
//		
		
		
		
		
		
		
		
		// create node
//		String test_name = "최효석";
//		
//		ExecutionResult result1 = engine.execute("start n=node(*) where n.name! = '" + test_name + "' return n");
//		System.out.println(result1);
//		System.out.println(result1.columns());	// 컬럼 이름 가져오기
		
//		Iterator<Node> test_node = result1.columnAs("n");
//		System.out.println(test_node.hasNext());
		
//		String nodeResult = null;
//		Iterator<Node> ti = result1.columnAs( "n" );
//		System.out.println(ti.hasNext());
		
		// getID
		// propertyKey
		// getProperty(propertyKey)
		
//		while (ti.hasNext()) {
//			Node ti_node = ti.next();
//			System.out.println("Kind #" + ti_node.getId());
//			System.out.println(ti_node.getProperty("name"));
//			for (String propertyKey : ti_node.getPropertyKeys()) {
//				System.out.println("\t" + propertyKey + " : " + ti_node.getProperty(propertyKey));
//			}
//		}
		
		
		
		
				
	
		
//		ExecutionResult result2 = engine.execute("create n = { name : '" + test_name + "', phone : '010 3048 3850'}");
//		System.out.println(result2);
		
		// create relationship
//		ExecutionResult result = engine.execute("start a=node(*), b=node(*) create a-[r:CON]->b where a.name! =\"seok\" and b.name! =\"seok2\"  return r");
//		System.out.println(result);
//		
		
		
		
		// check
		
		ExecutionEngine engine6 = new ExecutionEngine(graphDb);
		
		ExecutionResult result101 = engine6.execute("start n=node(*) return n");
		System.out.println(result101.dumpToString());
		
		ExecutionEngine engine7 = new ExecutionEngine(graphDb);
		
		ExecutionResult result102 = engine7.execute("start r=relationship(*) match p=a-[r]->b return p");
		System.out.println(result102.dumpToString());
		
		
	
	}
}
