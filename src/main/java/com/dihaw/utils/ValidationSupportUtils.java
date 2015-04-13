package com.dihaw.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class ValidationSupportUtils {
	
	public final static String SPECIAL_CHARS_REGEX_CLASS = "[^ \"%&\\/\\',.0-9A-Za-z\\-_\\$\\(\\)\\*\\+\\?\\@\\[\\\\\\]]";
	
	public static boolean containsSpecialChars(String target) {
		return StringUtils.isNotBlank(target) && target.matches(".*" + SPECIAL_CHARS_REGEX_CLASS + ".*");
	}
	
	public static void rejectIfEmptyOrWhitespace(Errors errors, String field, String errorCode) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, field, errorCode);
	}
	
	public static void rejectIfContainsSpecialChars(Errors errors, String field, String errorCode) {
		
		Object value = errors.getFieldValue(field);
		if (value != null && containsSpecialChars(value.toString())) {
			errors.rejectValue(field, errorCode);
		}
	}


}
