package org.sunriseframework.util;

import junit.framework.TestCase;

public class HashTest extends TestCase{

	public void testS() {
		String hash = Hash.generateHash("sample".getBytes(), "MD5");
		String result = "Xo/5v1W6NQgZnSLphBKb5g==";
		System.out.println(hash);
		System.out.println(result);
	}
}
