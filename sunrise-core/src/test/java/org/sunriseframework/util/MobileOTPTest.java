package org.sunriseframework.util;

import junit.framework.TestCase;

public class MobileOTPTest extends TestCase {
	
	public void testOTP() {
		String initsecret = "1234567890123456";
		String pin = "1234"; 
		String otp = "f44527";
		String token = MobileOTP.generateOTP(initsecret, pin);
		boolean resultF = MobileOTP.checkOTP("joe", otp, initsecret, pin,0);
		assertFalse(resultF);
		boolean resultT = MobileOTP.checkOTP("joe", token, initsecret, pin,0);
		assertTrue(resultT);
	}

	public void testOTP2() {
		String initsecret = "1234567890123456";
		String pin = "1234";
		String otp = MobileOTP.generateOTP(initsecret, pin);
		boolean result = MobileOTP.checkOTP("joe", otp, initsecret, pin,0);
		assertEquals(result, true);

	}
}
