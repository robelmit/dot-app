package com.rbstudio.ethiopia.pixel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rbstudio.ethiopia.pixel.object.Salary;

import java.util.ArrayList;
import java.util.List;

public class SalaryDatabase extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "salary";


    public SalaryDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(Salary.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Salary.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(String name,String sex,int salary,String descr,String jobtype,int Tax) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Salary.COLUMN_NAME,name);
        values.put(Salary.COLUMN_SEX,sex);
        values.put(Salary.COLUMN_SALARY, salary);
        values.put(Salary.COLUMN_DESCRIPTION, descr);
        values.put(Salary.COLUMN_JOBYPE, jobtype);
        values.put(Salary.COLUMN_Tax, Tax);


        // insert row
        long id = db.insert(Salary.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Salary getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Salary.TABLE_NAME,
                new String[]{Salary.COLUMN_ID, Salary.COLUMN_NAME, Salary.COLUMN_TIMESTAMP,Salary.COLUMN_SEX,Salary.COLUMN_SALARY,Salary.COLUMN_Tax,Salary.COLUMN_DESCRIPTION},
                Salary.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Salary note = new Salary(
                cursor.getInt(cursor.getColumnIndex(Salary.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Salary.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(Salary.COLUMN_TIMESTAMP)),
                cursor.getString(cursor.getColumnIndex(Salary.COLUMN_SEX)),
                cursor.getInt(cursor.getColumnIndex(Salary.COLUMN_SALARY)),
                cursor.getString(cursor.getColumnIndex(Salary.COLUMN_DESCRIPTION)),
                cursor.getInt(cursor.getColumnIndex(Salary.COLUMN_Tax))


                );

        // close the db connection
        cursor.close();

        return note;
    }

    public List<Salary> getAllNotes() {
        List<Salary> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Salary.TABLE_NAME + " ORDER BY " +
                Salary.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Salary note = new Salary();
                note.setId(cursor.getInt(cursor.getColumnIndex(Salary.COLUMN_ID)));
                note.setName(cursor.getString(cursor.getColumnIndex(Salary.COLUMN_NAME)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Salary.COLUMN_TIMESTAMP)));
                note.setSex(cursor.getString(cursor.getColumnIndex(Salary.COLUMN_SEX)));
                note.setSalary(cursor.getInt(cursor.getColumnIndex(Salary.COLUMN_SALARY)));
                note.setTax(cursor.getInt(cursor.getColumnIndex(Salary.COLUMN_Tax)));
                note.setDescription(cursor.getString(cursor.getColumnIndex(Salary.COLUMN_DESCRIPTION)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Salary.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Salary note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Salary.COLUMN_NAME, note.getName());

        // updating row
        return db.update(Salary.TABLE_NAME, values, Salary.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Salary note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Salary.TABLE_NAME, Salary.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
    public Cursor getloan(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Salary.TABLE_NAME + " "+ "WHERE DATE(timeStamp) >= DATE('now', '-30 day')",
                null);
        return res;

    }
    public Cursor getsalarycustom(String a,String b){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Salary.TABLE_NAME + " " +
                " WHERE DATE(timeStamp) " +
                " BETWEEN ? AND ?", new String[] {
                a + " 00:00:00", b + " 23:59:59" },null);
        return res;

    }
    public Cursor getnum(String a,String b){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Salary.TABLE_NAME + " " +
                " WHERE DATE(timeStamp) " +
                " BETWEEN ? AND ?", new String[] {
                a + " 00:00:00", b + " 23:59:59" },null);
        return res;

    }

}