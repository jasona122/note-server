package com.example.noteserver.handler;

import java.time.zone.ZoneRulesException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class NoteExceptionHandler extends ResponseEntityExceptionHandler {
	
    @ExceptionHandler(Exception.class)
    public ResponseEntity<NoteResponse> handle(HttpServletRequest req, Exception ex) {
    	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Whoops, something went wrong on our end!", ex);
    	NoteResponse response = new FailureNoteResponse(errorResponse);
        return new ResponseEntity<NoteResponse>(response, errorResponse.getStatus());
    }
    
    @ExceptionHandler({
    	NoResourceFoundException.class,
    	NoHandlerFoundException.class,
    	HttpRequestMethodNotSupportedException.class
    })
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ResponseEntity<NoteResponse> handleBadPathException(Exception ex) {
    	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "Our application does not handle this path", ex);
    	NoteResponse response = new FailureNoteResponse(errorResponse);
    	return new ResponseEntity<NoteResponse>(response, errorResponse.getStatus());
    }
    
    @ExceptionHandler(NoteNotFoundException.class)
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public ResponseEntity<NoteResponse> handleUserNotFoundException(Exception ex) {
    	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "Note is not present", ex);
    	NoteResponse response = new FailureNoteResponse(errorResponse);
    	return new ResponseEntity<NoteResponse>(response, errorResponse.getStatus());
    }
    
    @ExceptionHandler({
    	MethodArgumentTypeMismatchException.class,
    	ZoneRulesException.class
    })
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ResponseEntity<NoteResponse> handleMethodArgumentTypeMismatch(Exception ex) {
    	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid argument provided", ex);
    	NoteResponse response = new FailureNoteResponse(errorResponse);
    	return new ResponseEntity<NoteResponse>(response, errorResponse.getStatus());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ResponseEntity<NoteResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
    	String errorMessage = ex.getFieldError().getDefaultMessage();
    	ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid argument provided", errorMessage);
    	NoteResponse response = new FailureNoteResponse(errorResponse);
    	return new ResponseEntity<NoteResponse>(response, errorResponse.getStatus());
    }
    
}