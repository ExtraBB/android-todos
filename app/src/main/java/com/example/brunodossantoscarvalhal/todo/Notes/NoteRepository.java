package com.example.brunodossantoscarvalhal.todo.Notes;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.example.brunodossantoscarvalhal.todo.Main.AppDatabase;

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

    AppDatabase database;
    static NoteRepository _instance;

    public static final String ARG_NOTE_ID = "note_id";

    public NoteRepository(Context context) {
        database = Room.databaseBuilder(context, AppDatabase.class, "notes-db").allowMainThreadQueries().build();
    }

    public List<Note> getNotes() {
        return database.noteDao().loadNotes();
    }

    public Note getNoteById(int id) {
        return database.noteDao().loadNoteById(id);
    }

    public void addNote(Note note) { database.noteDao().insertNotes(note);}

    public void updateNote(Note note) { database.noteDao().updateNotes(note);}

    public void deleteNote(Note note) { database.noteDao().deleteNotes(note);}

    public static NoteRepository getInstance(Context context) {
        if(_instance == null) {
            _instance = new NoteRepository(context);
        }
        return _instance;
    }

}
