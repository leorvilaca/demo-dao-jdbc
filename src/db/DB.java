package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;

public class DB {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
			Properties props = LoadProperties();
			String url = props.getProperty("dburl");
			conn = DriverManager.getConnection(url, props);
			}
			catch(SQLException e) {
				e.getMessage();
			}	
		}
		return conn;
	}
	
	public static void closeClonection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				new DbException(e.getMessage());
			}
		}
	}

	
	private static Properties LoadProperties() {
		try(FileInputStream fs = new FileInputStream("db-properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		}
		catch (IOException e) {
			throw new DbException(e.getMessage());
		}
		
	}
	
	public static void closeStatement(PreparedStatement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				new DbException(e.getMessage());
			}			
		}
	}
	
	public static void closeResultSet(ResultSet rt) {
		if(rt != null) {
			try {
				rt.close();
			} catch (SQLException e) {
				new DbException(e.getMessage());
			}			
		}
	}
}
