package com.rbstudio.ethiopia.pixel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rbstudio.ethiopia.pixel.object.Two;
import java.util.ArrayList;
import java.util.List;


public class twodatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "two-db";


    public twodatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Two.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Two.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(int note) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Two.COLUMN_SUPPLY, note);

        // insert row
        long id = db.insert(Two.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Two getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Two.TABLE_NAME,
                new String[]{Two.COLUMN_ID, Two.COLUMN_SUPPLY, Two.COLUMN_TIMESTAMP},
                Two.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Two note = new Two(
                cursor.getInt(cursor.getColumnIndex(Two.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Two.COLUMN_SUPPLY)),
                cursor.getString(cursor.getColumnIndex(Two.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return note;
    }

    public List<Two> getAllNotes() {
        List<Two> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Two.TABLE_NAME + " ORDER BY " +
                Two.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Two note = new Two();
                note.setId(cursor.getInt(cursor.getColumnIndex(Two.COLUMN_ID)));
                note.setSupply(cursor.getString(cursor.getColumnIndex(Two.COLUMN_SUPPLY)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Two.COLUMN_TIMESTAMP)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Two.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

   /* public int updateNote(Two note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Two.COLUMN_RENT, note.getRent());

        // updating row
        return db.update(Two.TABLE_NAME, values, Two.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }*/

    public void deleteNote(Two note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Two.TABLE_NAME, Two.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
    public Cursor getanydate(String a,String b){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Two.TABLE_NAME + " " +
                " WHERE DATE(timeStamp) " +
                " BETWEEN ? AND ?", new String[] {
                a + " 00:00:00", b + " 23:59:59" },null);
        return res;

    }
}