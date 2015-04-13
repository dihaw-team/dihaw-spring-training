package com.dihaw.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.dihaw.entity.User;
import com.dihaw.utils.ValidationSupportUtils;

@Component("validatorUserEntry")
public class ValidatorUserEntry implements Validator {
	
	private final static int MAX_LENGTH = 10;
	private final static int MIN_LENGTH = 3;
	
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object arg0, Errors errors) {
		
		User target = (User) arg0;
		
		ValidationSupportUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "validation.not.null");
		ValidationSupportUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "validation.not.null");
		ValidationSupportUtils.rejectIfEmptyOrWhitespace(errors, "gender", "validation.not.null");
		ValidationSupportUtils.rejectIfEmptyOrWhitespace(errors, "city", "validation.not.null");
		
		if (!errors.hasFieldErrors("firstName") && target.getFirstName() != null) {
			
			if (target.getFirstName().length() > MAX_LENGTH) {
				errors.rejectValue("firstName", "validation.bad.max.length", new Object[]{MAX_LENGTH}, "validation.bad.max.length");
			}
			if (target.getFirstName().length() < MIN_LENGTH) {
				errors.rejectValue("firstName", "validation.bad.min.length", new Object[]{MIN_LENGTH}, "validation.bad.min.length");
			}
			if (!errors.hasFieldErrors("firstName")) {
				ValidationSupportUtils.rejectIfContainsSpecialChars(errors, "firstName", "validation.bad.chars");
			}
		}
		
		if (!errors.hasFieldErrors("lastName") && target.getLastName() != null) {
			
			if (target.getLastName().length() > MAX_LENGTH) {
				errors.rejectValue("lastName", "validation.bad.max.length", new Object[]{MAX_LENGTH}, "validation.bad.max.length");
			}
			if (target.getLastName().length() < MIN_LENGTH) {
				errors.rejectValue("lastName", "validation.bad.min.length", new Object[]{MIN_LENGTH}, "validation.bad.min.length");
			}
			if (!errors.hasFieldErrors("lastName")) {
				ValidationSupportUtils.rejectIfContainsSpecialChars(errors, "lastName", "validation.bad.chars");
			}
		}
		
		if (!errors.hasFieldErrors("gender") && target.getGender().value() != null) {
			
			if (!errors.hasFieldErrors("gender")) {
				ValidationSupportUtils.rejectIfContainsSpecialChars(errors, "gender", "validation.bad.chars");
			}
		}		
		
	}

}
