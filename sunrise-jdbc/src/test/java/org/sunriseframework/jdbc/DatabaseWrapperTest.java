package org.sunriseframework.jdbc;


import org.sunriseframework.util.Compression;
import org.sunriseframework.util.PropertyHelper;

import junit.framework.TestCase;

public class DatabaseWrapperTest extends TestCase {

	public void tRest6() {
		System.out.println("...");
		DatabaseWrapper db = new DatabaseWrapperFactory(PropertyHelper.load("config.properties"));
		String selectTableSQL = "select a from b where c=unhex('d')";
		String res = db.fetch(selectTableSQL, "e", String.class);
		System.out.println(res);		
	}
	
	public void tRest5() {
		System.out.println("...");		
		DatabaseWrapper db = new DatabaseWrapperFactory(PropertyHelper.load("config.properties"));
		String selectTableSQL = "select a from b where c=unhex('d')";
		byte[] r = db.fetch(selectTableSQL, "e", byte[].class);
		String result = Compression.ningDecode(r);
		System.out.println(result);
	}	
	
	public void tRest4() {	
		DatabaseWrapper db = new DatabaseWrapperFactory(PropertyHelper.load("config.properties"));
		String selectTableSQL = "select a from b where c=unhex('d')";
		byte[] r = db.fetch(selectTableSQL, "e", byte[].class);
		String result = Compression.ningDecode(r);
		System.out.println(result);
	}	
	
	public void tRest3 () {
		String str = "plaintext";
		byte[] comp = Compression.ningEncode(str.getBytes());
		DatabaseWrapper db = new DatabaseWrapperFactory(PropertyHelper.load("config.properties"));
		String sql = "INSERT INTO a (b, c,d, e ) "
				+ "VALUES (unhex(?),?,?,?)";
		db.insert(sql,"f",comp,"g","0000-00-00 00:00:00");

	}
	
	public void tRest2 () {
		System.out.println("...");		
		String str = "plaintext";
		DatabaseWrapper db = new DatabaseWrapperFactory(PropertyHelper.load("config.properties"));
		String sql = "INSERT INTO a (b, c,d, e ) "
				+ "VALUES (unhex(?),?,?,?)";
		db.insert(sql,"a",str,"b","0000-00-00 00:00:00");

	}
	
	public void tRest1() {		
		DatabaseWrapper db = new DatabaseWrapperFactory(PropertyHelper.load("config.properties"));
		String selectTableSQL = "select ...";
		byte[] r = db.fetch(selectTableSQL, "attr", byte[].class);
		System.out.println(Compression.ningDecode(r));
	}	

	public void test() {
		System.out.println("Remove R on methods above and fix the sql sample.");
	}
}
