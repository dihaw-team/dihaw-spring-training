package com.dihaw.controller.exception;

/**
 * Exception thrown when a user was not found.
 * 
 */
public class UserNotFoundException extends UserException{
	private static final long serialVersionUID = -6204868616442412784L;

	public UserNotFoundException(String message) {
        super(message);
    }

}
