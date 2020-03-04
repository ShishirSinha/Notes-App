package com.example.notesapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.DatabaseFiles.AppExecutors;
import com.example.notesapp.DatabaseFiles.NotesDb;
import com.example.notesapp.DatabaseFiles.NotesTable;

import java.util.Objects;

public class NoteContentActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private String noteHeading, noteDateTime, noteContent;
    private TextView noteHeadingTv, noteDateTimeTv, noteContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_content);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        noteHeadingTv = findViewById(R.id.noteHeadingTV);
        noteDateTimeTv = findViewById(R.id.noteDateTimeTV);
        noteContentTv = findViewById(R.id.noteContentTV);

        noteHeading = getIntent().getStringExtra("heading");
        noteDateTime = getIntent().getStringExtra("datetime");
        noteContent = getIntent().getStringExtra("content");

        noteHeadingTv.setText(noteHeading);
        noteDateTimeTv.setText(noteDateTime);
        noteContentTv.setText(noteContent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_delete) {
            final NotesDb appDb = NotesDb.getInstance(this);

            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    appDb.notesTableDao().deleteByDateTime(noteDateTime);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(NoteContentActivity.this, "Note deleted!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    });
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        noteHeading = noteHeadingTv.getText().toString().trim();
        noteContent = noteContentTv.getText().toString().trim();

        final NotesDb appDb = NotesDb.getInstance(this);
        final NotesTable x = new NotesTable(noteHeading, noteContent, noteDateTime);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                appDb.notesTableDao().insert(x);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(NoteContentActivity.this, "Note created!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
