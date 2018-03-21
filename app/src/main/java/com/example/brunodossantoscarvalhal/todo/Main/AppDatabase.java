package com.example.brunodossantoscarvalhal.todo.Main;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.brunodossantoscarvalhal.todo.Notes.Note;
import com.example.brunodossantoscarvalhal.todo.Notes.NoteDao;

/**
 * Created by brunodossantoscarvalhal on 21/03/2018.
 */

@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract NoteDao noteDao();
}
