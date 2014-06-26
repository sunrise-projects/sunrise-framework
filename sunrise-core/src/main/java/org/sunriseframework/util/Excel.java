package org.sunriseframework.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
* Helper utility to load excel
* @author Apache  Open-source
* @since 2.0.0.M1
*/
public class Excel {

	/**
	 * 
	 * @param c - class
	 * @param is - load file thru file system
	 * @return - custom object
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	
	public static  <T> List<T> load(Class c, InputStream is) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, ClassNotFoundException {
		
		
		List<T> listT = new ArrayList<T>();
		
		try {

		// Create Workbook instance holding reference to .xlsx file
		XSSFWorkbook workbook = new XSSFWorkbook(is);

		// Get first/desired sheet from the workbook
		XSSFSheet sheet = workbook.getSheetAt(0);

		// Iterate through each rows one by one
		Iterator<Row> rowIterator = sheet.iterator();
		List<String> headers = new ArrayList<String>();
		int counter = 0;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			// For each row, iterate through all the columns
			Iterator<Cell> cellIterator = row.cellIterator();
			if(counter == 0) {
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						throw new Exception("Numeric header is not allowed!");
					case Cell.CELL_TYPE_STRING:
						headers.add(cell.getStringCellValue().toString());
						break;
					}
				}				
			} else {
				@SuppressWarnings("unchecked")
				T classInstance = (T) Class.forName(c.getName()).newInstance();		
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					int colIdx = cell.getColumnIndex();
					Object cellValue = null;
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						cellValue = cell.getNumericCellValue();
						break;
					case Cell.CELL_TYPE_STRING:
						cellValue = cell.getStringCellValue();
						break;
					}					
					for (Method method : Class.forName(c.getName()).getMethods()){
						if(method.getName().contains("set"+headers.get(colIdx))) {
							method.invoke(classInstance, cellValue);
						}
					}					
				}
				listT.add(classInstance);
			}
			counter++;
		}
	} catch (Exception e) {
		e.printStackTrace();
	}		
		
		
		return listT;
		
		
	}	
	
}
