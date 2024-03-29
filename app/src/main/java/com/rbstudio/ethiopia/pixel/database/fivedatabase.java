package com.rbstudio.ethiopia.pixel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rbstudio.ethiopia.pixel.object.Five;

import java.util.ArrayList;
import java.util.List;


public class fivedatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "five-db";


    public fivedatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Five.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Five.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(int note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Five.COLUMN_Other, note);

        // insert row
        long id = db.insert(Five.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Five getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Five.TABLE_NAME,
                new String[]{Five.COLUMN_ID, Five.COLUMN_Other, Five.COLUMN_TIMESTAMP},
                Five.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Five note = new Five(
                cursor.getInt(cursor.getColumnIndex(Five.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(Five.COLUMN_Other)),
                cursor.getString(cursor.getColumnIndex(Five.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<Five> getAllNotes() {
        List<Five> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Five.TABLE_NAME + " ORDER BY " +
                Five.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Five note = new Five();
                note.setId(cursor.getInt(cursor.getColumnIndex(Five.COLUMN_ID)));
                note.setOther(cursor.getInt(cursor.getColumnIndex(Five.COLUMN_Other)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Five.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Five.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

   /* public int updateNote(Five note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Five.COLUMN_RENT, note.getRent());

        // updating row
        return db.update(Five.TABLE_NAME, values, Five.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }*/

    public void deleteNote(Five note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Five.TABLE_NAME, Five.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
    public Cursor getanydate(String a,String b){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Five.TABLE_NAME + " " +
                " WHERE DATE(timeStamp) " +
                " BETWEEN ? AND ?", new String[] {
                a + " 00:00:00", b + " 23:59:59" },null);
        return res;

    }
}