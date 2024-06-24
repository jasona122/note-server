package com.example.noteserver.handler;

import java.time.LocalDateTime;
import java.util.List;

import com.example.noteserver.model.Note;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NoteResponse {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
    private boolean success;
    
    @JsonProperty("notes")
    @JsonInclude(Include.NON_NULL)
    private List<Note> notesList;
    
    @JsonInclude(Include.NON_NULL)
    private Note note;
    
    private List<ErrorResponse> errors;
    
    protected NoteResponse(boolean success) {
    	this.timestamp = LocalDateTime.now();
    	this.success = success;
    }
    
    public boolean getSuccess() {
    	return this.success;
    }
	
    public Note getNote() {
    	return this.note;
    }
    
    public List<ErrorResponse> getErrors() {
    	return this.errors;
    }
    
    public List<Note> getNotesList() {
    	return this.notesList;
    }
    
    protected void setNote(Note note) {
    	this.note = note;
    }
    
    protected void setNotesList(List<Note> notes) {
    	this.notesList = notes;
    }
    
    protected void setErrors(List<ErrorResponse> errors) {
    	this.errors = errors;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
    	this.timestamp = timestamp;
    }
    
}