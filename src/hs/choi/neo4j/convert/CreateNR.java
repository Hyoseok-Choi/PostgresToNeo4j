package hs.choi.neo4j.convert;

import java.io.File;
import java.io.IOException;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.factory.GraphDatabaseSetting;
import org.neo4j.graphdb.factory.GraphDatabaseSettings;
import org.neo4j.kernel.impl.util.FileUtils;

public class CreateNR {
	private static final String DB_PATH = "target/book";	// db저장되는 위치 이클립스 워크스페이스 밑에

	
		
	// START SNIPPET: vars
	GraphDatabaseService graphDb;
	
	Node lab;
	Node person1 = null;
	Node person2 = null;
	Node person3 = null;
	Node person4 = null;
	Node person5 = null;
	Node person6 = null;
	Node person7 = null;
		
	Node location1 = null;
	Node location2 = null;


	// END SNIPPET: vars

	// START SNIPPET: createReltype
	private static enum RelTypes implements RelationshipType {
		IS_MEMBER_OF, HAS_A, BORROW, IS_LOCATED_IN
	}

	// END SNIPPET: createReltype

	
	// 메인!!
	public static void main(final String[] args) {
		CreateNR hello = new CreateNR();
		hello.createDb();
		//hello.removeData();
		hello.shutDown();
	}
	
	
	// DB생성,	Transaction 있는부분
	void createDb() {
		clearDb(); // db 삭제하는 거야!!! DB_PATH 디렉토리에 있던 파일들 모두 삭제 됨!
		// START SNIPPET: startDb
//		graphDb = new EmbeddedGraphDatabase(DB_PATH);
//		registerShutdownHook(graphDb);			// shutdown hook
		// END SNIPPET: startDb
		
		
		// auto indexing
		graphDb = new GraphDatabaseFactory().
	    newEmbeddedDatabaseBuilder( DB_PATH ).
	    setConfig( GraphDatabaseSettings.node_keys_indexable, "name,title" ).
	    setConfig( GraphDatabaseSettings.relationship_keys_indexable, "date" ).
	    setConfig( GraphDatabaseSettings.node_auto_indexing, GraphDatabaseSetting.TRUE ).
	    setConfig( GraphDatabaseSettings.relationship_auto_indexing, GraphDatabaseSetting.TRUE ).
	    newGraphDatabase();
		
				
		registerShutdownHook(graphDb);			// shutdown hook
		
		
		
//		IndexManager index = graphDb.index();		// indexing 1
//		Index<Node> person = index.forNodes( "person" );
//		Index<Node> book = index.forNodes( "book" );
//		RelationshipIndex bor = index.forRelationships( "bor" );
		

		// START SNIPPET: transaction
		Transaction tx = graphDb.beginTx();
		try {
			
		
			
			
			// Mutating operations go here
			// END SNIPPET: transaction
			// START SNIPPET: addData
			
			location1 = graphDb.createNode();
			location1.setProperty("name", "충무관1101");
			location2 = graphDb.createNode();
			location2.setProperty("name", "충무관1120");
			
					
			person1 = graphDb.createNode();
			person1.setProperty("name", "Minju Kyung");
			person1.setProperty("phone", "010 3382 1625");
			person1.setProperty("course", "doctor");
						
			person2 = graphDb.createNode();
			person2.setProperty("name", "Yangmo Seo");
			person2.setProperty("phone", "010 5143 5616");
			person2.setProperty("course", "master");
					
			person3 = graphDb.createNode();
			person3.setProperty("name", "Hakyung Lee");
			person3.setProperty("phone", "010 7118 8860");
			person3.setProperty("course", "master");
						
			person4 = graphDb.createNode();
			person4.setProperty("name", "Yejin Park");
			person4.setProperty("phone", "010 2021 8360");
			person4.setProperty("course", "master");
						
			person5 = graphDb.createNode();
			person5.setProperty("name", "Enkhbaatar Ariuntsetseg");
			person5.setProperty("phone", "010 9545 9033");
			person5.setProperty("course", "master");
						
			person6 = graphDb.createNode();
			person6.setProperty("name", "Daeho Hur");
			person6.setProperty("phone", "010 9248 4093");
			person6.setProperty("course", "master");
									
			person7 = graphDb.createNode();
			person7.setProperty("name", "Hyoseok Choi");
			person7.setProperty("phone", "010 3048 3850");
			person7.setProperty("course", "master");
			
			
			lab = graphDb.createNode();
			lab.setProperty("name", "GIS_lab");
			lab.setProperty("major", "GIS");
			
			
			// is_loacted_in
			lab.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			person1.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			person2.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			person3.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			person4.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			person5.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			person6.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			person7.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			
			// in_memeber_of
			
			person1.createRelationshipTo(lab, RelTypes.IS_MEMBER_OF);
			person2.createRelationshipTo(lab, RelTypes.IS_MEMBER_OF);
			person3.createRelationshipTo(lab, RelTypes.IS_MEMBER_OF);
			person4.createRelationshipTo(lab, RelTypes.IS_MEMBER_OF);
			person5.createRelationshipTo(lab, RelTypes.IS_MEMBER_OF);
			person6.createRelationshipTo(lab, RelTypes.IS_MEMBER_OF);
			person7.createRelationshipTo(lab, RelTypes.IS_MEMBER_OF);

			
			
			// 2006 start
			
			Node book1 = graphDb.createNode();
			book1.setProperty("No", "2006-01");
			book1.setProperty("title", "Geography Mark-Up Language : Foundation for the Geo-Web");
			book1.setProperty("author", "Ron Lake, David s. Burggraf, Milan Trninic, Laurie Rae");
			book1.setProperty("publisher", "WILEY");
			
			Node book2 = graphDb.createNode();
			book2.setProperty("No", "2006-02");
			book2.setProperty("title", "Oracle Database 10g : The Complete Reference");
			book2.setProperty("author", "Kevin Loney");
			book2.setProperty("publisher", "Osborne");
			
			Node book3 = graphDb.createNode();
			book3.setProperty("No", "2006-03");
			book3.setProperty("title", "Oracle Application Server : Web Development");
			book3.setProperty("author", "Chris Ostrowski, Bradley D.Brown, Forword By Thomas Kurian");
			book3.setProperty("publisher", "Osborne");
			
			Node book4 = graphDb.createNode();
			book4.setProperty("No", "2006-04");
			book4.setProperty("title", "Oracle Application Server 10g : Administration HandBook");
			book4.setProperty("author", "John Garmany, JR.Donald K. Burleson");
			book4.setProperty("publisher", "Osborne");
			
			Node book5 = graphDb.createNode();
			book5.setProperty("No", "2006-05");
			book5.setProperty("title", "Pro Oracle Spatial");
			book5.setProperty("author", "Ravi Kothuri Albert Godfrind Euro Beinat");
			book5.setProperty("publisher", "Apress");
			
			Node book6 = graphDb.createNode();
			book6.setProperty("No", "2006-06");
			book6.setProperty("title", "Oracle Application Server 10g");
			book6.setProperty("author", "Michael Wessler Erin Mulder Rob Harrop Jan Machacek");
			book6.setProperty("publisher", "Apress");
			
			Node book7 = graphDb.createNode();
			book7.setProperty("No", "2006-07");
			book7.setProperty("title", "Oracle Application Server 10g");
			book7.setProperty("author", "Rick Greenwald Robert Stackosiak Donald Bales");
			book7.setProperty("publisher", "O'Relly");
			
			Node book8 = graphDb.createNode();
			book8.setProperty("No", "2006-08");
			book8.setProperty("title", "Spatial Databases with Application to GIS");
			book8.setProperty("author", "Philippe Rigaux");
			book8.setProperty("publisher", "Margan Kaufmann");
			
			Node book9 = graphDb.createNode();
			book9.setProperty("No", "2006-09");
			book9.setProperty("title", "Designing Geodatabases");
			book9.setProperty("author", "David Arctur Michael Zeiler");
			book9.setProperty("publisher", "Esri");
			
			Node book10 = graphDb.createNode();
			book10.setProperty("No", "2006-10");
			book10.setProperty("title", "2006 GIS/RS 공동춘계학술대회 논문집");
			book10.setProperty("author", "many");
			book10.setProperty("publisher", "");
			
			Node book11 = graphDb.createNode();
			book11.setProperty("No", "2006-11");
			book11.setProperty("title", "2006 GIS/RS 공동춘계학술대회 포스터");
			book11.setProperty("author", "many");
			book11.setProperty("publisher", "");
			
			Node book12 = graphDb.createNode();
			book12.setProperty("No", "2006-12");
			book12.setProperty("title", "ro Oracle Spatial(제본)");
			book12.setProperty("author", "Ravi Kothuri Albert Godfrind Euro Beinat");
			book12.setProperty("publisher", "Apress");
			
			Node book13 = graphDb.createNode();
			book13.setProperty("No", "2006-13");
			book13.setProperty("title", "ro Oracle Spatial(제본)");
			book13.setProperty("author", "Ravi Kothuri Albert Godfrind Euro Beinat");
			book13.setProperty("publisher", "Apress");
			
			Node book14 = graphDb.createNode();
			book14.setProperty("No", "2006-14");
			book14.setProperty("title", "Pro .NET Oracle Programming");
			book14.setProperty("author", "Mark A. Williams");
			book14.setProperty("publisher", "Apress");
			
			Node book15 = graphDb.createNode();
			book15.setProperty("No", "2006-15");
			book15.setProperty("title", "Spatial Databases : A TOUR");
			book15.setProperty("author", "SHAS_Ahi Shekhar Sanjay Chawla");
			book15.setProperty("publisher", "Prentice Hall");
			
			Node book16 = graphDb.createNode();
			book16.setProperty("No", "2006-16");
			book16.setProperty("title", "THE CATHEDRAL&THE BAZAAR");
			book16.setProperty("author", "ERIC.S RAYMOND");
			book16.setProperty("publisher", "O'REILLY");
			
			Node book17 = graphDb.createNode();
			book17.setProperty("No", "2006-17");
			book17.setProperty("title", "Succeeding with Open Source");
			book17.setProperty("author", "Bernard Golden");
			book17.setProperty("publisher", "Addison Wesley");
			
			Node book18 = graphDb.createNode();
			book18.setProperty("No", "2006-18");
			book18.setProperty("title", "Beginning MapServer");
			book18.setProperty("author", "Bill Kropla");
			book18.setProperty("publisher", "Apress");
			
			Node book19 = graphDb.createNode();
			book19.setProperty("No", "2006-19");
			book19.setProperty("title", "Foundations of Ajax");
			book19.setProperty("author", "Ryan Asleson&Nathaniel T.Schutta");
			book19.setProperty("publisher", "Apress");
			
			Node book20 = graphDb.createNode();
			book20.setProperty("No", "2006-20");
			book20.setProperty("title", "MAPPING HACKS");
			book20.setProperty("author", "Schuyler Erle, Rich Gibson&Jo Walsh");
			book20.setProperty("publisher", "O'REILLY");
			
			Node book21 = graphDb.createNode();
			book21.setProperty("No", "2006-21");
			book21.setProperty("title", "GOOGLE MAPS HACKS");
			book21.setProperty("author", "Rich Gibson&Schuyler Erle");
			book21.setProperty("publisher", "O'REILLY");
			
			Node book22 = graphDb.createNode();
			book22.setProperty("No", "2006-22");
			book22.setProperty("title", "GOOGLE HACKS");
			book22.setProperty("author", "TARA Calishain&Rael Dornfest");
			book22.setProperty("publisher", "O'REILLY");
			
			Node book23 = graphDb.createNode();
			book23.setProperty("No", "2006-23");
			book23.setProperty("title", "Web Mapping");
			book23.setProperty("author", "Tyler Mitchell");
			book23.setProperty("publisher", "O'REILLY");
			
			Node book24 = graphDb.createNode();
			book24.setProperty("No", "2006-24");
			book24.setProperty("title", "mirror worlds");
			book24.setProperty("author", "David gelernter");
			book24.setProperty("publisher", "OXFORD");
			
			Node book25 = graphDb.createNode();
			book25.setProperty("No", "2006-25");
			book25.setProperty("title", "DEALING WITH DARWIN");
			book25.setProperty("author", "GEOFFREY A. MOORE");
			book25.setProperty("publisher", "PORFOLIO");
			
			Node book26 = graphDb.createNode();
			book26.setProperty("No", "2006-26");
			book26.setProperty("title", "Ajax IN ACTION");
			book26.setProperty("author", "Dave Crane Eric Pascarello with Darren James");
			book26.setProperty("publisher", "MANNING");
			
			Node book27 = graphDb.createNode();
			book27.setProperty("No", "2006-27");
			book27.setProperty("title", "Introdection to Oracle9i:SQL 1");
			book27.setProperty("author", "Oracle");
			book27.setProperty("publisher", "Oracle");
			
			Node book28 = graphDb.createNode();
			book28.setProperty("No", "2006-28");
			book28.setProperty("title", "Introdection to Oracle9i:SQL 2");
			book28.setProperty("author", "Oracle");
			book28.setProperty("publisher", "Oracle");
			
			Node book29 = graphDb.createNode();
			book29.setProperty("No", "2006-29");
			book29.setProperty("title", "Introdection to Oracle9i:SQL 3");
			book29.setProperty("author", "Oracle");
			book29.setProperty("publisher", "Oracle");
			
			Node book30 = graphDb.createNode();
			book30.setProperty("No", "2006-30");
			book30.setProperty("title", "Oracle9i Database Administration Fundamentals 1-1");
			book30.setProperty("author", "Oracle");
			book30.setProperty("publisher", "Oracle");
			
			Node book31 = graphDb.createNode();
			book31.setProperty("No", "2006-31");
			book31.setProperty("title", "Oracle9i Database Administration Fundamentals 1-2");
			book31.setProperty("author", "Oracle");
			book31.setProperty("publisher", "Oracle");
			
			Node book32 = graphDb.createNode();
			book32.setProperty("No", "2006-32");
			book32.setProperty("title", "Oracle9i Database Administration Fundamentals 2-1");
			book32.setProperty("author", "Oracle");
			book32.setProperty("publisher", "Oracle");
			
			Node book33 = graphDb.createNode();
			book33.setProperty("No", "2006-33");
			book33.setProperty("title", "Oracle9i Database Administration Fundamentals 2-2");
			book33.setProperty("author", "Oracle");
			book33.setProperty("publisher", "Oracle");
			
			Node book34 = graphDb.createNode();
			book34.setProperty("No", "2006-34");
			book34.setProperty("title", "Oracle9i Database Performance Tunning 1");
			book34.setProperty("author", "Oracle");
			book34.setProperty("publisher", "Oracle");
			
			Node book35 = graphDb.createNode();
			book35.setProperty("No", "2006-35");
			book35.setProperty("title", "Oracle9i Database Performance Tunning 2");
			book35.setProperty("author", "Oracle");
			book35.setProperty("publisher", "Oracle");
			
			Node book36 = graphDb.createNode();
			book36.setProperty("No", "2006-36");
			book36.setProperty("title", "Oracle9i Database 10g:New Feature for Administrators 1");
			book36.setProperty("author", "Oracle");
			book36.setProperty("publisher", "Oracle");
			
			Node book37 = graphDb.createNode();
			book37.setProperty("No", "2006-37");
			book37.setProperty("title", "Oracle9i Database 10g:New Feature for Administrators 2");
			book37.setProperty("author", "Oracle");
			book37.setProperty("publisher", "Oracle");
			
			Node book38 = graphDb.createNode();
			book38.setProperty("No", "2006-38");
			book38.setProperty("title", "Fundamemtals of Database Systems");
			book38.setProperty("author", "Elmasri&Navathe");
			book38.setProperty("publisher", "Addison Wesley");
			
			Node book39 = graphDb.createNode();
			book39.setProperty("No", "2006-39");
			book39.setProperty("title", "An Introduction to Database Systems");
			book39.setProperty("author", "C.J Date");
			book39.setProperty("publisher", "Addison Wesley");
			
			Node book40 = graphDb.createNode();
			book40.setProperty("No", "2006-40");
			book40.setProperty("title", "A FIRST COURSE IN DATABASE SYSTEMS");
			book40.setProperty("author", "Jeffrey D. Ullman Jennifer widom");
			book40.setProperty("publisher", "Prentice Hall");
			
			Node book41 = graphDb.createNode();
			book41.setProperty("No", "2006-41");
			book41.setProperty("title", "DATABASE SYSTEMS THE COMPLETE BOOK");
			book41.setProperty("author", "Hector garcia-Monlina Jeffrey D. Ullman Jennifer widom");
			book41.setProperty("publisher", "Prentice Hall");
			
			Node book42 = graphDb.createNode();
			book42.setProperty("No", "2006-42");
			book42.setProperty("title", "이펙티브 STL");
			book42.setProperty("author", "Scott Meyers");
			book42.setProperty("publisher", "정보문화사");
			book42.setProperty("etc", "dclee");
			
			Node book43 = graphDb.createNode();
			book43.setProperty("No", "2006-43");
			book43.setProperty("title", "CODE COMPLETE");
			book43.setProperty("author", "스티브 맥코넬");
			book43.setProperty("publisher", "Microsoft Press");
			book43.setProperty("etc", "dclee");
			
			Node book44 = graphDb.createNode();
			book44.setProperty("No", "2006-44");
			book44.setProperty("title", "COME Bible");
			book44.setProperty("author", "김응연");
			book44.setProperty("publisher", "삼양미디어");
			book44.setProperty("etc", "dclee");
			
			Node book45 = graphDb.createNode();
			book45.setProperty("No", "2006-45");
			book45.setProperty("title", "Oracle JDBC JSP");
			book45.setProperty("author", "이건영/정철");
			book45.setProperty("publisher", "네오솔루션");
			
			Node book46 = graphDb.createNode();
			book46.setProperty("No", "2006-46");
			book46.setProperty("title", "Beginning JAVA2 SDK 1.4 Edition");
			book46.setProperty("author", "아이버 호튼");
			book46.setProperty("publisher", "정보문화사");
			
			Node book47 = graphDb.createNode();
			book47.setProperty("No", "2006-47");
			book47.setProperty("title", "Beginning Oracle Programming");
			book47.setProperty("author", "Sean Dillon");
			book47.setProperty("publisher", "정보문화사");
			
			Node book48 = graphDb.createNode();
			book48.setProperty("No", "2006-48");
			book48.setProperty("title", "기초에서 실무 개발자로 Oracle 프로젝트 실무");
			book48.setProperty("author", "이공명");
			book48.setProperty("publisher", "컴스페이스");
			
			Node book49 = graphDb.createNode();
			book49.setProperty("No", "2006-49");
			book49.setProperty("title", "The Data Warehouse Toolkit");
			book49.setProperty("author", "Ralph Kimball");
			book49.setProperty("publisher", "WILEY");
			
			Node book50 = graphDb.createNode();
			book50.setProperty("No", "2006-50");
			book50.setProperty("title", "COMPUTATIONAL GEOMETRY");
			book50.setProperty("author", "Franco P.Preparata Michael lan Shamos");
			book50.setProperty("publisher", "Springer");
			
			Node book51 = graphDb.createNode();
			book51.setProperty("No", "2006-51");
			book51.setProperty("title", "The Data Warehouse ETL Toolkit");
			book51.setProperty("author", "Ralph kimball Joe Caserta");
			book51.setProperty("publisher", "WILEY");
			
			Node book52 = graphDb.createNode();
			book52.setProperty("No", "2006-52");
			book52.setProperty("title", "Mastering Data Warehouse Design");
			book52.setProperty("author", "Claudia Imhoff Nicholas Galemmo Jonathan G.Geige");
			book52.setProperty("publisher", "WILEY");
			
			Node book53 = graphDb.createNode();
			book53.setProperty("No", "2006-53");
			book53.setProperty("title", "Building the Data Warehouse");
			book53.setProperty("author", "William H.Inmon");
			book53.setProperty("publisher", "WILEY");
			
			Node book54 = graphDb.createNode();
			book54.setProperty("No", "2006-54");
			book54.setProperty("title", "Foundations of Multidimensional and Metric Data Structures");
			book54.setProperty("author", "Hanan Samet");
			book54.setProperty("publisher", "MORGAN KAUFMANN");
			
			Node book55 = graphDb.createNode();
			book55.setProperty("No", "2006-55");
			book55.setProperty("title", "Pro Oracle Spatial");
			book55.setProperty("author", "Ravi Kothuri Albert Godfrind Euro Beinat");
			book55.setProperty("publisher", "APRESS");
			book55.setProperty("ebook", true);
			
			
			book1.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book2.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book3.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book4.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book5.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book6.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book7.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book8.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book9.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book10.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book11.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book12.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book13.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book14.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book15.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book16.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book17.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book18.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book19.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book20.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book21.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book22.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book23.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book24.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book25.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book26.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book27.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book28.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book29.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book30.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book31.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book32.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book33.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book34.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book35.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book36.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book37.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book38.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book39.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book40.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book41.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book42.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book43.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book44.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book45.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book46.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book47.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book48.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book49.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book50.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book51.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book52.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book53.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book54.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book55.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			
			// 2006 end
			
			
			// 2007 start
			
			Node book56 = graphDb.createNode();
			book56.setProperty("No", "2007-01");
			book56.setProperty("title", "뇌를 자극하는 Redhat Fedora");
			book56.setProperty("author", "우재남");
			book56.setProperty("publisher", "한빛미디어");
			
			Node book57 = graphDb.createNode();
			book57.setProperty("No", "2007-02");
			book57.setProperty("title", "Head First Servlets & JSP");
			book57.setProperty("author", "케이시 시에라, 버트 베이츠, 브라얀 바샴");
			book57.setProperty("publisher", "O'REILLY 한빛미디어");
			
			Node book58 = graphDb.createNode();
			book58.setProperty("No", "2007-03");
			book58.setProperty("title", "Innovations in 3D Geo Information Systems");
			book58.setProperty("author", "Allias Abdul-Rahman Sisi Zlatanova Volker Coors");
			book58.setProperty("publisher", "Springer");
			book58.setProperty("etc", "dclee");
			
			Node book59 = graphDb.createNode();
			book59.setProperty("No", "2007-04");
			book59.setProperty("title", "MATLAB을 활용한 실용 디지털 영상처리");
			book59.setProperty("author", "정성환 이문호");
			book59.setProperty("publisher", "홍릉 과학출판사");
			book59.setProperty("etc", "dclee");
			
			Node book60 = graphDb.createNode();
			book60.setProperty("No", "2007-05");
			book60.setProperty("title", "Geometric Tools for Computer Graphics");
			book60.setProperty("author", "Philip J.Schneider David H.Eberly");
			book60.setProperty("publisher", "Morgan Kaufmann");
			book60.setProperty("etc", "dclee");
			
			Node book61 = graphDb.createNode();
			book61.setProperty("No", "2007-06");
			book61.setProperty("title", "MATLAB을 이용한 영상처리");
			book61.setProperty("author", "이임건 박성대 한수환");
			book61.setProperty("publisher", "도서출판 미래컴");
			book61.setProperty("etc", "dclee");
			
			Node book62 = graphDb.createNode();
			book62.setProperty("No", "2007-07");
			book62.setProperty("title", "HANDBOOK OF MATHEMATICS");
			book62.setProperty("author", "I.N.Bronshtein K.A.Semendyayev G.Musiol H.Muehlig");
			book62.setProperty("publisher", "Springer");
			book62.setProperty("etc", "dclee");
			
			Node book63 = graphDb.createNode();
			book63.setProperty("No", "2007-08");
			book63.setProperty("title", "고수로 가는 지름길 JSP 이렇게 시작하세요");
			book63.setProperty("author", "컴스페이스");
			book63.setProperty("publisher", "김형준");
			book63.setProperty("etc", "dclee");
			
			Node book64 = graphDb.createNode();
			book64.setProperty("No", "2007-09");
			book64.setProperty("title", "Professional JSP 2nd Edition");
			book64.setProperty("author", "정보문화사");
			book64.setProperty("publisher", "Simon Brown");
			
			Node book65 = graphDb.createNode();
			book65.setProperty("No", "2007-10");
			book65.setProperty("title", "XML Development with Java Technology");
			book65.setProperty("author", "Ajay Vohra Deepak Vohra");
			book65.setProperty("publisher", "Apress");
			book65.setProperty("ebook", true);
			
			Node book66 = graphDb.createNode();
			book66.setProperty("No", "2007-11");
			book66.setProperty("title", "SCJP Exam for J2SE 5");
			book66.setProperty("author", "Paul Sanghera Ph.D");
			book66.setProperty("publisher", "Apress");
			book66.setProperty("ebook", true);
			
			Node book67 = graphDb.createNode();
			book67.setProperty("No", "2007-12");
			book67.setProperty("title", "Beginning EJB 3 Application Development");
			book67.setProperty("author", "Raghu R. Kodali Jonathan wetherbee");
			book67.setProperty("publisher", "Apress");
			book67.setProperty("ebook", true);
			
			Node book68 = graphDb.createNode();
			book68.setProperty("No", "2007-13");
			book68.setProperty("title", "리눅스 서버관리 실무 바이블 (상)");
			book68.setProperty("author", "박성수");
			book68.setProperty("publisher", "수퍼유저코리아");
			
			Node book69 = graphDb.createNode();
			book69.setProperty("No", "2007-14");
			book69.setProperty("title", "리눅스 서버관리 실무 바이블 (하)");
			book69.setProperty("author", "박성수");
			book69.setProperty("publisher", "수퍼유저코리아");
			
			Node book70 = graphDb.createNode();
			book70.setProperty("No", "2007-15");
			book70.setProperty("title", "Oracle Database Programming using JAVA and Web Services");
			book70.setProperty("author", "Kuassi Mensah");
			book70.setProperty("publisher", "Digital Press");
			
			Node book71 = graphDb.createNode();
			book71.setProperty("No", "2007-16");
			book71.setProperty("title", "DIGITAL GEOMETRY (Grometric Methods for Digital Picture Analysis)");
			book71.setProperty("author", "Reinhard Klette Azriel Rosenfeld");
			book71.setProperty("publisher", "MORGAN KAUFMANN");
			book71.setProperty("etc", "dclee");
			
			Node book72 = graphDb.createNode();
			book72.setProperty("No", "2007-17");
			book72.setProperty("title", "Handbook of Image & Video Processing");
			book72.setProperty("author", "Al Bovik");
			book72.setProperty("publisher", "ELSEVIER");
			
			Node book73 = graphDb.createNode();
			book73.setProperty("No", "2007-18");
			book73.setProperty("title", "Integrating GIS and data warehousing in a Web environment");
			book73.setProperty("author", "R.G.Healey");
			book73.setProperty("publisher", "Taylor and Francis");
			
			Node book74 = graphDb.createNode();
			book74.setProperty("No", "2007-19");
			book74.setProperty("title", "Perceptions:Development of a Spatial Information Industry for the Seoul Metropolitan Area");
			book74.setProperty("author", "Dr Jane Drummond FRGS MRICS MInstCES");
			book74.setProperty("publisher", "University of GLASGOW");
			
			Node book75 = graphDb.createNode();
			book75.setProperty("No", "2007-20");
			book75.setProperty("title", "Beginnign XML with DOM and Ajax");
			book75.setProperty("author", "Sas Jacobs");
			book75.setProperty("publisher", "Apress");
			book75.setProperty("ebook", true);
			
			Node book76 = graphDb.createNode();
			book76.setProperty("No", "2007-21");
			book76.setProperty("title", "Pro JSP 2 4th");
			book76.setProperty("author", "Simon Brown");
			book76.setProperty("publisher", "Apress");
			book76.setProperty("ebook", true);
			
			Node book77 = graphDb.createNode();
			book77.setProperty("No", "2007-22");
			book77.setProperty("title", "Pro Eclipse JST plug-ins for J2EE Development");
			book77.setProperty("author", "Christopher M. Judd and Hakeem Shittu");
			book77.setProperty("publisher", "Apress");
			book77.setProperty("ebook", true);
			
			Node book78 = graphDb.createNode();
			book78.setProperty("No", "2007-23");
			book78.setProperty("title", "Pro Apache Tomcat 6");
			book78.setProperty("author", "matthew Moodie");
			book78.setProperty("publisher", "Apress");
			book78.setProperty("ebook", true);
			
			Node book79 = graphDb.createNode();
			book79.setProperty("No", "2007-24");
			book79.setProperty("title", "JDBC Recipes A problem-solution approach ");
			book79.setProperty("author", "Mahmoud Parsian");
			book79.setProperty("publisher", "Apress");
			book79.setProperty("ebook", true);
			
			Node book80 = graphDb.createNode();
			book80.setProperty("No", "2007-25");
			book80.setProperty("title", "Beginnign HTML with CSS and XHTML");
			book80.setProperty("author", "David Schultz and Cruig Cook");
			book80.setProperty("publisher", "Apress");
			book80.setProperty("ebook", true);
			
			Node book81 = graphDb.createNode();
			book81.setProperty("No", "2007-26");
			book81.setProperty("title", "자바 스크립트 for 웹 2.0");
			book81.setProperty("author", "셀리 파워즈");
			book81.setProperty("publisher", "O'REILLY");
			
			Node book82 = graphDb.createNode();
			book82.setProperty("No", "2007-27");
			book82.setProperty("title", "열혈강의 EJB Programming");
			book82.setProperty("author", "이성희");
			book82.setProperty("publisher", "FREELEC");
			
			Node book83 = graphDb.createNode();
			book83.setProperty("No", "2007-28");
			book83.setProperty("title", "Head First EJB");
			book83.setProperty("author", "케이시 시에라 버트 베이츠");
			book83.setProperty("publisher", "O'REILLY");
			
			Node book84 = graphDb.createNode();
			book84.setProperty("No", "2007-29");
			book84.setProperty("title", "Java Extreme Programming Cookbook");
			book84.setProperty("author", "Eric M.Burke&Brian M. Coyner");
			book84.setProperty("publisher", "O'REILLY");
			
			Node book85 = graphDb.createNode();
			book85.setProperty("No", "2007-30");
			book85.setProperty("title", "Agile Java Development with Spring, Hibernate and Eclipse");
			book85.setProperty("author", "Scott W. Ambler & Rod Johnson");
			book85.setProperty("publisher", "Developer's Library");
			
			Node book86 = graphDb.createNode();
			book86.setProperty("No", "2007-31");
			book86.setProperty("title", "Eclipse");
			book86.setProperty("author", "Steve Holzner");
			book86.setProperty("publisher", "O'REILLY");
			
			Node book87 = graphDb.createNode();
			book87.setProperty("No", "2007-32");
			book87.setProperty("title", "The Java Developer's Guide to ECLIPSE");
			book87.setProperty("author", "D'Anjou Fairbrother Kehn, MaCarthy Kellerman");
			book87.setProperty("publisher", "Addison Wesley");
			
			Node book88 = graphDb.createNode();
			book88.setProperty("No", "2007-33");
			book88.setProperty("title", "eclipse Rich Client Platform");
			book88.setProperty("author", "Jeff McAffer Jean-Michel Lemieux");
			book88.setProperty("publisher", "Addison Wesley");
			
			Node book89 = graphDb.createNode();
			book89.setProperty("No", "2007-34");
			book89.setProperty("title", "Java Example In a Nutshell");
			book89.setProperty("author", "David Flanagan");
			book89.setProperty("publisher", "O'REILLY");
			
			Node book90 = graphDb.createNode();
			book90.setProperty("No", "2007-35");
			book90.setProperty("title", "SCJD Exam with J2SE 5");
			book90.setProperty("author", "Andrew Monkhouse Terry Camerlengo");
			book90.setProperty("publisher", "Apress");
			book90.setProperty("ebook", true);
			
			Node book91 = graphDb.createNode();
			book91.setProperty("No", "2007-36");
			book91.setProperty("title", "Processing A Programming Handbook Visual Designers and Artists");
			book91.setProperty("author", "Casey Reas Ben Fry");
			book91.setProperty("publisher", "John Maeda");
			
			Node book92 = graphDb.createNode();
			book92.setProperty("No", "2007-37");
			book92.setProperty("title", "Processiong Creative Coding and Computational Art");
			book92.setProperty("author", "Ira Greenberg");
			book92.setProperty("publisher", "Keith Peters");
			
			Node book93 = graphDb.createNode();
			book93.setProperty("No", "2007-38");
			book93.setProperty("title", "Red Hat Linux 9");
			book93.setProperty("author", "박승규");
			book93.setProperty("publisher", "한빛미디어");
			book93.setProperty("ebook", true);
			
			Node book94 = graphDb.createNode();
			book94.setProperty("No", "2007-39");
			book94.setProperty("title", "Pro JavaScript Techniques");
			book94.setProperty("author", "John Resig");
			book94.setProperty("publisher", "Apress");
			
			Node book95 = graphDb.createNode();
			book95.setProperty("No", "2007-40");
			book95.setProperty("title", "The Definitive Guide to Java Swing");
			book95.setProperty("author", "John Zukowski");
			book95.setProperty("publisher", "Apress");
			book95.setProperty("ebook", true);
			
			Node book96 = graphDb.createNode();
			book96.setProperty("No", "2007-41");
			book96.setProperty("title", "Professional Java Script for web Deveiopers");
			book96.setProperty("author", "Nicholas C. Zakas");
			book96.setProperty("publisher", "wrox");
			
			Node book97 = graphDb.createNode();
			book97.setProperty("No", "2007-42");
			book97.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book97.setProperty("author", "Ayman F.Habib");
			book97.setProperty("publisher", "보고서");
			book97.setProperty("etc", "dclee");
			
			Node book98 = graphDb.createNode();
			book98.setProperty("No", "2007-43");
			book98.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book98.setProperty("author", "Ayman F.Habib");
			book98.setProperty("publisher", "보고서");
						
			Node book99 = graphDb.createNode();
			book99.setProperty("No", "2007-44");
			book99.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book99.setProperty("author", "Ayman F.Habib");
			book99.setProperty("publisher", "보고서");
						
			Node book100 = graphDb.createNode();
			book100.setProperty("No", "2007-45");
			book100.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book100.setProperty("author", "Ayman F.Habib");
			book100.setProperty("publisher", "보고서");
			
			Node book101 = graphDb.createNode();
			book101.setProperty("No", "2007-46");
			book101.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book101.setProperty("author", "Ayman F.Habib");
			book101.setProperty("publisher", "보고서");
			
			Node book102 = graphDb.createNode();
			book102.setProperty("No", "2007-47");
			book102.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book102.setProperty("author", "Ayman F.Habib");
			book102.setProperty("publisher", "보고서");
			
			Node book103 = graphDb.createNode();
			book103.setProperty("No", "2007-48");
			book103.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book103.setProperty("author", "Ayman F.Habib");
			book103.setProperty("publisher", "보고서");
			
			Node book104 = graphDb.createNode();
			book104.setProperty("No", "2007-49");
			book104.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book104.setProperty("author", "Ayman F.Habib");
			book104.setProperty("publisher", "보고서");
			
			Node book105 = graphDb.createNode();
			book105.setProperty("No", "2007-50");
			book105.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book105.setProperty("author", "Ayman F.Habib");
			book105.setProperty("publisher", "보고서");
			
			Node book106 = graphDb.createNode();
			book106.setProperty("No", "2007-51");
			book106.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book106.setProperty("author", "Ayman F.Habib");
			book106.setProperty("publisher", "보고서");
			
			Node book107 = graphDb.createNode();
			book107.setProperty("No", "2007-52");
			book107.setProperty("title", "Algorithmic Development for Rapid Updating of GIS Database Using Multi-Source Geo-Spatial Data");
			book107.setProperty("author", "Ayman F.Habib");
			book107.setProperty("publisher", "보고서");
			
			Node book108 = graphDb.createNode();
			book108.setProperty("No", "2007-53");
			book108.setProperty("title", "Representations of SPACE AND TIME");
			book108.setProperty("author", "Donna J. Peuquet");
			book108.setProperty("publisher", "Guilfford");
			

			
			book56.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book57.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book58.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book59.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book60.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book61.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book62.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book63.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book64.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book65.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book66.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book67.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book68.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book69.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book70.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book71.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book72.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book73.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book74.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book75.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book76.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book77.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book78.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book79.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book80.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book81.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book82.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book83.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book84.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book85.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book86.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book87.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book88.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book89.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book90.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book91.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book92.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book93.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book94.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book95.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book96.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book97.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book98.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book99.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book100.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book101.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book102.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book103.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book104.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book105.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book106.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book107.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book108.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);

			// 2007 end
			
			
			
			
			
			// 2008 start
			
			Node book109 = graphDb.createNode();
			book109.setProperty("No", "2008-01");
			book109.setProperty("title", "Practical Web 2.0 Application with PHP");
			book109.setProperty("author", "Quentin Zervaas");
			book109.setProperty("publisher", "Apress");
			book109.setProperty("ebook", true);
			
			Node book110 = graphDb.createNode();
			book110.setProperty("No", "2008-02");
			book110.setProperty("title", "PHP Objects, Patterns, and Practice");
			book110.setProperty("author", "Matt Zandstra");
			book110.setProperty("publisher", "Apress");
			book110.setProperty("ebook", true);
			
			Node book111 = graphDb.createNode();
			book111.setProperty("No", "2008-03");
			book111.setProperty("title", "후니의 쉽게 쓴 시스코 네트워킹");
			book111.setProperty("author", "진강훈");
			book111.setProperty("publisher", "사이버 출판사");
			
			Node book112 = graphDb.createNode();
			book112.setProperty("No", "2008-04");
			book112.setProperty("title", "Pro Spring");
			book112.setProperty("author", "Rob Harrop Jan Machac");
			book112.setProperty("publisher", "Apress");
			book112.setProperty("ebook", true);
			
			Node book113 = graphDb.createNode();
			book113.setProperty("No", "2008-05");
			book113.setProperty("title", "유닉스 리눅스 명령어 사전");
			book113.setProperty("author", "박종오, 우종경");
			book113.setProperty("publisher", "한빛미디어");
			
			Node book114 = graphDb.createNode();
			book114.setProperty("No", "2008-06");
			book114.setProperty("title", "이클립스 RCP");
			book114.setProperty("author", "Jeff McAffer,Jean-Micjael Lemieux");
			book114.setProperty("publisher", "에이콘");
			
			Node book115 = graphDb.createNode();
			book115.setProperty("No", "2008-07");
			book115.setProperty("title", "자바 웹 프로그램밍");
			book115.setProperty("author", "황희정");
			book115.setProperty("publisher", "한빛미디어");
			
			Node book116 = graphDb.createNode();
			book116.setProperty("No", "2008-08");
			book116.setProperty("title", "PostgreSQL 8 FOR WINDOWS");
			book116.setProperty("author", "Ricjard Blum");
			book116.setProperty("publisher", "Osborne");
			
			Node book117 = graphDb.createNode();
			book117.setProperty("No", "2008-09");
			book117.setProperty("title", "리치클라이언트 개발을 위한 이클립스 SWT");
			book117.setProperty("author", "박용우");
			book117.setProperty("publisher", "성안당");
			
			Node book118 = graphDb.createNode();
			book118.setProperty("No", "2008-10");
			book118.setProperty("title", "Design Patterns");
			book118.setProperty("author", "Erch Gamma");
			book118.setProperty("publisher", "Addison-Wesley");
			
			Node book119 = graphDb.createNode();
			book119.setProperty("No", "2008-11");
			book119.setProperty("title", "MAYA 7.0 50일 완성");
			book119.setProperty("author", "이승엽 정재환");
			book119.setProperty("publisher", "가메출판사");
			
			Node book120 = graphDb.createNode();
			book120.setProperty("No", "2008-12");
			book120.setProperty("title", "3DS MAX 9");
			book120.setProperty("author", "김상윤");
			book120.setProperty("publisher", "DIGITAL BOOKS");
			
			Node book121 = graphDb.createNode();
			book121.setProperty("No", "2008-13");
			book121.setProperty("title", "JAVA in a nutshell");
			book121.setProperty("author", "David Flanagan");
			book121.setProperty("publisher", "O'REILLY");
			
			Node book122 = graphDb.createNode();
			book122.setProperty("No", "2008-14");
			book122.setProperty("title", "Design Patterns JAVA Workbook");
			book122.setProperty("author", "Steven John Metsker");
			book122.setProperty("publisher", "Addison-wesley");
			
			Node book123 = graphDb.createNode();
			book123.setProperty("No", "2008-15");
			book123.setProperty("title", "Parsing Techniques");
			book123.setProperty("author", "Dick Grune Ceriel J.H Jacobs");
			book123.setProperty("publisher", "Springer");
			
			Node book124 = graphDb.createNode();
			book124.setProperty("No", "2008-16");
			book124.setProperty("title", "Digital Elevation Model Technologies and Applications : The DEM Users Manual");
			book124.setProperty("author", "David F.Maune PhD CP");
			book124.setProperty("publisher", "ASPRS");
			book124.setProperty("etc", "dclee");
			
			Node book125 = graphDb.createNode();
			book125.setProperty("No", "2008-17");
			book125.setProperty("title", "JAVA IN A NUTSHEELL A Desktop  Quick Reference ");
			book125.setProperty("author", "David Flanagan");
			book125.setProperty("publisher", "O'REILLY");
			
			Node book126 = graphDb.createNode();
			book126.setProperty("No", "2008-18");
			book126.setProperty("title", "Geometric Partial Differential Equations and Image Analysis");
			book126.setProperty("author", "Guillermo Sapiro");
			book126.setProperty("publisher", "Cambridge");
			book126.setProperty("etc", "dclee");
			
			Node book127 = graphDb.createNode();
			book127.setProperty("No", "2008-19");
			book127.setProperty("title", "Remote sensing of Impervious surfaces");
			book127.setProperty("author", "Qihao Weng");
			book127.setProperty("publisher", "CRC Press");
			book127.setProperty("etc", "dclee");
			
			Node book128 = graphDb.createNode();
			book128.setProperty("No", "2008-20");
			book128.setProperty("title", "Contributing to eclipse Principles, Patterns, and Plug-ins");
			book128.setProperty("author", "Erich Gamma, Kent Beck");
			book128.setProperty("publisher", "Addison Wesley");
			
			Node book129 = graphDb.createNode();
			book129.setProperty("No", "2008-21");
			book129.setProperty("title", "Visual C++ 6.0과 GNU 컴파일러로 구현하는 수치해석학 2004");
			book129.setProperty("author", "최행진");
			book129.setProperty("publisher", "아진");
			
			Node book130 = graphDb.createNode();
			book130.setProperty("No", "2008-22");
			book130.setProperty("title", "C로 구현한 수치해석");
			book130.setProperty("author", "지영준, 김화준, 허정권");
			book130.setProperty("publisher", "높이깊이");
			book130.setProperty("etc", "dclee");
			
			Node book131 = graphDb.createNode();
			book131.setProperty("No", "2008-23");
			book131.setProperty("title", "Pro Java 6 3D Game Development");
			book131.setProperty("author", "Andrew Davison");
			book131.setProperty("publisher", "Apress");
			book131.setProperty("ebook", true);
			
			Node book132 = graphDb.createNode();
			book132.setProperty("No", "2008-24");
			book132.setProperty("title", "Mathematical handbook for scientists and engineers");
			book132.setProperty("author", "Granino A. Korn Theresa M. Korn");
			book132.setProperty("publisher", "Dover");
			
			Node book133 = graphDb.createNode();
			book133.setProperty("No", "2008-25");
			book133.setProperty("title", "Handbook of Mathematical Models in Computer Vision");
			book133.setProperty("author", "Nikos Paragios Yunmei Chen Olivier Faugeras");
			book133.setProperty("publisher", "Springer");
			
			Node book134 = graphDb.createNode();
			book134.setProperty("No", "2008-26");
			book134.setProperty("title", "Mathematical Handbook of Formulas and tables");
			book134.setProperty("author", "Murray R. Spiegel John Liu Seymour Lipschutz");
			book134.setProperty("publisher", "McGraw Hill");
			
			Node book135 = graphDb.createNode();
			book135.setProperty("No", "2008-27");
			book135.setProperty("title", "Geometric Programming Using Open Geometry GL");
			book135.setProperty("author", "Georg Glaeser Hans-Peter Schrocker");
			book135.setProperty("publisher", "Springer");
			
			Node book136 = graphDb.createNode();
			book136.setProperty("No", "2008-28");
			book136.setProperty("title", "Pro OGRE 3D Programming");
			book136.setProperty("author", "Gregory Junker ");
			book136.setProperty("publisher", "Apress");
			book136.setProperty("ebook", true);


			
			book109.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book110.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book111.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book112.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book113.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book114.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book115.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book116.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book117.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book118.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book119.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book120.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book121.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book122.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book123.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book124.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book125.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book126.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book127.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book128.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book129.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book130.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book131.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book132.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book133.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book134.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book135.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book136.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			
			
			
			// 2008 end
			
			
			// 2009 start
			
			Node book137 = graphDb.createNode();
			book137.setProperty("No", "2009-01");
			book137.setProperty("title", "Beginning Spatial with SQL Server 2008");
			book137.setProperty("author", "Alastair Aitchison");
			book137.setProperty("publisher", "Apress");
			book137.setProperty("ebook", true);
			
			Node book138 = graphDb.createNode();
			book138.setProperty("No", "2009-02");
			book138.setProperty("title", "Eclipse Rich Ajax Platform");
			book138.setProperty("author", "Fabian Lange");
			book138.setProperty("publisher", "Apress");
			book138.setProperty("ebook", true);
			
			Node book139 = graphDb.createNode();
			book139.setProperty("No", "2009-03");
			book139.setProperty("title", "Foundations of SQL Server 2005 Business Intelligence");
			book139.setProperty("author", "Lynn Langit");
			book139.setProperty("publisher", "Apress");
			book139.setProperty("ebook", true);
			
			Node book140 = graphDb.createNode();
			book140.setProperty("No", "2009-04");
			book140.setProperty("title", "Practical Eclipse Rich Client Platform Projects");
			book140.setProperty("author", "Vladimir Silva");
			book140.setProperty("publisher", "Apress");
			book140.setProperty("ebook", true);
			
			Node book141 = graphDb.createNode();
			book141.setProperty("No", "2009-05");
			book141.setProperty("title", "The Definitive Guide to SWTand JFace");
			book141.setProperty("author", "Rob Warner with Robert Harris");
			book141.setProperty("publisher", "Apress");
			book141.setProperty("ebook", true);
			
			Node book142 = graphDb.createNode();
			book142.setProperty("No", "2009-06");
			book142.setProperty("title", "자카르타 스트럿츠 프로그래밍");
			book142.setProperty("author", "처크 캐버네스");
			book142.setProperty("publisher", "한빛미디어");
			
			Node book143 = graphDb.createNode();
			book143.setProperty("No", "2009-07");
			book143.setProperty("title", "스프링 인 액션");
			book143.setProperty("author", "크레이그 월즈");
			book143.setProperty("publisher", "ITC");
			
			Node book144 = graphDb.createNode();
			book144.setProperty("No", "2009-08");
			book144.setProperty("title", "스트럿츠 프레임워크 워크북");
			book144.setProperty("author", "박재성");
			book144.setProperty("publisher", "한빛미디어");
			
			Node book145 = graphDb.createNode();
			book145.setProperty("No", "2009-09");
			book145.setProperty("title", "SWT/JFace 인 액션");
			book145.setProperty("author", "Matthew Scarpino");
			book145.setProperty("publisher", "에이콘");
			
			Node book146 = graphDb.createNode();
			book146.setProperty("No", "2009-10");
			book146.setProperty("title", "이클립스 프로젝트 필수 유틸리티");
			book146.setProperty("author", "민진우");
			book146.setProperty("publisher", "한빛미디어");
			
			Node book147 = graphDb.createNode();
			book147.setProperty("No", "2009-11");
			book147.setProperty("title", "이클립스 실전 플러그인 개발");
			book147.setProperty("author", "에릭 클레이버그");
			book147.setProperty("publisher", "에이콘");
			
			Node book148 = graphDb.createNode();
			book148.setProperty("No", "2009-12");
			book148.setProperty("title", "자바 프로젝트 필수 유틸리티");
			book148.setProperty("author", "박재성");
			book148.setProperty("publisher", "한빛미디어");
			
			Node book149 = graphDb.createNode();
			book149.setProperty("No", "2009-13");
			book149.setProperty("title", "The Mythical Man-Month");
			book149.setProperty("author", "Frederick P.Brooks JR");
			book149.setProperty("publisher", "Addison Wesley");
			
			Node book150 = graphDb.createNode();
			book150.setProperty("No", "2009-14");
			book150.setProperty("title", "Java Web Services");
			book150.setProperty("author", "Martin Kalim");
			book150.setProperty("publisher", "OREILLY");
			
			Node book151 = graphDb.createNode();
			book151.setProperty("No", "2009-15");
			book151.setProperty("title", "The KML Hand book");
			book151.setProperty("author", "Josie wernecke");
			book151.setProperty("publisher", "Addison Wesley");
			
			Node book152 = graphDb.createNode();
			book152.setProperty("No", "2009-16");
			book152.setProperty("title", "eclipse Plug-ins Third Edition");
			book152.setProperty("author", "Eric Clayberg, Dan Rubel");
			book152.setProperty("publisher", "Addison Wesley");
			
			Node book153 = graphDb.createNode();
			book153.setProperty("No", "2009-17");
			book153.setProperty("title", "Business Intelligence Roadmap");
			book153.setProperty("author", "Larissa T.Moss, Shaku Atre");
			book153.setProperty("publisher", "Addison Wesley");
			
			Node book154 = graphDb.createNode();
			book154.setProperty("No", "2009-18");
			book154.setProperty("title", "Head First Java Script");
			book154.setProperty("author", "마이클 모리슨");
			book154.setProperty("publisher", "O'REILLY 한빛미디어");
			
			Node book155 = graphDb.createNode();
			book155.setProperty("No", "2009-19");
			book155.setProperty("title", "Head First HTML with CSS & XHTML");
			book155.setProperty("author", "에리자베스 프리먼 에릭 프리먼");
			book155.setProperty("publisher", "O'REILLY 한빛미디어");
			
			Node book156 = graphDb.createNode();
			book156.setProperty("No", "2009-20");
			book156.setProperty("title", "인터넷 프로그래밍 기초");
			book156.setProperty("author", "고민정");
			book156.setProperty("publisher", "한빛미디어");
			book156.setProperty("etc", "도서관 반납");
			
			Node book157 = graphDb.createNode();
			book157.setProperty("No", "2009-21");
			book157.setProperty("title", "Open Source GIS:A GRASS GIS Approach");
			book157.setProperty("author", "Neteler, Markus ");
			book157.setProperty("publisher", "Springer");
			
			Node book158 = graphDb.createNode();
			book158.setProperty("No", "2009-22");
			book158.setProperty("title", "Learning ExtJS");
			book158.setProperty("author", "Shea Frederick");
			book158.setProperty("publisher", "Packt Publishing");
			
			Node book159 = graphDb.createNode();
			book159.setProperty("No", "2009-23");
			book159.setProperty("title", "Image Processing The Fundamentals");
			book159.setProperty("author", "Maria Petrou, Panagiota Bosdogianni");
			book159.setProperty("publisher", "WILEY");
			book159.setProperty("etc", "delee");
			book159.setProperty("ebook", true);
			
			Node book160 = graphDb.createNode();
			book160.setProperty("No", "2009-24");
			book160.setProperty("title", "오픈소스 GS를 이용한 디지털 영상처리 기본 프로그래밍");
			book160.setProperty("author", "정성환, 이문호");
			book160.setProperty("publisher", "홍릉과학출판사");
			
			Node book161 = graphDb.createNode();
			book161.setProperty("No", "2009-25");
			book161.setProperty("title", "오픈소스 OpenCV를 이용한 컴퓨터 비전 실무 프로그래밍");
			book161.setProperty("author", "정성환, 이문호");
			book161.setProperty("publisher", "홍릉과학출판사");
			
			Node book162 = graphDb.createNode();
			book162.setProperty("No", "2009-26");
			book162.setProperty("title", "Pro JavaFX trade Platform Script Desktop and Mobile RIA with Java trade Technology");
			book162.setProperty("author", "James L.Weaver");
			book162.setProperty("publisher", "apress");
			book162.setProperty("ebook", true);
			
			Node book163 = graphDb.createNode();
			book163.setProperty("No", "2009-27");
			book163.setProperty("title", "eginning Databases with PostgreSQL From Novice to Professional Second Edition");
			book163.setProperty("author", "Neil Matthew");
			book163.setProperty("publisher", "apress");
			book163.setProperty("ebook", true);
			
			Node book164 = graphDb.createNode();
			book164.setProperty("No", "2009-28");
			book164.setProperty("title", "Practical API Design Confessions of a Java trade Framework Architect");
			book164.setProperty("author", "Jaroslav Tulach");
			book164.setProperty("publisher", "apress");
			book164.setProperty("ebook", true);
			
			Node book165 = graphDb.createNode();
			book165.setProperty("No", "2009-29");
			book165.setProperty("title", "Effective Java second edition");
			book165.setProperty("author", "Joshua Bloch");
			book165.setProperty("publisher", "Addison Wesley");
			
			Node book166 = graphDb.createNode();
			book166.setProperty("No", "2009-30");
			book166.setProperty("title", "Generalisation of Geographic Information: Cartographic Modelling and Applications ");
			book166.setProperty("author", "William A. Mackaness");
			book166.setProperty("publisher", "ELSEVIER");
			
			Node book167 = graphDb.createNode();
			book167.setProperty("No", "2009-31");
			book167.setProperty("title", "Java Thread Programming");
			book167.setProperty("author", "Paul Hyde");
			book167.setProperty("publisher", "SAMS");
			
			Node book168 = graphDb.createNode();
			book168.setProperty("No", "2009-32");
			book168.setProperty("title", "Patterns for Parallel Programming ");
			book168.setProperty("author", "Timothy G.Mattson");
			book168.setProperty("publisher", "Addison Wesley");
			
			Node book169 = graphDb.createNode();
			book169.setProperty("No", "2009-33");
			book169.setProperty("title", "Hadoop: The Definitive Guide");
			book169.setProperty("author", "Tom White ");
			book169.setProperty("publisher", "O'REILLY");
			
			Node book170 = graphDb.createNode();
			book170.setProperty("No", "2009-34");
			book170.setProperty("title", "Principles of Parallel Programming");
			book170.setProperty("author", "Calvin Lin");
			book170.setProperty("publisher", "Addison Wesley");
			
			Node book171 = graphDb.createNode();
			book171.setProperty("No", "2009-35");
			book171.setProperty("title", "Java Concurrency in Practice");
			book171.setProperty("author", "Brian Goetz");
			book171.setProperty("publisher", "Addison Wesley");
			
			Node book172 = graphDb.createNode();
			book172.setProperty("No", "2009-36");
			book172.setProperty("title", "The Art of Multiprocessor Programming ");
			book172.setProperty("author", "Maurice Herlihy");
			book172.setProperty("publisher", "MORGAN KAUFMANN");
			
			Node book173 = graphDb.createNode();
			book173.setProperty("No", "2009-37");
			book173.setProperty("title", "디지털 영상처리");
			book173.setProperty("author", "Rafael C.Gonzalez");
			book173.setProperty("publisher", "Prentice Hall");
			
			Node book174 = graphDb.createNode();
			book174.setProperty("No", "2009-38");
			book174.setProperty("title", "Programming Amazon Web Services");
			book174.setProperty("author", "James Murty");
			book174.setProperty("publisher", "O'REILLY");
			
			Node book175 = graphDb.createNode();
			book175.setProperty("No", "2009-39");
			book175.setProperty("title", "Introduction to Parallel Computing");
			book175.setProperty("author", "Ananth Grama");
			book175.setProperty("publisher", "Addison Wesley");
			
			Node book176 = graphDb.createNode();
			book176.setProperty("No", "2009-40");
			book176.setProperty("title", "Cloud Application Architectures");
			book176.setProperty("author", "George Reese");
			book176.setProperty("publisher", "O'REILLY");
			
			Node book177 = graphDb.createNode();
			book177.setProperty("No", "2009-41");
			book177.setProperty("title", "Alice & Java");
			book177.setProperty("author", "John Lewis");
			book177.setProperty("publisher", "Addison Wesley");
			
			Node book178 = graphDb.createNode();
			book178.setProperty("No", "2009-42");
			book178.setProperty("title", "JavaFX");
			book178.setProperty("author", "Jim Clark");
			book178.setProperty("publisher", "JPUB");
						
			Node book179 = graphDb.createNode();
			book179.setProperty("No", "2009-43");
			book179.setProperty("title", "OpenCV");
			book179.setProperty("author", "Gary Rost Bradski");
			book179.setProperty("publisher", "O'REILLY");
						
			
			
			book137.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book138.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book139.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book140.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book141.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book142.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book143.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book144.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book145.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book146.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book147.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book148.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book149.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book150.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book151.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book152.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book153.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book154.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book155.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book156.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book157.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book158.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book159.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book160.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book161.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book162.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book163.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book164.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book165.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book166.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book167.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book168.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book169.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book170.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book171.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book172.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book173.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book174.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book175.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book176.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book177.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book178.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book179.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);

			
			
			// 2009 end
			
						
			
			// 2010 start
			
			Node book180 = graphDb.createNode();
			book180.setProperty("No", "2010-01");
			book180.setProperty("title", "Programming Massively Parallel Processors: A Hands-on Approach");
			book180.setProperty("author", "David B. Kirk");
			book180.setProperty("publisher", "Morgan Kaufmann");
			
			Node book181 = graphDb.createNode();
			book181.setProperty("No", "2010-02");
			book181.setProperty("title", "WINDOWS SERVER 2008 바이블");
			book181.setProperty("author", "Charlie Russel");
			book181.setProperty("publisher", "정보문화사");
			
			Node book182 = graphDb.createNode();
			book182.setProperty("No", "2010-03");
			book182.setProperty("title", "Beginning Google Web TooKit From Novice to Professional");
			book182.setProperty("author", "Brom Smeets, Uri Boness, Roald Bankrs");
			book182.setProperty("publisher", "Apress");
			book182.setProperty("ebook", true);
			
			Node book183 = graphDb.createNode();
			book183.setProperty("No", "2010-04");
			book183.setProperty("title", "Accelerated GWT - Buildin Enterprise Google Web ToolKit Applications");
			book183.setProperty("author", "Vipul Gupta");
			book183.setProperty("publisher", "Apress");
			book183.setProperty("ebook", true);
			
			Node book184 = graphDb.createNode();
			book184.setProperty("No", "2010-05");
			book184.setProperty("title", "Beginning Java Google App Engine");
			book184.setProperty("author", "Kyle Roche, Jeff Douglas");
			book184.setProperty("publisher", "Apress");
			book184.setProperty("ebook", true);
			
			Node book185 = graphDb.createNode();
			book185.setProperty("No", "2010-06");
			book185.setProperty("title", "AIRBORN AND TERRESTRIAL LASER SCANNING");
			book185.setProperty("author", "George Vosselman, Hans-Gerd Maas");
			book185.setProperty("publisher", "CRC Press");
			book185.setProperty("etc", "dclee");
			
			Node book186 = graphDb.createNode();
			book186.setProperty("No", "2010-07");
			book186.setProperty("title", "TOPOGRAPHIC LASER RANGING AND SACNNING PRINCIPLES AND PROCESSING");
			book186.setProperty("author", "Jie Shan, Charles K. Toth");
			book186.setProperty("publisher", "CRC Press");
						
			Node book187 = graphDb.createNode();
			book187.setProperty("No", "2010-08");
			book187.setProperty("title", "Laser Scanning for the Environmental Sciences");
			book187.setProperty("author", "George L. Heitage, Andrew R.G.lARGE");
			book187.setProperty("publisher", "WILEY-BLACKWELL");
			
			Node book188 = graphDb.createNode();
			book188.setProperty("No", "2010-09");
			book188.setProperty("title", "Photogrammetry Geometry from images and Laser Scans 2nd edition");
			book188.setProperty("author", "Karl Kraus");
			book188.setProperty("publisher", "Walter de Gruyter");
			
			Node book189 = graphDb.createNode();
			book189.setProperty("No", "2010-10");
			book189.setProperty("title", "GWT in practice");
			book189.setProperty("author", "Robert Cooper");
			book189.setProperty("publisher", "Manning Publications");
			
			Node book190 = graphDb.createNode();
			book190.setProperty("No", "2010-11");
			book190.setProperty("title", "Basic GIS Coordinates, Second Edition");
			book190.setProperty("author", "Jan Van Sickle");
			book190.setProperty("publisher", "CRC Press");
			
			Node book191 = graphDb.createNode();
			book191.setProperty("No", "2010-12");
			book191.setProperty("title", "Image Analysis, Classification, and Change Detection in Remote Sensing: With Algorithms for ENVI/IDL, Second Edition");
			book191.setProperty("author", "Morton J. Canty");
			book191.setProperty("publisher", "CRC Press");
			
			Node book192 = graphDb.createNode();
			book192.setProperty("No", "2010-13");
			book192.setProperty("title", "Geographic Data Mining and Knowledge Discovery, Second Edition");
			book192.setProperty("author", "Harvey J. Miller");
			book192.setProperty("publisher", "CRC Press");
			
			Node book193 = graphDb.createNode();
			book193.setProperty("No", "2010-14");
			book193.setProperty("title", "The 3-D Global Spatial Data Model: Foundation of the Spatial Data Infrastructure");
			book193.setProperty("author", "Earl F. Burkholder");
			book193.setProperty("publisher", "CRC Press");
			
			Node book194 = graphDb.createNode();
			book194.setProperty("No", "2010-15");
			book194.setProperty("title", "SManual of Geospatial Science and Technology, Second Edition");
			book194.setProperty("author", "John D. Bossler");
			book194.setProperty("publisher", "CRC Press");
			
			Node book195 = graphDb.createNode();
			book195.setProperty("No", "2010-16");
			book195.setProperty("title", "Advances in Photogrammetry, Remote Sensing and Spatial Information Sciences: 2008 ISPRS Congress Book");
			book195.setProperty("author", "Zhilin Li");
			book195.setProperty("publisher", "CRC Press");
			
			Node book196 = graphDb.createNode();
			book196.setProperty("No", "2010-17");
			book196.setProperty("title", "Machine Learning for Spatial Environmental Data: Theory, Applications, and Software");
			book196.setProperty("author", "Mikhail Kanevski");
			book196.setProperty("publisher", "CRC Press");
			
			Node book197 = graphDb.createNode();
			book197.setProperty("No", "2010-18");
			book197.setProperty("title", "Principles of Modeling Uncertainties in Spatial Data and Spatial Analyses ");
			book197.setProperty("author", "Wenzhong Shi");
			book197.setProperty("publisher", "CRC Press");
			
			Node book198 = graphDb.createNode();
			book198.setProperty("No", "2010-19");
			book198.setProperty("title", "CAD and GIS Integration");
			book198.setProperty("author", "HAS_Asan A. Karimi");
			book198.setProperty("publisher", "CRC Press");
			
			Node book199 = graphDb.createNode();
			book199.setProperty("No", "2010-20");
			book199.setProperty("title", "Theories of Geographic Concepts: Ontological Approaches to Semantic Integration");
			book199.setProperty("author", "Marinos Kavouras");
			book199.setProperty("publisher", "CRC Press");
			
			Node book200 = graphDb.createNode();
			book200.setProperty("No", "2010-21");
			book200.setProperty("title", "Fuzzy Surfaces in GIS and Geographical Analysis: Theory, Analytical Methods, Algorithms and Applications ");
			book200.setProperty("author", "Weldon Lodwick");
			book200.setProperty("publisher", "CRC Press");
			
			Node book201 = graphDb.createNode();
			book201.setProperty("No", "2010-22");
			book201.setProperty("title", "Fundamentals of Satellite Remote Sensing ");
			book201.setProperty("author", "Emilio Chuvieco");
			book201.setProperty("publisher", "CRC Press");
			
			Node book202 = graphDb.createNode();
			book202.setProperty("No", "2010-23");
			book202.setProperty("title", "GPU Gems 3");
			book202.setProperty("author", "Hubert Nguyen");
			book202.setProperty("publisher", "Addison Wesley");
			
			Node book203 = graphDb.createNode();
			book203.setProperty("No", "2010-24");
			book203.setProperty("title", "GPU Gems Programming Techniques, Tips, and Tricks for Real-Time Grphics");
			book203.setProperty("author", "Randima Frenando");
			book203.setProperty("publisher", "Addison Wesley");
			
			Node book204 = graphDb.createNode();
			book204.setProperty("No", "2010-25");
			book204.setProperty("title", "GPU Gems 2 Programming Techniques for High-Performance Graphics and General-Purpose Computtation");
			book204.setProperty("author", "Matt Pharr");
			book204.setProperty("publisher", "Addison Wesley");
			
			Node book205 = graphDb.createNode();
			book205.setProperty("No", "2010-26");
			book205.setProperty("title", "GPU Gems Programming Techniques for Real-Time Graphics");
			book205.setProperty("author", "");
			book205.setProperty("publisher", "Addison Wesley");
			
			Node book206 = graphDb.createNode();
			book206.setProperty("No", "2010-27");
			book206.setProperty("title", "Developing with Ext GWT Enterprise RIA Development");
			book206.setProperty("author", "Grant Slender");
			book206.setProperty("publisher", "Apress");
			book206.setProperty("ebook", true);
			
			Node book207 = graphDb.createNode();
			book207.setProperty("No", "2010-28");
			book207.setProperty("title", "CLOUD COMPUTING EXPLAINED");
			book207.setProperty("author", "J2OHN EHOTON");
			book207.setProperty("publisher", "RP");
			
			Node book208 = graphDb.createNode();
			book208.setProperty("No", "2010-29");
			book208.setProperty("title", "CLOUD COMPUTING A Practical Approach");
			book208.setProperty("author", "Anthony T.Velte");
			book208.setProperty("publisher", "McGraw Hill");
			
			Node book209 = graphDb.createNode();
			book209.setProperty("No", "2010-30");
			book209.setProperty("title", "온톨로지 개발자를 위한 시맨틱웹");
			book209.setProperty("author", "DEAN ALLEMANG");
			book209.setProperty("publisher", "사이텍미디어");
			
			Node book210 = graphDb.createNode();
			book210.setProperty("No", "2010-31");
			book210.setProperty("title", "인터넷 진화의 열쇠 온톨로지");
			book210.setProperty("author", "노상규, 박진수");
			book210.setProperty("publisher", "가즈토이");
			
			Node book211 = graphDb.createNode();
			book211.setProperty("No", "2010-32");
			book211.setProperty("title", "시맨틱 웹을 위한 RDF/OWL 입문 ");
			book211.setProperty("author", "신기정영");
			book211.setProperty("publisher", "홍릉과학출판사");
			
			

			book180.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book181.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book182.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book183.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book184.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book185.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book186.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book187.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book188.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book189.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book190.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book191.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book192.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book193.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book194.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book195.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book196.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book197.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book198.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book199.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book200.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book201.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book202.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book203.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book204.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book205.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book206.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book207.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book208.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book209.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book210.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book211.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			
			
			
			// 2010 end
			
			
			
			// 2011 start
			
			Node book212 = graphDb.createNode();
			book212.setProperty("No", "2011-01");
			book212.setProperty("title", "Openlayers 2.10 Beginner's Guide");
			book212.setProperty("author", "Hazzard, Erik");
			book212.setProperty("publisher", "PACKT Publishing");
			
			Node book213 = graphDb.createNode();
			book213.setProperty("No", "2011-02");
			book213.setProperty("title", "PostGIS in Action");
			book213.setProperty("author", "Obe, Regina");
			book213.setProperty("publisher", "Manning Publications");
			
			Node book214 = graphDb.createNode();
			book214.setProperty("No", "2011-03");
			book214.setProperty("title", "Python Geospatial Development");
			book214.setProperty("author", "Erik Westra");
			book214.setProperty("publisher", "PACKT Publishing");
			
			Node book215 = graphDb.createNode();
			book215.setProperty("No", "2011-04");
			book215.setProperty("title", "안드로이드 프로그래밍의 이해");
			book215.setProperty("author", "우종정");
			book215.setProperty("publisher", "생능");
			
			Node book216 = graphDb.createNode();
			book216.setProperty("No", "2011-05");
			book216.setProperty("title", "R in Action");
			book216.setProperty("author", "Robert I. Kabacoff");
			book216.setProperty("publisher", "MANNING");
			
			Node book217 = graphDb.createNode();
			book217.setProperty("No", "2011-06");
			book217.setProperty("title", "Statistics for SPATIO-TEMPERAL DATA");
			book217.setProperty("author", "Noel Cressie, Christopher K. Wikle");
			book217.setProperty("publisher", "WILEY");
			
			Node book218 = graphDb.createNode();
			book218.setProperty("No", "2011-07");
			book218.setProperty("title", "Decision Support Systems for Business Intelligence");
			book218.setProperty("author", "Vicki L. Sauter");
			book218.setProperty("publisher", "WILEY");
			
			Node book219 = graphDb.createNode();
			book219.setProperty("No", "2011-08");
			book219.setProperty("title", "Information Visualization: Beyond the Horizon [Kindle Edition]");
			book219.setProperty("author", "Chaomei Chen");
			book219.setProperty("publisher", "Springer");
			
			Node book220 = graphDb.createNode();
			book220.setProperty("No", "2011-09");
			book220.setProperty("title", "Exploratory Social Network Analysis with Pajek (Structural Analysis in the Social Sciences) [Kindle Edition]");
			book220.setProperty("author", "Wouter de Nooy");
			book220.setProperty("publisher", "Cambridge University Press");
			
			Node book221 = graphDb.createNode();
			book221.setProperty("No", "2011-10");
			book221.setProperty("title", "Envisioning Information");
			book221.setProperty("author", "Edward R. Tufte");
			book221.setProperty("publisher", "Graphics Pr");
			
			Node book222 = graphDb.createNode();
			book222.setProperty("No", "2011-11");
			book222.setProperty("title", "Now You See It: Simple Visualization Techniques for Quantitative Analysis");
			book222.setProperty("author", "Stephen Few");
			book222.setProperty("publisher", "Analytics Press");
			
			Node book223 = graphDb.createNode();
			book223.setProperty("No", "2011-12");
			book223.setProperty("title", "Social Network Analysis: Methods and Applications (Structural Analysis in the Social Sciences) [Kindle Edition]");
			book223.setProperty("author", "Katherine Faust and Stanley Wasserman");
			book223.setProperty("publisher", "Cambridge University Press");
			
			Node book224 = graphDb.createNode();
			book224.setProperty("No", "2011-13");
			book224.setProperty("title", "Modeling Infectious Diseases in Humans and Animals [Kindle Edition]");
			book224.setProperty("author", "Matt J. Keeling , Pejman Rohani");
			book224.setProperty("publisher", "Princeton University Press");
			
			Node book225 = graphDb.createNode();
			book225.setProperty("No", "2011-14");
			book225.setProperty("title", "Statistical Analysis of Network Data: Methods and Models (Springer Series in Statistics) [Kindle Edition]");
			book225.setProperty("author", "Eric D. Kolaczyk");
			book225.setProperty("publisher", "Springer");
			
			Node book226 = graphDb.createNode();
			book226.setProperty("No", "2011-15");
			book226.setProperty("title", "Simulation Modelling and Analysis [Paperback]");
			book226.setProperty("author", "W David Kelton");
			book226.setProperty("publisher", "Mcgraw Hill Higher Education");
			
			Node book227 = graphDb.createNode();
			book227.setProperty("No", "2011-16");
			book227.setProperty("title", "Parallel Programming with MPI [Kindle Edition]");
			book227.setProperty("author", "Peter Pacheco");
			book227.setProperty("publisher", "Morgan Kaufmann");
			
			Node book228 = graphDb.createNode();
			book228.setProperty("No", "2011-17");
			book228.setProperty("title", "High Performance Linux Clusters with OSCAR, Rocks, OpenMosix, and MPI (Nutshell Handbooks) [Kindle Edition]");
			book228.setProperty("author", "Joseph Sloan");
			book228.setProperty("publisher", "O'Reilly Media");
			
			Node book229 = graphDb.createNode();
			book229.setProperty("No", "2011-18");
			book229.setProperty("title", "Analyzing Social Media Networks with NodeXL:Insights from a Connected");
			book229.setProperty("author", "Derek Hansen,et al");
			book229.setProperty("publisher", "Morgan Kaufmann");
			
			Node book230 = graphDb.createNode();
			book230.setProperty("No", "2011-19");
			book230.setProperty("title", "빅 데이터 시대를 위한 NoSQL 핵심 가이드");
			book230.setProperty("author", "Tatsuya Sasaki");
			book230.setProperty("publisher", "로드북");
			
			Node book231 = graphDb.createNode();
			book231.setProperty("No", "2011-20");
			book231.setProperty("title", "Mining the Social Web");
			book231.setProperty("author", "Matthew A. Russell");
			book231.setProperty("publisher", "O'Reilly Media");
			
			Node book232 = graphDb.createNode();
			book232.setProperty("No", "2011-21");
			book232.setProperty("title", "Ontology-Driven Translation of Geospatial Data");
			book232.setProperty("author", "S. Schade");
			book232.setProperty("publisher", "IOS Press");
			
			Node book233 = graphDb.createNode();
			book233.setProperty("No", "2011-22");
			book233.setProperty("title", "Practical RDF");
			book233.setProperty("author", "Shelley Powers");
			book233.setProperty("publisher", "O'Reilly Media");
			
			Node book234 = graphDb.createNode();
			book234.setProperty("No", "2011-23");
			book234.setProperty("title", "Learning SPARQL");
			book234.setProperty("author", "Bob DuCharme");
			book234.setProperty("publisher", "O'Reilly Media");
			
			Node book235 = graphDb.createNode();
			book235.setProperty("No", "2011-24");
			book235.setProperty("title", "Building Clustered Linux Systems");
			book235.setProperty("author", "Robert W. Lucke");
			book235.setProperty("publisher", "Prentice Hall");

			
			

			book212.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book213.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book214.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book215.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book216.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book217.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book218.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book219.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book220.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book221.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book222.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book223.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book224.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book225.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book226.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book227.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book228.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book229.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book230.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book231.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book232.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book233.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book234.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			book235.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			
			
			
			// 2011 end
			
			
			// 2012 start
			
			Node book236 = graphDb.createNode();
			book236.setProperty("No", "2012-01");
			book236.setProperty("title", "Agent-Based and Individual-Based Modeling: A Practical Introduction");
			book236.setProperty("author", "Steven F. Railsback, Volker Grimm");
			book236.setProperty("publisher", "Princeton University Press");

			
			book236.createRelationshipTo(location1, RelTypes.IS_LOCATED_IN);
			
			// 2012 end
			
			

			
			
			// has_a
			
			lab.createRelationshipTo(book1, RelTypes.HAS_A);
			lab.createRelationshipTo(book2, RelTypes.HAS_A);
			lab.createRelationshipTo(book3, RelTypes.HAS_A);
			lab.createRelationshipTo(book4, RelTypes.HAS_A);
			lab.createRelationshipTo(book5, RelTypes.HAS_A);
			lab.createRelationshipTo(book6, RelTypes.HAS_A);
			lab.createRelationshipTo(book7, RelTypes.HAS_A);
			lab.createRelationshipTo(book8, RelTypes.HAS_A);
			lab.createRelationshipTo(book9, RelTypes.HAS_A);
			lab.createRelationshipTo(book10, RelTypes.HAS_A);
			lab.createRelationshipTo(book11, RelTypes.HAS_A);
			lab.createRelationshipTo(book12, RelTypes.HAS_A);
			lab.createRelationshipTo(book13, RelTypes.HAS_A);
			lab.createRelationshipTo(book14, RelTypes.HAS_A);
			lab.createRelationshipTo(book15, RelTypes.HAS_A);
			lab.createRelationshipTo(book16, RelTypes.HAS_A);
			lab.createRelationshipTo(book17, RelTypes.HAS_A);
			lab.createRelationshipTo(book18, RelTypes.HAS_A);
			lab.createRelationshipTo(book19, RelTypes.HAS_A);
			lab.createRelationshipTo(book20, RelTypes.HAS_A);
			lab.createRelationshipTo(book21, RelTypes.HAS_A);
			lab.createRelationshipTo(book22, RelTypes.HAS_A);
			lab.createRelationshipTo(book23, RelTypes.HAS_A);
			lab.createRelationshipTo(book24, RelTypes.HAS_A);
			lab.createRelationshipTo(book25, RelTypes.HAS_A);
			lab.createRelationshipTo(book26, RelTypes.HAS_A);
			lab.createRelationshipTo(book27, RelTypes.HAS_A);
			lab.createRelationshipTo(book28, RelTypes.HAS_A);
			lab.createRelationshipTo(book29, RelTypes.HAS_A);
			lab.createRelationshipTo(book30, RelTypes.HAS_A);
			lab.createRelationshipTo(book31, RelTypes.HAS_A);
			lab.createRelationshipTo(book32, RelTypes.HAS_A);
			lab.createRelationshipTo(book33, RelTypes.HAS_A);
			lab.createRelationshipTo(book34, RelTypes.HAS_A);
			lab.createRelationshipTo(book35, RelTypes.HAS_A);
			lab.createRelationshipTo(book36, RelTypes.HAS_A);
			lab.createRelationshipTo(book37, RelTypes.HAS_A);
			lab.createRelationshipTo(book38, RelTypes.HAS_A);
			lab.createRelationshipTo(book39, RelTypes.HAS_A);
			lab.createRelationshipTo(book40, RelTypes.HAS_A);
			lab.createRelationshipTo(book41, RelTypes.HAS_A);
			lab.createRelationshipTo(book42, RelTypes.HAS_A);
			lab.createRelationshipTo(book43, RelTypes.HAS_A);
			lab.createRelationshipTo(book44, RelTypes.HAS_A);
			lab.createRelationshipTo(book45, RelTypes.HAS_A);
			lab.createRelationshipTo(book46, RelTypes.HAS_A);
			lab.createRelationshipTo(book47, RelTypes.HAS_A);
			lab.createRelationshipTo(book48, RelTypes.HAS_A);
			lab.createRelationshipTo(book49, RelTypes.HAS_A);
			lab.createRelationshipTo(book50, RelTypes.HAS_A);
			lab.createRelationshipTo(book51, RelTypes.HAS_A);
			lab.createRelationshipTo(book52, RelTypes.HAS_A);
			lab.createRelationshipTo(book53, RelTypes.HAS_A);
			lab.createRelationshipTo(book54, RelTypes.HAS_A);
			lab.createRelationshipTo(book55, RelTypes.HAS_A);
			lab.createRelationshipTo(book56, RelTypes.HAS_A);
			lab.createRelationshipTo(book57, RelTypes.HAS_A);
			lab.createRelationshipTo(book58, RelTypes.HAS_A);
			lab.createRelationshipTo(book59, RelTypes.HAS_A);
			lab.createRelationshipTo(book60, RelTypes.HAS_A);
			lab.createRelationshipTo(book61, RelTypes.HAS_A);
			lab.createRelationshipTo(book62, RelTypes.HAS_A);
			lab.createRelationshipTo(book63, RelTypes.HAS_A);
			lab.createRelationshipTo(book64, RelTypes.HAS_A);
			lab.createRelationshipTo(book65, RelTypes.HAS_A);
			lab.createRelationshipTo(book66, RelTypes.HAS_A);
			lab.createRelationshipTo(book67, RelTypes.HAS_A);
			lab.createRelationshipTo(book68, RelTypes.HAS_A);
			lab.createRelationshipTo(book69, RelTypes.HAS_A);
			lab.createRelationshipTo(book70, RelTypes.HAS_A);
			lab.createRelationshipTo(book71, RelTypes.HAS_A);
			lab.createRelationshipTo(book72, RelTypes.HAS_A);
			lab.createRelationshipTo(book73, RelTypes.HAS_A);
			lab.createRelationshipTo(book74, RelTypes.HAS_A);
			lab.createRelationshipTo(book75, RelTypes.HAS_A);
			lab.createRelationshipTo(book76, RelTypes.HAS_A);
			lab.createRelationshipTo(book77, RelTypes.HAS_A);
			lab.createRelationshipTo(book78, RelTypes.HAS_A);
			lab.createRelationshipTo(book79, RelTypes.HAS_A);
			lab.createRelationshipTo(book80, RelTypes.HAS_A);
			lab.createRelationshipTo(book81, RelTypes.HAS_A);
			lab.createRelationshipTo(book82, RelTypes.HAS_A);
			lab.createRelationshipTo(book83, RelTypes.HAS_A);
			lab.createRelationshipTo(book84, RelTypes.HAS_A);
			lab.createRelationshipTo(book85, RelTypes.HAS_A);
			lab.createRelationshipTo(book86, RelTypes.HAS_A);
			lab.createRelationshipTo(book87, RelTypes.HAS_A);
			lab.createRelationshipTo(book88, RelTypes.HAS_A);
			lab.createRelationshipTo(book89, RelTypes.HAS_A);
			lab.createRelationshipTo(book90, RelTypes.HAS_A);
			lab.createRelationshipTo(book91, RelTypes.HAS_A);
			lab.createRelationshipTo(book92, RelTypes.HAS_A);
			lab.createRelationshipTo(book93, RelTypes.HAS_A);
			lab.createRelationshipTo(book94, RelTypes.HAS_A);
			lab.createRelationshipTo(book95, RelTypes.HAS_A);
			lab.createRelationshipTo(book96, RelTypes.HAS_A);
			lab.createRelationshipTo(book97, RelTypes.HAS_A);
			lab.createRelationshipTo(book98, RelTypes.HAS_A);
			lab.createRelationshipTo(book99, RelTypes.HAS_A);
			lab.createRelationshipTo(book100, RelTypes.HAS_A);
			lab.createRelationshipTo(book101, RelTypes.HAS_A);
			lab.createRelationshipTo(book102, RelTypes.HAS_A);
			lab.createRelationshipTo(book103, RelTypes.HAS_A);
			lab.createRelationshipTo(book104, RelTypes.HAS_A);
			lab.createRelationshipTo(book105, RelTypes.HAS_A);
			lab.createRelationshipTo(book106, RelTypes.HAS_A);
			lab.createRelationshipTo(book107, RelTypes.HAS_A);
			lab.createRelationshipTo(book108, RelTypes.HAS_A);
			lab.createRelationshipTo(book109, RelTypes.HAS_A);
			lab.createRelationshipTo(book110, RelTypes.HAS_A);
			lab.createRelationshipTo(book111, RelTypes.HAS_A);
			lab.createRelationshipTo(book112, RelTypes.HAS_A);
			lab.createRelationshipTo(book113, RelTypes.HAS_A);
			lab.createRelationshipTo(book114, RelTypes.HAS_A);
			lab.createRelationshipTo(book115, RelTypes.HAS_A);
			lab.createRelationshipTo(book116, RelTypes.HAS_A);
			lab.createRelationshipTo(book117, RelTypes.HAS_A);
			lab.createRelationshipTo(book118, RelTypes.HAS_A);
			lab.createRelationshipTo(book119, RelTypes.HAS_A);
			lab.createRelationshipTo(book120, RelTypes.HAS_A);
			lab.createRelationshipTo(book121, RelTypes.HAS_A);
			lab.createRelationshipTo(book122, RelTypes.HAS_A);
			lab.createRelationshipTo(book123, RelTypes.HAS_A);
			lab.createRelationshipTo(book124, RelTypes.HAS_A);
			lab.createRelationshipTo(book125, RelTypes.HAS_A);
			lab.createRelationshipTo(book126, RelTypes.HAS_A);
			lab.createRelationshipTo(book127, RelTypes.HAS_A);
			lab.createRelationshipTo(book128, RelTypes.HAS_A);
			lab.createRelationshipTo(book129, RelTypes.HAS_A);
			lab.createRelationshipTo(book130, RelTypes.HAS_A);
			lab.createRelationshipTo(book131, RelTypes.HAS_A);
			lab.createRelationshipTo(book132, RelTypes.HAS_A);
			lab.createRelationshipTo(book133, RelTypes.HAS_A);
			lab.createRelationshipTo(book134, RelTypes.HAS_A);
			lab.createRelationshipTo(book135, RelTypes.HAS_A);
			lab.createRelationshipTo(book136, RelTypes.HAS_A);
			lab.createRelationshipTo(book137, RelTypes.HAS_A);
			lab.createRelationshipTo(book138, RelTypes.HAS_A);
			lab.createRelationshipTo(book139, RelTypes.HAS_A);
			lab.createRelationshipTo(book140, RelTypes.HAS_A);
			lab.createRelationshipTo(book141, RelTypes.HAS_A);
			lab.createRelationshipTo(book142, RelTypes.HAS_A);
			lab.createRelationshipTo(book143, RelTypes.HAS_A);
			lab.createRelationshipTo(book144, RelTypes.HAS_A);
			lab.createRelationshipTo(book145, RelTypes.HAS_A);
			lab.createRelationshipTo(book146, RelTypes.HAS_A);
			lab.createRelationshipTo(book147, RelTypes.HAS_A);
			lab.createRelationshipTo(book148, RelTypes.HAS_A);
			lab.createRelationshipTo(book149, RelTypes.HAS_A);
			lab.createRelationshipTo(book150, RelTypes.HAS_A);
			lab.createRelationshipTo(book151, RelTypes.HAS_A);
			lab.createRelationshipTo(book152, RelTypes.HAS_A);
			lab.createRelationshipTo(book153, RelTypes.HAS_A);
			lab.createRelationshipTo(book154, RelTypes.HAS_A);
			lab.createRelationshipTo(book155, RelTypes.HAS_A);
			lab.createRelationshipTo(book156, RelTypes.HAS_A);
			lab.createRelationshipTo(book157, RelTypes.HAS_A);
			lab.createRelationshipTo(book158, RelTypes.HAS_A);
			lab.createRelationshipTo(book159, RelTypes.HAS_A);
			lab.createRelationshipTo(book160, RelTypes.HAS_A);
			lab.createRelationshipTo(book161, RelTypes.HAS_A);
			lab.createRelationshipTo(book162, RelTypes.HAS_A);
			lab.createRelationshipTo(book163, RelTypes.HAS_A);
			lab.createRelationshipTo(book164, RelTypes.HAS_A);
			lab.createRelationshipTo(book165, RelTypes.HAS_A);
			lab.createRelationshipTo(book166, RelTypes.HAS_A);
			lab.createRelationshipTo(book167, RelTypes.HAS_A);
			lab.createRelationshipTo(book168, RelTypes.HAS_A);
			lab.createRelationshipTo(book169, RelTypes.HAS_A);
			lab.createRelationshipTo(book170, RelTypes.HAS_A);
			lab.createRelationshipTo(book171, RelTypes.HAS_A);
			lab.createRelationshipTo(book172, RelTypes.HAS_A);
			lab.createRelationshipTo(book173, RelTypes.HAS_A);
			lab.createRelationshipTo(book174, RelTypes.HAS_A);
			lab.createRelationshipTo(book175, RelTypes.HAS_A);
			lab.createRelationshipTo(book176, RelTypes.HAS_A);
			lab.createRelationshipTo(book177, RelTypes.HAS_A);
			lab.createRelationshipTo(book178, RelTypes.HAS_A);
			lab.createRelationshipTo(book179, RelTypes.HAS_A);
			lab.createRelationshipTo(book180, RelTypes.HAS_A);
			lab.createRelationshipTo(book181, RelTypes.HAS_A);
			lab.createRelationshipTo(book182, RelTypes.HAS_A);
			lab.createRelationshipTo(book183, RelTypes.HAS_A);
			lab.createRelationshipTo(book184, RelTypes.HAS_A);
			lab.createRelationshipTo(book185, RelTypes.HAS_A);
			lab.createRelationshipTo(book186, RelTypes.HAS_A);
			lab.createRelationshipTo(book187, RelTypes.HAS_A);
			lab.createRelationshipTo(book188, RelTypes.HAS_A);
			lab.createRelationshipTo(book189, RelTypes.HAS_A);
			lab.createRelationshipTo(book190, RelTypes.HAS_A);
			lab.createRelationshipTo(book191, RelTypes.HAS_A);
			lab.createRelationshipTo(book192, RelTypes.HAS_A);
			lab.createRelationshipTo(book193, RelTypes.HAS_A);
			lab.createRelationshipTo(book194, RelTypes.HAS_A);
			lab.createRelationshipTo(book195, RelTypes.HAS_A);
			lab.createRelationshipTo(book196, RelTypes.HAS_A);
			lab.createRelationshipTo(book197, RelTypes.HAS_A);
			lab.createRelationshipTo(book198, RelTypes.HAS_A);
			lab.createRelationshipTo(book199, RelTypes.HAS_A);
			lab.createRelationshipTo(book200, RelTypes.HAS_A);
			lab.createRelationshipTo(book201, RelTypes.HAS_A);
			lab.createRelationshipTo(book202, RelTypes.HAS_A);
			lab.createRelationshipTo(book203, RelTypes.HAS_A);
			lab.createRelationshipTo(book204, RelTypes.HAS_A);
			lab.createRelationshipTo(book205, RelTypes.HAS_A);
			lab.createRelationshipTo(book206, RelTypes.HAS_A);
			lab.createRelationshipTo(book207, RelTypes.HAS_A);
			lab.createRelationshipTo(book208, RelTypes.HAS_A);
			lab.createRelationshipTo(book209, RelTypes.HAS_A);
			lab.createRelationshipTo(book210, RelTypes.HAS_A);
			lab.createRelationshipTo(book211, RelTypes.HAS_A);
			lab.createRelationshipTo(book212, RelTypes.HAS_A);
			lab.createRelationshipTo(book213, RelTypes.HAS_A);
			lab.createRelationshipTo(book214, RelTypes.HAS_A);
			lab.createRelationshipTo(book215, RelTypes.HAS_A);
			lab.createRelationshipTo(book216, RelTypes.HAS_A);
			lab.createRelationshipTo(book217, RelTypes.HAS_A);
			lab.createRelationshipTo(book218, RelTypes.HAS_A);
			lab.createRelationshipTo(book219, RelTypes.HAS_A);
			lab.createRelationshipTo(book220, RelTypes.HAS_A);
			lab.createRelationshipTo(book221, RelTypes.HAS_A);
			lab.createRelationshipTo(book222, RelTypes.HAS_A);
			lab.createRelationshipTo(book223, RelTypes.HAS_A);
			lab.createRelationshipTo(book224, RelTypes.HAS_A);
			lab.createRelationshipTo(book225, RelTypes.HAS_A);
			lab.createRelationshipTo(book226, RelTypes.HAS_A);
			lab.createRelationshipTo(book227, RelTypes.HAS_A);
			lab.createRelationshipTo(book228, RelTypes.HAS_A);
			lab.createRelationshipTo(book229, RelTypes.HAS_A);
			lab.createRelationshipTo(book230, RelTypes.HAS_A);
			lab.createRelationshipTo(book231, RelTypes.HAS_A);
			lab.createRelationshipTo(book232, RelTypes.HAS_A);
			lab.createRelationshipTo(book233, RelTypes.HAS_A);
			lab.createRelationshipTo(book234, RelTypes.HAS_A);
			lab.createRelationshipTo(book235, RelTypes.HAS_A);
			lab.createRelationshipTo(book236, RelTypes.HAS_A);
		
			

			
			
			
			
			
//			ArrayList<String> arr = new ArrayList<String>();
//			for (int i = 1; i < 237; i++){
//				String bookid = "book"+i;
//				arr.add(bookid);
//			}
//			lab.createRelationshipTo(arr.get(1), RelTypes.HAS_A);
			
			
			
//			ArrayList<String> arry = new ArrayList<String>();
//			for ( int i = 1 ; i < 31 ; i++){
//				arry.add("book"+i);
//			}
//			
//			for ( int i = 1 ; i < 31 ; i++){
//				lab.createRelationshipTo(arry.get(i), RelTypes.HAS_A);
//			}
			
			
			
			// borrow
			
			
			Relationship relationship1 = null;
			relationship1 = person1.createRelationshipTo(book232,RelTypes.BORROW);
			relationship1.setProperty("date", "");
			
			Relationship relationship2 = null;
			relationship2 = person3.createRelationshipTo(book113,RelTypes.BORROW);
			relationship2.setProperty("date", "");
			
			Relationship relationship3 = null;
			relationship3 = person1.createRelationshipTo(book211,RelTypes.BORROW);
			relationship3.setProperty("date", "");

			Relationship relationship4 = null;
			relationship4 = person3.createRelationshipTo(book93,RelTypes.BORROW);
			relationship4.setProperty("date", "");
			
			Relationship relationship5 = null;
			relationship5 = person3.createRelationshipTo(book213,RelTypes.BORROW);
			relationship5.setProperty("date", "");
			
			Relationship relationship6 = null;
			relationship6 = person7.createRelationshipTo(book142,RelTypes.BORROW);
			relationship6.setProperty("date", "");
			
			Relationship relationship7 = null;
			relationship7 = person7.createRelationshipTo(book143,RelTypes.BORROW);
			relationship7.setProperty("date", "");
			
			Relationship relationship8 = null;
			relationship8 = person7.createRelationshipTo(book144,RelTypes.BORROW);
			relationship8.setProperty("date", "");
			
			
			
			
			
			// END SNIPPET: addData

			
			
			
			
			// START SNIPPET: readData

			System.out.println(person7.getProperty("name"));

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
			person7.getSingleRelationship(RelTypes.BORROW, Direction.OUTGOING)
					.delete();		// rel 지우고
			person7.delete();		// 노드지우고
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
}
