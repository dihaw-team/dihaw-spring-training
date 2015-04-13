package com.dihaw.controller.exception;

/**
 * Abstract base exception for user.
 * 
 */
public abstract class UserException extends Exception {
	private static final long serialVersionUID = 4887331242311607724L;
	
    public UserException(String message) {
        super(message);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

}
