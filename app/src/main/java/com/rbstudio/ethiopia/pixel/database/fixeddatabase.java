package com.rbstudio.ethiopia.pixel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rbstudio.ethiopia.pixel.object.Fixed;
import com.rbstudio.ethiopia.pixel.object.Fixed;
import com.rbstudio.ethiopia.pixel.object.Loan;

import java.util.ArrayList;
import java.util.List;

public class fixeddatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "fixed";


    public fixeddatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create fixed table
        db.execSQL(Fixed.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Fixed.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(String name,int  price,int year,String description,int number) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Fixed.COLUMN_NAME,name);
        values.put(Fixed.COLUMN_PRICE,price);
        values.put(Fixed.COLUMN_YEAR, year);
        values.put(Fixed.COLUMN_DESCRIPTION, description);
        values.put(Fixed.COLUMN_Number, number);


        // insert row
        long id = db.insert(Fixed.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Fixed getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Fixed.TABLE_NAME,
                new String[]{Fixed.COLUMN_ID, Fixed.COLUMN_NAME, Fixed.COLUMN_TIMESTAMP,Fixed.COLUMN_PRICE,Fixed.COLUMN_Number,Fixed.COLUMN_YEAR,Fixed.COLUMN_DESCRIPTION},
                Fixed.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Fixed note = new Fixed(
                cursor.getInt(cursor.getColumnIndex(Fixed.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Fixed.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Fixed.COLUMN_TIMESTAMP)),
                cursor.getInt(cursor.getColumnIndex(Fixed.COLUMN_PRICE)),
                cursor.getInt(cursor.getColumnIndex(Fixed.COLUMN_YEAR)),
                cursor.getString(cursor.getColumnIndex(Fixed.COLUMN_DESCRIPTION)),
                cursor.getInt(cursor.getColumnIndex(Fixed.COLUMN_Number))
        );

        // close the db connection
        cursor.close();

        return note;
    }

    public List<Fixed> getAllNotes() {
        List<Fixed> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Fixed.TABLE_NAME + " ORDER BY " +
                Fixed.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Fixed note = new Fixed();
                note.setId(cursor.getInt(cursor.getColumnIndex(Fixed.COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(Fixed.COLUMN_NAME)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Fixed.COLUMN_TIMESTAMP)));
                note.setPrice(cursor.getInt(cursor.getColumnIndex(Fixed.COLUMN_PRICE)));
                note.setYear(cursor.getInt(cursor.getColumnIndex(Fixed.COLUMN_YEAR)));
                note.setYear(cursor.getInt(cursor.getColumnIndex(Fixed.COLUMN_Number)));
                note.setDescription(cursor.getString(cursor.getColumnIndex(Fixed.COLUMN_DESCRIPTION)));


                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Fixed.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Fixed note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Fixed.COLUMN_NAME, note.getName());
        values.put(Fixed.COLUMN_PRICE, note.getPrice());
        values.put(Fixed.COLUMN_YEAR, note.getYear());
        values.put(Fixed.COLUMN_DESCRIPTION, note.getDescription());

        // updating row
        return db.update(Fixed.TABLE_NAME, values, Fixed.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Fixed note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Fixed.TABLE_NAME, Fixed.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
    public Cursor getloan(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Fixed.TABLE_NAME  + " ",
                null);
        return res;

    }
    public Cursor getmaterialcustom(String a,String b){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Fixed.TABLE_NAME + " " +
                " WHERE DATE(timeStamp) " +
                " BETWEEN ? AND ?", new String[] {
                a + " 00:00:00", b + " 23:59:59" },null);
        return res;

    }
}