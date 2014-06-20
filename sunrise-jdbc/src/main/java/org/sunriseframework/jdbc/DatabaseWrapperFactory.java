package org.sunriseframework.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;

import com.mysql.jdbc.PreparedStatement;

public class DatabaseWrapperFactory extends MySQLBase implements
		DatabaseWrapper {

	Properties properties = null;

	private final static Logger LOGGER = Logger
			.getLogger(DatabaseWrapperFactory.class.getName());

	public DatabaseWrapperFactory(Properties p) {
		this.properties = p;
	}

	public void insert(String query, Object... args) {
		try {
			open(properties.getProperty("dbhost"),
					properties.getProperty("dbuser"),
					properties.getProperty("dbpass"),
					properties.getProperty("dbport"),
					properties.getProperty("dbname"));
			Statement statement = null;
			Connection dbConnection = null;
			dbConnection = connection;
			try {
				PreparedStatement pstmt = (PreparedStatement) dbConnection
						.prepareStatement(query);
				int i = 1;
				for (Object obj : args) {
					if (obj instanceof String) {
						pstmt.setString(i, String.valueOf(obj));
					} else if (obj instanceof byte[]) {
						pstmt.setBytes(i, (byte[]) obj);
					} else if (obj instanceof Integer) {
						pstmt.setInt(i, Integer.valueOf(obj.toString()));
					}
					i++;
				}
				pstmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public static String encode(byte[] byteArray) {
		StringBuffer hexBuffer = new StringBuffer(byteArray.length * 2);
		for (int i = 0; i < byteArray.length; i++)
			for (int j = 1; j >= 0; j--)
				hexBuffer.append(HEX[(byteArray[i] >> (j * 4)) & 0xF]);
		return hexBuffer.toString();
	}

	private final static char[] HEX = new char[] { '0', '1', '2', '3', '4',
			'5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public <T> T fetch(String query, String columnLabel, Class<T> classFile) {
		T resp = null;
		try {
			open(properties.getProperty("dbhost"),
					properties.getProperty("dbuser"),
					properties.getProperty("dbpass"),
					properties.getProperty("dbport"),
					properties.getProperty("dbname"));

			Map<String, Object> map = new HashMap<String, Object>();
			Connection dbConnection = null;
			Statement statement = null;
			try {
				dbConnection = connection;
				statement = dbConnection.createStatement();

				LOGGER.info(query);

				ResultSet rs = statement.executeQuery(query);

				if (rs.next()) {
					resp = rs.getObject(columnLabel, classFile);
				} else {
					LOGGER.info("No result found.");
				}

			} catch (SQLException e) {
				LOGGER.info(e.toString());
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						LOGGER.info(e.toString());
					}
				}
			}
			System.out.println(map.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return resp;
	}

}
