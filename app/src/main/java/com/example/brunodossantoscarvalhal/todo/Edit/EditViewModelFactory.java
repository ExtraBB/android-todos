package com.example.brunodossantoscarvalhal.todo.Edit;

/**
 * Created by brunodossantoscarvalhal on 22/03/2018.
 */

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class EditViewModelFactory implements ViewModelProvider.Factory {

    private final int noteId;

    public EditViewModelFactory(int noteId) {
        this.noteId = noteId;
    }

    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(EditViewModel.class)) {
            return (T) new EditViewModel(noteId);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
