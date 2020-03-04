package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.notesapp.DatabaseFiles.AppExecutors;
import com.example.notesapp.DatabaseFiles.NotesDb;
import com.example.notesapp.DatabaseFiles.NotesTable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private List<NotesTable> notesList;
    private FloatingActionButton addNoteFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNoteFab = findViewById(R.id.addNoteFab);
        recyclerView = findViewById(R.id.rv1);

        notesList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        NotesClass note1 = new NotesClass("Untitled", "14-07-2019 07:00AM", "Hey! its my birthday today! Let's party");
//        NotesClass note2 = new NotesClass("Project List", "14-02-2019 07:00AM", "Notes App, RoadExpress App");
//        NotesClass note3 = new NotesClass("Workshops", "14-07-2019 07:00AM", "App Development Workshop, IoT workshop");
//
//        notesList.add(note1);
//        notesList.add(note2);
//        notesList.add(note3);

        final NotesDb appDb = NotesDb.getInstance(this);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                notesList = appDb.notesTableDao().getAllItems();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "Total number of notes = " + notesList.size());

                        adapter = new NotesAdapter(notesList, MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });



        addNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String currentDateandTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());

                Intent intent = new Intent(MainActivity.this, NoteContentActivity.class);
                intent.putExtra("heading", "Untitled");
                intent.putExtra("datetime", currentDateandTime);
                intent.putExtra("content", "");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        final NotesDb appDb = NotesDb.getInstance(this);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                notesList = appDb.notesTableDao().getAllItems();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.e(TAG, "Total number of notes = " + notesList.size());

                        adapter = new NotesAdapter(notesList, MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });
    }
}
