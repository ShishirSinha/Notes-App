package com.example.notesapp.DatabaseFiles;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface NotesTableDao {

    @Query("SELECT * FROM NotesTable ORDER BY NotesTable.datetime")
    List<NotesTable> getAllItems();

//    @Query("SELECT * FROM NotesTable WHERE NotesTable.datetime = :datetime")
//    List<NotesTable> getNoteByDateTime(String datetime);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(NotesTable notes);

    @Query("DELETE FROM NotesTable WHERE NotesTable.datetime = :datetime")
    void deleteByDateTime(String datetime);

    @Query("DELETE FROM NotesTable")
    void deleteAllNotes();
}