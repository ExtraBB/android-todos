package com.example.brunodossantoscarvalhal.todo.Notes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by brunodossantoscarvalhal on 20/03/2018.
 */

@Entity(tableName = "notes")
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String description;
    public boolean completed;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }
}
