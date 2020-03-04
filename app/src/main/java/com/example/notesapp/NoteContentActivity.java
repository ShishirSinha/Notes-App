package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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
