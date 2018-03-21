package com.example.brunodossantoscarvalhal.todo.Notes;

/**
 * Created by brunodossantoscarvalhal on 20/03/2018.
 */

public class Note {
    private String title;
    private String description;
    private boolean completed;
    private int id;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) { this.description = description; }

    public int getId() {
        return this.id;
    }
    public void setId(int id) { this.id = id; }

    public boolean getCompleted() { return this.completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
