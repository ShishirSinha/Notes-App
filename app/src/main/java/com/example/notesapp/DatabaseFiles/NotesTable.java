package com.example.notesapp.DatabaseFiles;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NotesTable {

    @PrimaryKey
    @NonNull
    private String datetime;
    @NonNull
    private String heading;
    @NonNull
    private String content;


    public NotesTable(@NonNull String heading, String content, String datetime) {
        this.heading = heading;
        this.content = content;
        this.datetime = datetime;
    }

    @NonNull
    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(@NonNull String datetime) {
        this.datetime = datetime;
    }

    @NonNull
    public String getHeading() {
        return heading;
    }

    public void setHeading(@NonNull String heading) {
        this.heading = heading;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }
}
