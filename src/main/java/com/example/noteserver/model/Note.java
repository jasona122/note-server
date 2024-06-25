package com.example.noteserver.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Note {
	
	private static final String EMPTY_TEXT_ERROR_MESSAGE = "Text cannot be empty";
	private static final String NULL_TEXT_ERROR_MESSAGE = "Text field is not present in the request body";
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=1, message=EMPTY_TEXT_ERROR_MESSAGE)
    @NotNull(message=NULL_TEXT_ERROR_MESSAGE)
    private String text;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;

    public Note() {
        // Default constructor required by JPA
    }
    
    public Note(Long id, String text, LocalDateTime timestamp) {
    	this.id = id;
    	this.text = text;
    	this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
}
