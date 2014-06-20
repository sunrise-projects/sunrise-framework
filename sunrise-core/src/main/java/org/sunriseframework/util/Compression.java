package org.sunriseframework.util;

import com.ning.compress.lzf.LZFDecoder;
import com.ning.compress.lzf.LZFEncoder;
import com.ning.compress.lzf.LZFException;

public class Compression {

	public static String ningDecode(byte[] compressed) {
		String result = null;
		try {
			byte[] uncompressed = LZFDecoder.decode(compressed);
			result = new String(uncompressed);
		} catch (LZFException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return result;		
	}
	
	public static byte[] ningEncode(byte[] compressed) {
		return LZFEncoder.encode(compressed);		
	}		
	
}
