package com.example.noteserver.handler;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	
	private HttpStatus status;
	private String message;
	private String error;
	
	private ErrorResponse(HttpStatus status, String message) {
		this.status = status;
		this.message = message;
	}

	public ErrorResponse(HttpStatus status, String message, Throwable ex) {
		this(status, message);
		this.error = ex.getMessage();
	}
	
	public ErrorResponse(HttpStatus status, String message, String errorMessage) {
		this(status, message);
		this.error = errorMessage;
	}
   
	public HttpStatus getStatus() {
		return this.status;
	}
   
	public String getMessage() {
		return this.message;
	}
	
	public String getError() {
		return this.error;
	}
	
}