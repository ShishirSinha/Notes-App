package com.example.notesapp.DatabaseFiles;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {NotesTable.class}, version = 1, exportSchema = false)

public abstract class NotesDb extends RoomDatabase {

    private static final String DB_NAME = "MenuDb.db";
    private static NotesDb instance;

    public static synchronized NotesDb getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NotesDb.class,
                    DB_NAME).build();
        }
        return instance;
    }

    public abstract NotesTableDao notesTableDao();
}