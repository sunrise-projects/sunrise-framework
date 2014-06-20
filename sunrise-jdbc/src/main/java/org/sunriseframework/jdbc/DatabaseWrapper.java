package org.sunriseframework.jdbc;


public interface DatabaseWrapper extends MySQLWrapper {
	public <T> T fetch(String query, String columnLabel, Class<T> classFile);
	public void insert(String query, Object... args);
}
