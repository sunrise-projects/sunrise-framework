package org.sunriseframework.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import junit.framework.TestCase;

public class ExcelTest extends TestCase {

	public void testExcel() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException, FileNotFoundException  {
		
//		String filename = "C:\\excel.xlsx";
//		InputStream is = new FileInputStream(filename);
		InputStream is = getClass().getResourceAsStream("/excel.xlsx");
		List<Object> obj = (List<Object>)Excel.load(ExcelObj.class, is);
		assertEquals(obj.size(), 5);
		assertEquals(((ExcelObj)obj.get(0)).getHeader1(), "a1");
	
	}

}
