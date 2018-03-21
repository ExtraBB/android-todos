package com.example.brunodossantoscarvalhal.todo.Notes;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by brunodossantoscarvalhal on 20/03/2018.
 */

public class NoteRepository {

    Map<Integer, Note> mNotes;
    int idCounter = 1;

    static NoteRepository _instance;

    public static final String ARG_NOTE_ID = "note_id";

    public NoteRepository() {
        mNotes = new HashMap<Integer, Note>();
        addNote(new Note("Title 1", "Description 1"));
        addNote(new Note("Title 2", "Description 2"));
        addNote(new Note("Title 3", "Description 3"));
    }

    public List<Note> getNotes() {
        return new ArrayList<Note>(mNotes.values());
    }

    public Note getNoteById(int id) {
        return mNotes.get(id);
    }

    public void addNote(Note note) {
        note.setId(idCounter);
        mNotes.put(idCounter++, note);
    }

    public void editNote(int id, @Nullable String title, @Nullable String description) {
        Note note = mNotes.get(id);
        if(title != null) {
            note.setTitle(title);
        }
        if(description != null) {
            note.setDescription(description);
        }
    }

    public static NoteRepository getInstance() {
        if(_instance == null) {
            _instance = new NoteRepository();
        }
        return _instance;
    }

}
