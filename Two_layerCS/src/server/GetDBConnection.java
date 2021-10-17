package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//连接数据库
public class GetDBConnection {
	public static Connection connectDB(String DBName, String id, String p) {
		Connection con = null;
		String uri = "jdbc:mysql://localhost:33066/" + DBName
				+ "?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&useSSL=false";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (Exception e) {
			System.out.println("数据库连接失败");
		}
		try {
			con = DriverManager.getConnection(uri, id, p);
		} catch (SQLException e) {
			System.out.println("数据库连接失败");
		}
		return con;
	}
}