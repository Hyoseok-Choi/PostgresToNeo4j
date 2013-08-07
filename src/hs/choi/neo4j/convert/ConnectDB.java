package hs.choi.neo4j.convert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectDB {
	
	private static Connection conn; 

	public static void main(String[] args) throws SQLException {

		String url = "jdbc:postgresql://localhost/geoweb"; // jdbc:postgresql//[host]:[port]/[db_name]
		String user = "postgres";
		String pwd = "postgres";
//		String sql = "select * from seoul limit 5;";
		String sql = "create table my_test3 as select * from seoul limit;";

		String driver = "org.postgresql.Driver";

		try {
			// JDBC �쒕씪�대쾭 �대옒��濡쒕뵫
			Class.forName(driver);
			System.out.println("DB �곌껐 �깃났");

			// DB���곌껐���꾪븳 Connection 媛앹껜 �앹꽦
			// getConnection()濡�Connection 媛앹껜瑜��살뼱��
			conn = DriverManager.getConnection(url, user, pwd);
			
			// �ㅼ젣 SQL臾몄쓣 �ㅽ뻾�섍린 �꾪븳 Statement 媛앹껜 �앹꽦
			// Connection 媛앹껜濡��묎렐�섍린 �꾪빐 createStatement() 硫붿냼�쒕� �몄텧
			Statement stmt = conn.createStatement();
			
			// SQL �ㅽ뻾1 (INSERT, UPDATE, DELETE臾멸낵 �ъ슜)
			stmt.executeUpdate(sql);
			System.out.println("荑쇰━ �ㅽ뻾 �깃났");
			
			// SQL �ㅽ뻾2 (SELECT臾멸낵 �④퍡 �ъ슜, 寃곌낵瑜�諛섑솚 諛쏆쓬)
//			ResultSet rs = stmt.executeQuery(sql);
//			System.out.println("荑쇰━ �ㅽ뻾 �깃났");
//
//			// 紐⑤뱺�됱쓣 諛섎났�섎㈃��
//			while (rs.next()) {
//				// 而щ읆 �대쫫�쇰줈 諛쏄린
//				int osm_id = rs.getInt("osm_id");
//				String osm_name = rs.getString("osm_name");
//				double lat = rs.getDouble("y1");
//				double lon = rs.getDouble("x1");
//				
//				System.out.println(osm_id + " / " + osm_name + " / " + lat + " / " + lon);
//			}
//			
//			// �リ린
//			rs.close();
			stmt.close();
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("DB �곌껐 �ㅽ뙣");
		}

	}

}
