/*
package com.rbstudio.ethiopia.pixel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rbstudio.ethiopia.pixel.object.About;

import java.util.ArrayList;
import java.util.List;

public class aboutdatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "other_db";


    public aboutdatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(About.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + About.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(String note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(About.COLUMN_NOTE, note);

        // insert row
        long id = db.insert(About.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public About getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(About.TABLE_NAME,
                new String[]{About.COLUMN_ID, About.COLUMN_NOTE, About.COLUMN_TIMESTAMP},
                About.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        About note = new About(
                cursor.getInt(cursor.getColumnIndex(About.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(About.COLUMN_NOTE)),
                cursor.getString(cursor.getColumnIndex(About.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<About> getAllNotes() {
        List<About> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + About.TABLE_NAME + " ORDER BY " +
                About.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                About note = new About();
                note.setId(cursor.getInt(cursor.getColumnIndex(About.COLUMN_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(About.COLUMN_NOTE)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(About.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + About.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(About note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(About.COLUMN_NOTE, note.getNote());

        // updating row
        return db.update(About.TABLE_NAME, values, About.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(About note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(About.TABLE_NAME, About.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
}*/
