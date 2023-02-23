package lm.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;

public class DBConn {
	// Connection String
	  final static String DB_URL="jdbc:oracle:thin:@lmdatabase_high?TNS_ADMIN=D:/wallet/Wallet_LMDATABASE";
	  final static String DB_USER = "admin";
	  final static String DB_PASSWORD = "Lmdata123456";
      private  static      OracleConnection  conn = null;

	                     
	// 생성자 - private
	private  DBConn() {		
	}
	
	public  static OracleConnection getInstance() {
		if(conn != null) {
			return conn;
		}
		
		Properties info = new Properties();     
	    info.put(OracleConnection.CONNECTION_PROPERTY_USER_NAME, DB_USER);
	    info.put(OracleConnection.CONNECTION_PROPERTY_PASSWORD, DB_PASSWORD);          
	    info.put(OracleConnection.CONNECTION_PROPERTY_DEFAULT_ROW_PREFETCH, "20");    
	  
	    OracleDataSource ods = null;
		try {
			ods = new OracleDataSource();
			ods.setURL(DB_URL);    
			ods.setConnectionProperties(info);
			conn = (OracleConnection) ods.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
		
	    
	}
	
}








