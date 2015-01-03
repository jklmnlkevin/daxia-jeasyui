package com.daxia.core.util;

import com.daxia.core.exception.ValidationException;

public class ValidationUtils {

	public static void assertTrue(boolean value, String msg) {
		if (!value) {
			throw new ValidationException(msg);
		}
		
	}
	
}
