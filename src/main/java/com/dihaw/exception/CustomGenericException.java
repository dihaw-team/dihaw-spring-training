package com.dihaw.exception;

public class CustomGenericException extends RuntimeException {

	private static final long serialVersionUID = -3402052260770397992L;
	
	private String errorCode;
	private String errorMessage;
	 
	public CustomGenericException() {
	}
	
	public CustomGenericException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	public String getErrorCode() {
		return errorCode;
	}
 
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
 
	public String getErrorMessage() {
		return errorMessage;
	}
 
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
