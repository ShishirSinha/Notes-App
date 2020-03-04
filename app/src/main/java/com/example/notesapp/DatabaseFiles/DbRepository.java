package com.example.notesapp.DatabaseFiles;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

public class DbRepository {

    private NotesTableDao NotesDao;
    private static List<NotesTable> allNotesList;
//    private static List<NotesTable> fewNotesList;

    public DbRepository(Application application) {
        NotesDb database = NotesDb.getInstance(application);
        NotesDao = database.notesTableDao();
        // allTrips = TripDao.getAllTrips();
    }

    public void insert(NotesTable note) {
        new InsertNoteAsyncTask(NotesDao).execute(note);
    }

    public void deleteByDateTime(String itemName) {
        new DeleteNoteByDateTimeAsyncTask(NotesDao).execute(itemName);
    }

    public void deleteAllMenuItems() {
        new DeleteAllMenuItemsAsyncTask(NotesDao).execute();
    }

    public List<NotesTable> getAllItems() {
        new SelectAllNotesAsyncTask(NotesDao).execute();
        return allNotesList;
    }

//    public List<NotesTable> getMenuItemByName(String itemname) {
//        new SelectNotesByDateTimeAsyncTask(NotesDao).execute(itemname);
//        return fewNotesList;
//    }



    private static class InsertNoteAsyncTask extends AsyncTask<NotesTable, Void, Void> {
        private NotesTableDao notesDao;

        private InsertNoteAsyncTask(NotesTableDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(NotesTable... note) {
            notesDao.insert(note[0]);
            return null;
        }
    }

    private static class DeleteNoteByDateTimeAsyncTask extends AsyncTask<String, Void, Void> {
        private NotesTableDao notesDao;

        private DeleteNoteByDateTimeAsyncTask(NotesTableDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(String... items) {
            notesDao.deleteByDateTime(items[0]);
            return null;
        }
    }

    private static class DeleteAllMenuItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private NotesTableDao notesDao;

        private DeleteAllMenuItemsAsyncTask(NotesTableDao itemsDao) {
            this.notesDao = itemsDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            notesDao.deleteAllNotes();
            return null;
        }
    }

    private static class SelectAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NotesTableDao notesDao;

        private SelectAllNotesAsyncTask(NotesTableDao notesDao) {
            this.notesDao = notesDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            allNotesList = new ArrayList<>();
            allNotesList = notesDao.getAllItems();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }

//    private static class SelectNotesByDateTimeAsyncTask extends AsyncTask<String, Void, Void> {
//
//        private NotesTableDao notesDao;
//
//        private SelectNotesByDateTimeAsyncTask(NotesTableDao notesDao) {
//            this.notesDao = notesDao;
//        }
//
//        @Override
//        protected Void doInBackground(String... items) {
//            fewNotesList = new ArrayList<>();
//            fewNotesList = notesDao.getNoteByDateTime(items[0]);
//            return null;
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
//
//        }
//    }
}
