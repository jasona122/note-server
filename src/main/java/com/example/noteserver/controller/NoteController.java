package com.example.noteserver.controller;

import java.net.URI;
import java.time.ZoneId;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.noteserver.handler.NoteResponse;
import com.example.noteserver.handler.SuccessNoteResponse;
import com.example.noteserver.model.Note;
import com.example.noteserver.service.ClientService;
import com.example.noteserver.service.NoteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notes")
public class NoteController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoteController.class);
	
	@Autowired
	private NoteService noteService;
	
	@Autowired
	private ClientService client;
	
	@Tag(name = "GET", description = "GET methods of Notes API")
    @GetMapping
    public ResponseEntity<NoteResponse> getAllNotes(            
    		@RequestParam(required = false) String tz,
            @RequestParam(defaultValue = "ASC") String order) {
        Sort sorter = Sort.by(Sort.Direction.fromString(order), "timestamp");
		List<Note> notes = noteService.getAllNotes(sorter);
		
        if (tz != null) {
            ZoneId zoneId = ZoneId.of(tz);
            notes.forEach(note -> note.setTimestamp(
                note.getTimestamp().atZone(ZoneId.systemDefault()).withZoneSameInstant(zoneId).toLocalDateTime()
            ));
        }
		
		NoteResponse response = new SuccessNoteResponse(notes);
		return ResponseEntity.ok(response);
    }
    
	@Tag(name = "GET", description = "GET methods of Notes API")
    @GetMapping("/{id}")
    public ResponseEntity<NoteResponse> getNoteById(@Valid @PathVariable long id) {
    	Note note = noteService.getNoteById(id);
    	NoteResponse response = new SuccessNoteResponse(note);
    	return ResponseEntity.ok(response);
    }
    
	@Tag(name = "POST", description = "POST methods of Notes API")
    @PostMapping
    public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody Note note) {
    	Note createdNote = noteService.saveNote(note);
    	URI location = ServletUriComponentsBuilder
    			.fromCurrentRequest()
    			.path("/{id}")
    			.buildAndExpand(createdNote.getId())
    			.toUri();
    	
    	client.pushNote(createdNote)
    	.doOnSuccess(message -> {
    		logger.info("Success pushing note with id {}", createdNote.getId());
    	})
    	.onErrorResume(error -> {
            logger.error("Error pushing note with id {}: {}", createdNote.getId(), error.getMessage());
            return Mono.empty();
        })
    	.subscribe();
    	
    	NoteResponse response = new SuccessNoteResponse(createdNote);
    	return ResponseEntity.created(location).body(response);
    }

}
