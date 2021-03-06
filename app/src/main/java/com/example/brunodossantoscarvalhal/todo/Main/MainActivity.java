package com.example.brunodossantoscarvalhal.todo.Main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.brunodossantoscarvalhal.todo.Edit.EditActivity;
import com.example.brunodossantoscarvalhal.todo.Notes.Note;
import com.example.brunodossantoscarvalhal.todo.Notes.NoteListFragment;
import com.example.brunodossantoscarvalhal.todo.Notes.NoteRecyclerViewAdapter;
import com.example.brunodossantoscarvalhal.todo.Notes.NoteRepository;
import com.example.brunodossantoscarvalhal.todo.Notes.NotesViewModel;
import com.example.brunodossantoscarvalhal.todo.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements NoteListFragment.OnListFragmentInteractionListener {

    DrawerLayout mDrawerLayout;
    Fragment currentFragment;
    NotesViewModel notesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NoteRepository.InitializeInstance(getApplicationContext());
        notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);

        setupActionBar();
        setupNavigationListener();
        setupFloatingActionButton();
        setFragment(new NoteListFragment(), null, false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_sun:
                Snackbar.make(findViewById(R.id.fragment_container), R.string.sun, Snackbar.LENGTH_LONG).show();
                return true;
            case R.id.action_help:
                setHelpFragment();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EditActivity.EDIT_REQUEST_CODE) {
            if (resultCode == EditActivity.EDIT_RESULT_OK || resultCode == EditActivity.EDIT_RESULT_DELETED) {
                if (!(currentFragment instanceof NoteListFragment)) {
                    setNoteListFragment();
                }
            }
        }
    }

    private void setupFloatingActionButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivityForResult(intent, EditActivity.EDIT_REQUEST_CODE);
            }
        });
    }

    private void setupNavigationListener() {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.drawer_1:
                                setNoteListFragment();
                                break;
                            case R.id.drawer_2:
                                setHelpFragment();
                                break;
                            default:
                                break;
                        }
                        mDrawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    private void setupActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle(R.string.main_title);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);
    }

    private void setFragment(Fragment fragment, Bundle args, boolean addToBackStack) {
        if (args != null) {
            fragment.setArguments(args);
        }

        currentFragment = fragment;

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            transaction.addToBackStack(null);
        }

        transaction.commit();
    }

    private void setHelpFragment() {
        Bundle args = new Bundle();
        args.putString(HelpFragment.ARG_TEXT, "Help");
        setFragment(new HelpFragment(), args, true);
    }

    private void setNoteListFragment() {
        setFragment(new NoteListFragment(), null, true);
    }

    @Override
    public void onListFragmentClick(Note item) {
        Intent intent = new Intent(MainActivity.this, EditActivity.class);
        intent.putExtra(NoteRepository.ARG_NOTE_ID, item.id);
        startActivityForResult(intent, EditActivity.EDIT_REQUEST_CODE);
    }

    @Override
    public void onListFragmentCheck(Note item, boolean checked) {
        notesViewModel.setNoteCompletion(item, checked);
    }
}
