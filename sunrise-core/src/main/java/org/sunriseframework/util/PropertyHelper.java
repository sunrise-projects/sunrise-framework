package org.sunriseframework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHelper {
	
	public static Properties load(String filename) {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = PropertyHelper.class.getClassLoader().getResourceAsStream(filename);
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return null;
			}

			// load a properties file from class path, inside static method
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}
