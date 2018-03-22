package com.example.brunodossantoscarvalhal.todo.Notes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by brunodossantoscarvalhal on 21/03/2018.
 */

@Dao
public interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertNotes(Note... notes);

    @Update
    public void updateNotes(Note... notes);

    @Delete
    public void deleteNotes(Note... notes);

    @Query("SELECT * FROM notes")
    public List<Note> loadNotes();

    @Query("SELECT * FROM notes WHERE id = :id LIMIT 1")
    public Note loadNoteById(int id);
}
