package org.sunriseframework.jdbc;

public interface MySQLWrapper {
	public void open(String host, String user, String passwd, String port,
			String database) throws Exception;
	public void close();
}
