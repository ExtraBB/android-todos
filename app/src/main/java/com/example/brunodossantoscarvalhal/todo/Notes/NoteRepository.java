package com.example.brunodossantoscarvalhal.todo.Notes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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
        database = Room.databaseBuilder(context, AppDatabase.class, "notes-db").build();
    }

    public LiveData<List<Note>> getNotes() {
        return database.noteDao().loadNotes();
    }

    public Note getNoteById(int id) {
        return database.noteDao().loadNoteById(id);
    }

    public void insertNotes(Note... notes) { database.noteDao().insertNotes(notes);}

    public void updateNotes(Note... notes) { database.noteDao().updateNotes(notes);}

    public void deleteNotes(Note... notes) { database.noteDao().deleteNotes(notes);}

    public static void InitializeInstance(Context context) {
        _instance = new NoteRepository(context);
    }

    public static NoteRepository getInstance() {
        return _instance;
    }

}
