package com.example.noteserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.noteserver.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
	
	@Query("SELECT n FROM Note n WHERE n.text LIKE %?1%")
	public List<Note> findNoteByText(String text);
}