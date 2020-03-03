package com.example.notesapp;

public class NotesClass {

    private String mNoteHeading;
    private String mNoteDateTime;
    private String mNoteContent;

    public NotesClass(String mNoteHeading, String mNoteDateTime, String mNoteContent) {
        this.mNoteHeading = mNoteHeading;
        this.mNoteDateTime = mNoteDateTime;
        this.mNoteContent = mNoteContent;
    }

    public String getmNoteHeading() {
        return mNoteHeading;
    }

    public void setmNoteHeading(String mNoteHeading) {
        this.mNoteHeading = mNoteHeading;
    }

    public String getmNoteDateTime() {
        return mNoteDateTime;
    }

    public void setmNoteDateTime(String mNoteDateTime) {
        this.mNoteDateTime = mNoteDateTime;
    }

    public String getmNoteContent() {
        return mNoteContent;
    }

    public void setmNoteContent(String mNoteContent) {
        this.mNoteContent = mNoteContent;
    }
}
