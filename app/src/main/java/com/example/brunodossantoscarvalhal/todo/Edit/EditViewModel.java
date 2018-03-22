package com.example.brunodossantoscarvalhal.todo.Edit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.example.brunodossantoscarvalhal.todo.Notes.Note;
import com.example.brunodossantoscarvalhal.todo.Notes.NoteRepository;

import java.util.List;

/**
 * Created by brunodossantoscarvalhal on 22/03/2018.
 */

public class EditViewModel extends ViewModel {

    private MutableLiveData<Note> mNote;
    private final int mNoteId;
    NoteRepository mNoteRepository;

    public EditViewModel(int id) {
        mNoteId = id;
        mNoteRepository = NoteRepository.getInstance();
    }

    public LiveData<Note> getNote() {
        if (mNote == null ) {
            mNote = new MutableLiveData<Note>();
            refreshNote();
        }
        return mNote;
    }

    public void refreshNote() {
        if(mNoteId == -1) {
            return;
        }
        new AsyncTask<Integer,Void,Note>() {
            @Override
            protected Note doInBackground(Integer... ids) {
                return mNoteRepository.getNoteById(ids[0]);
            }
            @Override
            protected void onPostExecute(Note note) {
                mNote.setValue(note);
            }
        }.execute(mNoteId);
    }

    public void insertNote(String title, String description) {
        new AsyncTask<Note,Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Note... notes) {
                mNoteRepository.insertNotes(notes);
                return true;
            }
            @Override
            protected void onPostExecute(Boolean succeeded) {
                refreshNote();
            }
        }.execute(new Note(title, description));
    }

    public void updateNote(String title, String description) {
        Note note = mNote.getValue();
        note.title = title;
        note.description = description;

        new AsyncTask<Note,Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Note... notes) {
                mNoteRepository.updateNotes(notes);
                return true;
            }
            @Override
            protected void onPostExecute(Boolean succeeded) {
                refreshNote();
            }
        }.execute(note);
    }

    public void deleteNote() {
        new AsyncTask<Note,Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Note... notes) {
                mNoteRepository.deleteNotes(notes);
                return true;
            }
            @Override
            protected void onPostExecute(Boolean succeeded) {
                refreshNote();
            }
        }.execute(mNote.getValue());
    }
}
