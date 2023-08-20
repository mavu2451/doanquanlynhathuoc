package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class KetNoiDatabase {
	public static Connection getConnection() {
		Connection conn = null;
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String link = "localhost:1433";
		String db = "QuanLyHieuThuoc";
		String user = "sa";
		String password = "password";
		String url = "jdbc:sqlserver://"+link+";databaseName="+db+";user="+user+";password="+password+";trustServerCertificate=true";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url);
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return conn;
		
	}
}
