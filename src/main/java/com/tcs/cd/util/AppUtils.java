/**
 * 
 */
package com.tcs.cd.util;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author darsh500
 *
 */
public class AppUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(AppUtils.class);

	/**
	 * Class contains reusable static utilities
	 */
	public AppUtils() {
	
	}

	public static boolean isnumberstring(String string) {
		if (string.matches("[0-9]+")){
			LOG.info("isnumberstring? ->  yes it is a number");
			return true;	        
		}
		LOG.info("isnumberstring? -> No it is not a number");
		return false;
	}
	
	public static boolean isNull(Object object) {		
		if (object == null) {
			return true;
		}
		return false;
	}
	public static boolean isNullOrEmpty(String string) {		
		if(string==null || string.equals("") || string.trim().length()==0){
			return true;
		}
		return false;

	}
	public static <T> boolean IsNullOrEmpty(Collection<T> list) {
		if( list == null || list.isEmpty() || list.size()==0)
			return true;
		return false;
	}
	

}
