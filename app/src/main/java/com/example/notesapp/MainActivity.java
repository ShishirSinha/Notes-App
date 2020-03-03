package com.example.notesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String TAG = getClass().getSimpleName();
    private RecyclerView recyclerView;
    private NotesAdapter adapter;
    private List<NotesClass> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rv1);

        notesList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        NotesClass note1 = new NotesClass("Untitled", "14-07-2019 07:00AM", "Hey! its my birthday today! Let's party");
        NotesClass note2 = new NotesClass("Project List", "14-02-2019 07:00AM", "Notes App, RoadExpress App");
        NotesClass note3 = new NotesClass("Workshops", "14-07-2019 07:00AM", "App Development Workshop, IoT workshop");

        notesList.add(note1);
        notesList.add(note2);
        notesList.add(note3);

        adapter = new NotesAdapter(notesList, this);
        recyclerView.setAdapter(adapter);

    }
}
