package org.sunriseframework.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MySQLBase implements MySQLWrapper {
	
	private final static Logger LOGGER = Logger.getLogger(MySQLBase.class.getName());
	
	Connection connection = null;
	
	public void open(String host, String user, String passwd, String port, String database) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LOGGER.info("Not found "+e.toString());
			throw new Exception();
		}

		LOGGER.info("MySQL JDBC Driver Registered!");

		try {
			connection = DriverManager
					.getConnection("jdbc:mysql://"+host+":"+port+"/"+database,
							user, passwd);

		} catch (SQLException e) {
			LOGGER.info("Connection Failed! "+e.toString());
			throw new Exception();
		}

		if (connection != null) {
			LOGGER.info("Connected!");
		} else {
			LOGGER.info("Failed to make connection!");
		}

	}
	
	public void close() {
		if (connection != null) {
			LOGGER.info("Still Connected!");
			try {
				connection.close();
				LOGGER.info("Closed!");
			} catch (SQLException e) {
				LOGGER.info("Error closing connection!" + e.toString());
			}
		} else {
			LOGGER.info("No connection!");
		}		
	}

}
