package com.rbstudio.ethiopia.pixel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rbstudio.ethiopia.pixel.object.Four;

import java.util.ArrayList;
import java.util.List;


public class fourdatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "four-db";


    public fourdatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Four.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Four.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(int note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Four.COLUMN_PAYABLE, note);

        // insert row
        long id = db.insert(Four.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Four getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Four.TABLE_NAME,
                new String[]{Four.COLUMN_ID, Four.COLUMN_PAYABLE, Four.COLUMN_TIMESTAMP},
                Four.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Four note = new Four(
                cursor.getInt(cursor.getColumnIndex(Four.COLUMN_ID)),
                cursor.getInt(cursor.getColumnIndex(Four.COLUMN_PAYABLE)),
                cursor.getString(cursor.getColumnIndex(Four.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<Four> getAllNotes() {
        List<Four> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Four.TABLE_NAME + " ORDER BY " +
                Four.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Four note = new Four();
                note.setId(cursor.getInt(cursor.getColumnIndex(Four.COLUMN_ID)));
                note.setPayable(cursor.getInt(cursor.getColumnIndex(Four.COLUMN_PAYABLE)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Four.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Four.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

 /*   public int updateNote(Four note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Four.COLUMN_PAYABLE, note.getPayable());

        // updating row
        return db.update(Four.TABLE_NAME, values, Four.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }*/

    public void deleteNote(Four note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Four.TABLE_NAME, Four.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
    public Cursor getanydate(String a,String b){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Four.TABLE_NAME + " " +
                " WHERE DATE(timeStamp) " +
                " BETWEEN ? AND ?", new String[] {
                a + " 00:00:00", b + " 23:59:59" },null);
        return res;

    }
}