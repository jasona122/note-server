package com.example.noteserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.noteserver.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{}