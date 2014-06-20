/*
* Copyright 2002-2013 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.sunriseframework.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

/**
* Helper utility to generate hash
* @author Apache  Open-source
* @since 1.0-rc1
*/
public class Hash {

	public static String generateHash(byte[] input, String algorithm) {
        MessageDigest algo;
        String passwordHash = null;
		try {
			algo = MessageDigest.getInstance(algorithm); //MD5, SHA-1
			algo.reset();
			algo.update(input);
	        Base64 encoder = new Base64();
	        passwordHash = new String(encoder.encode(algo.digest()));    			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return passwordHash;
	
	}	
}
