package com.example.noteserver.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.noteserver.handler.NoteNotFoundException;
import com.example.noteserver.model.Note;
import com.example.noteserver.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;
	
	public List<Note> getAllNotes(Sort sorter) {
		return noteRepository.findAll(sorter);
	}
	
	public List<Note> search(String text) {
		return noteRepository.findNoteByText(text);
	}
	
	public Note saveNote(Note note) {
		note.setTimestamp(LocalDateTime.now());
		return noteRepository.save(note);
	}
	
	public Note getNoteById(long id) {
		Note note = noteRepository.findById(id).orElseThrow(() -> new NoteNotFoundException("Note not found with id: " + id));
		return note;
	}

	public void deleteAllNotes() {
		noteRepository.deleteAll();
	}

}