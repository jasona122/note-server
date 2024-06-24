package com.example.noteserver.handler;

import java.util.ArrayList;
import java.util.List;

public class FailureNoteResponse extends NoteResponse {
	
	public FailureNoteResponse(List<ErrorResponse> errors) {
		super(false);
		this.setErrors(errors);
	}
	
	public FailureNoteResponse(ErrorResponse error) {
		super(false);
		List<ErrorResponse> errors = new ArrayList<ErrorResponse>();
		errors.add(error);
		this.setErrors(errors);
	}
	
}