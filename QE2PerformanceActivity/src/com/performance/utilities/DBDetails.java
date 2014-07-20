package com.performance.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDetails {
	
	 //get database connections
	public Connection getDBConnection() throws ClassNotFoundException,
	SQLException {
		// Load the mySql Driver class
		Class.forName("com.mysql.jdbc.Driver");		
		// build the connection string, and get a connection
		String db_connect_string = "jdbc:mysql://localhost/";	
		String dbName = "qe2_bridge";
		String userName = "root"; 
		String password = "root";
		Connection con = DriverManager.getConnection(db_connect_string+dbName,userName,password);	
		return con;
    }
    	
	/**
	 * getQueryString
	 * @param sqlQueryType
	 * @return
	 */
	public String getSqlQuery(String sqlQueryType){		
		String sqlQuery ="";		
		if(sqlQueryType.equalsIgnoreCase("BRIDGE-HOURLY-ACTIVITY")){
			sqlQuery = "SELECT uid, gate_number, vehicle_type, direction, vrn, crossing_datetime FROM qe2_bridge.bridge_crossing_performance";     			  			
		}
		System.out.println(sqlQuery);
		return sqlQuery;		
	}
}
