package com.example.noteserver.handler;

import java.util.List;

import com.example.noteserver.model.Note;

public class SuccessNoteResponse extends NoteResponse {

	public SuccessNoteResponse() {
		super(true);
	}
	
	public SuccessNoteResponse(Note note) {
		super(true);
		this.setNote(note);
	}
	
	public SuccessNoteResponse(List<Note> notesList) {
		super(true);
		this.setNotesList(notesList);
	}
	
}