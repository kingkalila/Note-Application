package com.simplenote.note.repository;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplenote.note.model.Note;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class NoteRepository {
    private static final String NOTES_FILE = "notes.json";
    private List<Note> notes;
    private ObjectMapper mapper = new ObjectMapper();

    public NoteRepository() {
        notes = new ArrayList<>();
        loadNotes();
    }

    public List<Note> getAllNotes() {
        return notes;
    }

    public Note getNoteById(String id) {
        for (Note note : notes) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    public void createNote(Note note) {
        note.setId(UUID.randomUUID().toString());
        notes.add(note);
        saveNotes();
    }

    public void updateNote(Note note) {
        Note existingNote = getNoteById(note.getId());
        if (existingNote != null) {
            existingNote.setTitle(note.getTitle());
            existingNote.setBody(note.getBody());
            saveNotes();
        }
    }

    public void deleteNote(String id) {
        Note note = getNoteById(id);
        if (note != null) {
            notes.remove(note);
            saveNotes();
        }
    }

    private void loadNotes() {
        File file = new File(NOTES_FILE);
        if (file.exists()) {
            try {
                // Load notes from file
                // ...
                notes = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Note.class));
            } catch (IOException e) {
                // Handle error
                System.err.println("Error loading notes: " + e.getMessage());
            }
        }
    }

    private void saveNotes() {
        try {
            // Save notes to file
            // ...
            mapper.writeValue(new File(NOTES_FILE), notes);
        } catch (IOException e) {
            // Handle error
            System.err.println("Error saving notes: " + e.getMessage());
        }
    }
}
