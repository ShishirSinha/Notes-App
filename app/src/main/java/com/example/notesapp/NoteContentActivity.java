package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
}
