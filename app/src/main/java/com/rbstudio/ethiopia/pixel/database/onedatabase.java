package com.rbstudio.ethiopia.pixel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rbstudio.ethiopia.pixel.object.One;

import java.util.ArrayList;
import java.util.List;


public class onedatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "one-db";


    public onedatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(One.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + One.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(int note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(One.COLUMN_RENT, note);

        // insert row
        long id = db.insert(One.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public One getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(One.TABLE_NAME,
                new String[]{One.COLUMN_ID, One.COLUMN_RENT, One.COLUMN_TIMESTAMP},
                One.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        One note = new One(
                cursor.getInt(cursor.getColumnIndex(One.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(One.COLUMN_RENT)),
                cursor.getString(cursor.getColumnIndex(One.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<One> getAllNotes() {
        List<One> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + One.TABLE_NAME + " ORDER BY " +
                One.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                One note = new One();
                note.setId(cursor.getInt(cursor.getColumnIndex(One.COLUMN_ID)));
                note.setRent(cursor.getInt(cursor.getColumnIndex(One.COLUMN_RENT)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(One.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + One.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(One note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(One.COLUMN_RENT, note.getRent());

        // updating row
        return db.update(One.TABLE_NAME, values, One.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(One note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(One.TABLE_NAME, One.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
    public Cursor getanydate(String a,String b){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + One.TABLE_NAME + " " +
                " WHERE DATE(timeStamp) " +
                " BETWEEN ? AND ?", new String[] {
                a + " 00:00:00", b + " 23:59:59" },null);
        return res;

    }
}