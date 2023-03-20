package com.rbstudio.ethiopia.pixel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.rbstudio.ethiopia.pixel.object.Loan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class loandatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "loan";


    public loandatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Loan.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Loan.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(String name,int amount,int interest,int payment,String des,int principal) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Loan.COLUMN_NAME,name);
        values.put(Loan.COLUMN_AMOUNT,amount);
        values.put(Loan.COLUMN_INTEREST, interest);
        values.put(Loan.COLUMN_PAYMENT, payment);
        values.put(Loan.COLUMN_DESCRIPTION, des);
        values.put(Loan.COLUMN_PRINCIPAL, principal);

        // insert row
        long id = db.insert(Loan.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Loan getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Loan.TABLE_NAME,
                new String[]{Loan.COLUMN_ID, Loan.COLUMN_NAME, Loan.COLUMN_TIMESTAMP,Loan.COLUMN_AMOUNT,Loan.COLUMN_INTEREST,Loan.COLUMN_PAYMENT,Loan.COLUMN_DESCRIPTION,Loan.COLUMN_PRINCIPAL},
                Loan.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Loan note = new Loan(
                cursor.getInt(cursor.getColumnIndex(Loan.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Loan.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Loan.COLUMN_TIMESTAMP)),
                cursor.getInt(cursor.getColumnIndex(Loan.COLUMN_AMOUNT)),
                cursor.getInt(cursor.getColumnIndex(Loan.COLUMN_INTEREST)),
                cursor.getInt(cursor.getColumnIndex(Loan.COLUMN_PAYMENT)),
                cursor.getString(cursor.getColumnIndex(Loan.COLUMN_DESCRIPTION)),
                cursor.getInt(cursor.getColumnIndex(Loan.COLUMN_PRINCIPAL))

                );

        // close the db connection
        cursor.close();

        return note;
    }

    public Collection<? extends Loan> getAllNotes() {
        List<Loan> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Loan.TABLE_NAME + " ORDER BY " +
                Loan.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Loan note = new Loan();
                note.setId(cursor.getInt(cursor.getColumnIndex(Loan.COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(Loan.COLUMN_NAME)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Loan.COLUMN_TIMESTAMP)));
                note.setAmount(cursor.getInt(cursor.getColumnIndex(Loan.COLUMN_AMOUNT)));
                note.setInterest(cursor.getInt(cursor.getColumnIndex(Loan.COLUMN_INTEREST)));
                note.setPayment(cursor.getInt(cursor.getColumnIndex(Loan.COLUMN_PAYMENT)));
                note.setPrincipal(cursor.getInt(cursor.getColumnIndex(Loan.COLUMN_PRINCIPAL)));


                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Loan.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Loan note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Loan.COLUMN_NAME, note.getName());

        // updating row
        return db.update(Loan.TABLE_NAME, values, Loan.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Loan note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Loan.TABLE_NAME, Loan.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }

    public Cursor getloan(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Loan.TABLE_NAME + " "+"WHERE DATE(timeStamp) >= DATE('now', '-30 day')",
                null);
        return res;

    }
    public Cursor getloancustom(String a,String b){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Loan.TABLE_NAME + " " +
                " WHERE DATE(timeStamp) " +
                " BETWEEN ? AND ?", new String[] {
                a + " 00:00:00", b + " 23:59:59" },null);
        return res;

    }
}