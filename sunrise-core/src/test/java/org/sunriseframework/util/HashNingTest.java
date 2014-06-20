package org.sunriseframework.util;

import junit.framework.TestCase;

public class HashNingTest extends TestCase{

	public void testS() {
		String hash = Hash.generate("sample".getBytes(), "MD5");
		String result = "Xo/5v1W6NQgZnSLphBKb5g==";
		System.out.println(hash);
		System.out.println(result);
	}
	
	public void testHash() {
        String hash = Hash.generate("sample".getBytes(), "SHA-1");
        String hash2 = Hash.generate("sample".getBytes(), "SHA-1");
        assertEquals(hash, hash2);
	
	}
	public void testNing() {
		byte[] b = Compression.ningEncode("sample".getBytes());
		String c = Compression.ningDecode(b);
		assertEquals("sample", c);
	}	
}
