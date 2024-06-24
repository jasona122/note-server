package com.example.noteserver.service;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.noteserver.model.Note;

import reactor.core.publisher.Mono;

@Service
public class ClientService {
	
	private final WebClient webClient;
	private static final String noteAPI = "https://note-app.free.beeceptor.com";

	public ClientService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl(noteAPI).build();
	}

	public Mono<ResponseEntity<Note>> pushNote(Note note) {
		return this.webClient.post().uri("/notes")
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(note)
				.retrieve()
				.toEntity(Note.class);
	}
	
}