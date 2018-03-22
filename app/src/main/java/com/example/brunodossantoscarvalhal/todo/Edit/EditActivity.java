package com.example.brunodossantoscarvalhal.todo.Edit;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.brunodossantoscarvalhal.todo.Notes.Note;
import com.example.brunodossantoscarvalhal.todo.Notes.NoteRepository;
import com.example.brunodossantoscarvalhal.todo.Notes.NotesViewModel;
import com.example.brunodossantoscarvalhal.todo.R;

public class EditActivity extends AppCompatActivity {

    public static final int EDIT_REQUEST_CODE = 1337;
    public static final int EDIT_RESULT_OK = 1338;
    public static final int EDIT_RESULT_CANCELLED = 1339;
    public static final int EDIT_RESULT_DELETED = 1340;

    EditText titleEditText;
    EditText descriptionEditText;

    EditViewModel viewModel;
    boolean editingExistingNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        titleEditText = findViewById(R.id.title_edit);
        descriptionEditText = findViewById(R.id.description_edit);

        setupActionBar();
        setupFloatingActionButton();
        setupViewModel();
    }

    private void setupViewModel() {
        Bundle bundle = getIntent().getExtras();
        int noteId = -1;
        if (bundle != null) {
            noteId = bundle.getInt(NoteRepository.ARG_NOTE_ID, -1);
        }

        viewModel = ViewModelProviders.of(this, new EditViewModelFactory(noteId)).get(EditViewModel.class);
        viewModel.getNote().observe(this, new Observer<Note>() {
            @Override
            public void onChanged(@Nullable Note note) {
                editingExistingNote = note != null;
                setFieldsToNote(note);
            }
        });
        viewModel.refreshNote();
    }

    private void setFieldsToNote(Note note) {
        if (note != null) {
            titleEditText.setText(note.title);
            descriptionEditText.setText(note.description);
        } else {
            titleEditText.setText("");
            descriptionEditText.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (editingExistingNote) {
            getMenuInflater().inflate(R.menu.edit_menu, menu);
        }
        return true;
    }

    private void setupActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle(R.string.edit_title);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }

    private void setupFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (titleEditText.getText().toString().equals("")) {
                    Snackbar.make(findViewById(R.id.input_container), R.string.no_title, Snackbar.LENGTH_LONG).show();
                    return;
                }
                if (editingExistingNote) {
                    viewModel.updateNote(titleEditText.getText().toString(), descriptionEditText.getText().toString());
                } else {
                    viewModel.insertNote(titleEditText.getText().toString(), descriptionEditText.getText().toString());
                }
                setResult(EDIT_RESULT_OK);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(EDIT_RESULT_CANCELLED);
                finish();
                return true;
            case R.id.action_delete:
                viewModel.deleteNote();
                setResult(EDIT_RESULT_DELETED);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
