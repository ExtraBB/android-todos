package com.example.brunodossantoscarvalhal.todo.Notes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by brunodossantoscarvalhal on 22/03/2018.
 */

public class NotesViewModel extends ViewModel {

    private LiveData<List<Note>> mNotes;
    private NoteRepository mNoteRepository;

    public LiveData<List<Note>> getNotes() {
        if(mNoteRepository == null) {
            mNoteRepository = NoteRepository.getInstance();
        }
        if (mNotes == null) {
            mNotes = mNoteRepository.getNotes();
        }
        return mNotes;
    }

    public void setNoteCompletion(Note note, boolean completion) {
        note.completed = completion;
        new AsyncTask<Note,Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Note... notes) {
                mNoteRepository.updateNotes(notes);
                return true;
            }
        }.execute(note);
    }

}
