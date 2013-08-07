package hs.choi.neo4j.convert;

import java.sql.Connection;
import java.util.Iterator;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.helpers.collection.IteratorUtil;
import org.neo4j.kernel.EmbeddedGraphDatabase;




public class PostgresToNeo4j {

	private static final String DB_PATH = "C:/dev_convert/neo4j-community-1.8.2/data/graph.db";

    static GraphDatabaseService graphDb = new EmbeddedGraphDatabase( DB_PATH );
    
    private static Connection conn;
    
    public static void main( String[] args )
    {
        go_cypher(graphDb);
    }
    
    private static void go_cypher(GraphDatabaseService graphDb) {
		
	       
        // cypher start
        
	
		// 1
//		ExecutionResult result = engine.execute("start n=node(1) return n, n.name");
		
		// 2
//		ExecutionResult result = engine.execute("start Cy=node:node_auto_index(name = 'Cypher')" +
//				" match Cy-[:knows]->person" +
//				" return person.name");
		
		// 3		
//		ExecutionResult result = engine.execute("start Cy=node(0,1,2,3,4) " +
//				"match Cy-[:KNOWS]->person " +
//				"return person.name");
//		System.out.println(result);
		
		// 4		
//		ExecutionResult result1 = engine.execute("start n=node:nodes('username:user0@neo4j.org') return n");
//		ExecutionResult result1 = engine.execute("start n=node:nodes(username='user0@neo4j.org') return n");
//				System.out.println(result1);
		
		// 5		
//		ExecutionResult result2 = engine.execute("start r=relationship(*) return r");
//		System.out.println(result2);
		
//		ExecutionResult result2 = engine.execute("start n=node('name:Thomas Anderson') return n, n.name");		// 안되네
//		System.out.println(result2);
		
		// 6		
//		ExecutionResult result = engine.execute("start a=node(1), b=node(6) match p = shortestPath( a-[*]->b ) return p");
//		System.out.println(result);
		
		// 7	
//		ExecutionResult result = engine.execute("start a=node(4) match a-[KNOWS]-b return b");
//		System.out.println(result);
		
		// 8
//		ExecutionResult result = engine.execute("start a=node(4) where a.name =~ /(?i)cy.*/ return a");
//		System.out.println(result);
		
		// 9
//		ExecutionResult result = engine.execute("start a=node(1) match a-->b return distinct b");
//		System.out.println(result);
		
		// 10
//		ExecutionResult result = engine.execute("start a=node(*) return a order by a.name? ");
//		System.out.println(result);
		
		// 11  facebook
//		ExecutionResult result = engine.execute("start n=node(*), r=relationship(*) return n, r limit 100");
//		System.out.println(result);
		
		//12
//		ExecutionResult result = engine.execute("start r=relationship(*) return r");
//		System.out.println(result);
		
		//13
//		ExecutionResult result = engine.execute("CREATE n = {name : 'Andres', title : 'Developer'}");
//		System.out.println(result);
		
		
		//14 create
//		ExecutionResult result = engine.execute("start n=node(*) return n");
//		System.out.println(result);
		
		//14 create
//		ExecutionResult result = engine.execute("start r=relationship(*) return r");
//		System.out.println(result);
		
		//15 create
//		ExecutionResult result = engine.execute("create n");
//		System.out.println(result);
				
		//16 create
//		ExecutionResult result = engine.execute("create n = { name : 'seok', phone : '010 3048 3850'}");
//		System.out.println(result);
		
		//17 create
//		ExecutionResult result = engine.execute("create n = { name : 'sook' } return n");
//		System.out.println(result);
		
		//18 create
//		ExecutionResult result = engine.execute("start a=node(7), b=node(9) create a-[r:REL]->b return r");
//		System.out.println(result);
		
		//19 create
//		ExecutionResult result = engine.execute("start a=node(6), b=node(9) create a-[r:REL { name : a.name + ' <->' + b.name }]->b return r");
//		System.out.println(result);
		
		//20 create
//		Map<String, Object> props = new HashMap<String, Object>();
//		props.put( "name", "Andres" );
//		props.put( "position", "Developer" );
//		
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put( "props", props );
//		
//		ExecutionResult result = engine.execute("create n = { props }", params);
//		System.out.println(result);
        
		//20 delete
//		ExecutionResult result = engine.execute("start n = node(10) delete n");
//		System.out.println(result);
		
		//21 delete
//		ExecutionResult result = engine.execute("start n = node(9) match n-[r]-() delete n,r");
//		System.out.println(result);
		
		//22 delete
//		ExecutionResult result = engine.execute("start n = node(9) match n-[r]-() delete n,r");
//		System.out.println(result);
		
		//23 delete
//		ExecutionResult result = engine.execute("start n = node(9) delete n.name return n");
//		System.out.println(result);
		
		//24 set
//		ExecutionResult result = engine.execute("start n = node(9) set n.job='student' return n");
//		System.out.println(result);
		
		//24 set
//		ExecutionResult result = engine.execute("start begin=node(1),end=node(2) match p=begin-[*]->end foreach(n in nodes(p) : set n.marked=true)");
//		System.out.println(result);
		
//		ExecutionResult result = engine.execute("start n=node(*) return n");
//		System.out.println(result);
		
//		ExecutionResult result = engine.execute("start r=relationship(*) return r");
//		System.out.println(result);
		
		
//		ExecutionResult result = engine.execute("start n=node(*) return n");
//		ExecutionResult result = engine.execute("start n=node:node_auto_index(name='Hyoseok Choi') return n");
//		System.out.println(result);
		
		
		
//		ExecutionResult result = engine.execute("start r = relationship(*) match a-[r]->b return a.name,b.title,r.date");
//		System.out.println(result);

		
//		ExecutionResult result = engine.execute("start n1=node(*) where n1.course!='master' return n1");
//		System.out.println(result);
		
        // cypher end

		
		
		// project Cypher
		
		// 대여한 사람 , 대여중인 책 검색
//		ExecutionResult result = engine.execute("start r = relationship(*) match a-[r:BORROW]->b return a.name,b.title,r.date order by a.name");
//		System.out.println(result);
		
		// 대여중인 책 검색
//		ExecutionResult result = engine.execute("start r = relationship(*) match a-[r:BORROW]->b return b.No,b.title,b.author");
//		System.out.println(result);
		
		// 대여한 사람 검색
//		ExecutionResult result = engine.execute("start r = relationship(*) match a-[r:BORROW]->() return DISTINCT a");
//		ExecutionResult result = engine.execute("start a = node(*) match a-[r:BORROW]->() return DISTINCT a");
//		System.out.println(result);		// 둘다 같은 결과
	
		// 2006 년도 검색
//		ExecutionResult result = engine.execute("start n = node(*) where n.No !=~ /2006.*/ return n order by n.No");
//		System.out.println(result);
		
		// 조건 (책, 사람 검색할때)
//		ExecutionResult result = engine.execute("start a=node(*) where a.name ! IN ['Hyoseok Choi'] return a");		// name이 Hyoseok Choi인 
//		ExecutionResult result = engine.execute("start a=node(*) where a.name !=~ /.*seok.*/ return a");			// name에 seok이 포함되어 있는
//		System.out.println(result);

		
		// 사람 생성
//		ExecutionResult result = engine.execute("CREATE n = {name : 'test', phone : '010 1234 5678', course : 'master'}");
//		System.out.println(result);

		// 사람 node + rel 생성
//		ExecutionResult result = engine.execute("start n1=node:node_auto_index(name='GIS_lab') CREATE n2 = {name : 'test', phone : '010 1234 5678', course : 'master'} CREATE n2-[r:IS_MEMBER_OF]->n1 return n1,n2,r");
//		ExecutionResult result = engine.execute("start n = node(*) match n-[r]->n2 where n.name!='test' return n,r,n2");
//		System.out.println(result);
		
				
		
		// 책 node + rel 생성
//		ExecutionResult result = engine.execute("start n1=node:node_auto_index(name='GIS_lab') CREATE n2 = {No : '2012-02', title : 'hamlet', author : 'shakespeare'} CREATE n1-[r:HAS_A]->n2 return n1,r,n2");
//		ExecutionResult result = engine.execute("start n = node(*) match n-[r]->n2 where n2.title!='hamlet' return n,r,n2");
//		System.out.println(result);
//		String a = result.toString();
//		System.out.println(a.charAt(0));		
		
		
//		book1.setProperty("No", "2006-01");
//		book1.setProperty("title", "Geography Mark-Up Language : Foundation for the Geo-Web");
//		book1.setProperty("author", "Ron Lake, David s. Burggraf, Milan Trninic, Laurie Rae");
//		book1.setProperty("publisher", "WILEY");
		
		
//		// 사람 삭제 (rel 지우고 noe 지우기)
//		ExecutionResult result = engine.execute("start n=node(*) where n.name!='test' return n ");
//		System.out.println(result);
//		ExecutionResult result = engine.execute("start n=node(*) match ()-[r]->n where n.name!='test' delete r,n");
//		System.out.println(result);
//		ExecutionResult result = engine.execute("start n=node(*) where n.name!='test' delete n");
//		System.out.println(result);
		
		
		
		
		
		// node, property 생성
//		CREATE n = {name : 'Andres', title : 'Developer'}
		
		// rel 생성
//		start a=node(6), b=node(9) create a-[r:REL { name : a.name + ' <->' + b.name }]->b return r
		
		// index를 이용한
//		ExecutionResult result = engine.execute("start n=node:node_auto_index(name='Hyoseok Choi') return n");
//		System.out.println(result);

		
/////////////// postgres_start
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
////			System.out.println("荑쇰━ �ㅽ뻾 �깃났");
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
//		
/////////////// postgres_end
    	
		ExecutionEngine engine = new ExecutionEngine(graphDb);
		
		// create node
//		ExecutionResult result = engine.execute("create n = { name : 'seok2', phone : '010 3048 3850'}");
//		System.out.println(result);
		
		// create node
//		ExecutionResult result = engine.execute("create n = { name : '"+ array_name.get(1) +"', phone : '010 3048 3850'}");
//		System.out.println(result);
		
		// create node
		String test_name = "최효석";
		
		ExecutionResult result1 = engine.execute("start n=node(*) where n.name! = '" + test_name + "' return n");
		System.out.println(result1);
		System.out.println(result1.columns());	// 컬럼 이름 가져오기
		
//		Iterator<Node> test_node = result1.columnAs("n");
//		System.out.println(test_node.hasNext());
		
		String nodeResult;
		Iterator<Node> n_column = result1.columnAs( "n" );
		for ( Node node : IteratorUtil.asIterable( n_column ) )
		{
		    // note: we're grabbing the name property from the node,
		    // not from the n.name in this case.
		    nodeResult = node + ": " + node.getProperty( "name" );
		}
		
//		Iterator<Map<String,Object>> ti = result1.iterator();
//		
//		System.out.println("0");
//        while (ti.hasNext()){ 
//            Map <String,Object> map = ti.next(); 
//            System.out.println(map.get("n").toString());
//            System.out.println("1");
//    } 
//        System.out.println("2");
		
//		Iterator<Node> ti = result1.columnAs("n");
//		System.out.println(ti.toString());	
//		
//		while (ti.hasNext()) {
//			
//			System.out.println(ti.toString());
//			
//		}
		
		
//        CypherParser parser = new CypherParser();
//        ExecutionEngine engine = new ExecutionEngine(graphDb);
//        Query query = parser.parse("start n=node(*) where n.name! = '" + test_name + "' return n");
//        ExecutionResult result1 = engine.execute(query)
//		
//        Iterator<Node> kindsOfFruit = result1.columnAs("n");
//        while (kindsOfFruit.hasNext()) {
//            Node kindOfFruit = kindsOfFruit.next();
//            System.out.println("Kind #" + kindOfFruit.getId());
//            for (String propertyKey : kindOfFruit.getPropertyKeys()) {
//                System.out.println("\t" + propertyKey + " : " + kindOfFruit.getProperty(propertyKey));
//            }
//        }
				
	
		
//		ExecutionResult result2 = engine.execute("create n = { name : '" + test_name + "', phone : '010 3048 3850'}");
//		System.out.println(result2);
		
		// create relationship
//		ExecutionResult result = engine.execute("start a=node(*), b=node(*) create a-[r:CON]->b where a.name! =\"seok\" and b.name! =\"seok2\"  return r");
//		System.out.println(result);
//		
//		// check
//		ExecutionResult result3 = engine.execute("start n=node(*) return n");
//		System.out.println(result3);
//		ExecutionResult result4 = engine.execute("start r=relationship(*) match p=a-[r]->b return p");
//		System.out.println(result4);
	}
}
