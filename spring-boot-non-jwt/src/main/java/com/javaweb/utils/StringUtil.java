package com.javaweb.utils;

public class StringUtil {
	public static boolean check(String value) {
		if(value!=null && !value.isBlank()) return true;
		else
			return false;
	}
}
