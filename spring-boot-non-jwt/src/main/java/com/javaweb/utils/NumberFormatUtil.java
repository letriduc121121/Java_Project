package com.javaweb.utils;

public class NumberFormatUtil {
	public static boolean isLong(String value) {
		if(value==null) return false;
		try {
			Long number=Long.parseLong(value);
			
		}
		catch(NumberFormatException ex) {
			return false;
		}
		return true;
	}
	public static boolean isFloat(String value) {
		if(value==null) return false;
		try {
			Float number=Float.parseFloat(value);
			
		}
		catch(NumberFormatException ex) {
			return false;
		}
		return true;
	}
}
