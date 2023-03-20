package com.rbstudio.ethiopia.pixel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.rbstudio.ethiopia.pixel.object.Loan;

import org.w3c.dom.NamedNodeMap;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    //ma table
    public static final String TABLE_NAME = "serv";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SERVICE = "service";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_EXPENSE = "expense";
    public static final String COLUMN_PAYMENT = "payment";
    public static final String COLUMN_CUSTOMER = "customer";


    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP ,"
                    + COLUMN_SERVICE  + " TEXT,"
                    + COLUMN_CUSTOMER  + " TEXT,"
                    + COLUMN_DESCRIPTION  + " TEXT,"
                    + COLUMN_EXPENSE  + " INTEGER,"
                    + COLUMN_PAYMENT  + " INTEGER"
                    + ")";

    // Database Name
    private static final String DATABASE_NAME = "pixel";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(CREATE_TABLE);
        db.execSQL(Note.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Note.TABLE_NAME);
        onCreate(db);
    }

    public long insertNote(String service,String des,int expense,int payment,String cust) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
         values.put(Service.COLUMN_SERVICE,service);
         values.put(Service.COLUMN_DESCRIPTION,des);
        values.put(Service.COLUMN_EXPENSE, expense);
        values.put(Service.COLUMN_PAYMENT, payment);
        values.put(Service.COLUMN_CUSTOMER,cust);


        // insert row
        long id = db.insert(Service.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public long insertpro(String product,int  number,int pricebuy,int pricesold,String prodes) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //id` and `timestamp` will be inserted automatically.
        // no need to add them
       values.put(Note.COLUMN_PRODUCT,product);
        values.put(Note.COLUMN_PRONUM,number);
        values.put(Note.COLUMN_PRICEBUY, pricebuy);
        values.put(Note.COLUMN_PRICESOLD, pricesold);
        values.put(Note.COLUMN_DESCRIPTION, prodes);
        // insert row
        long id = db.insert(Note.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }
    public Service getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Service.TABLE_NAME,
                new String[]{Service.COLUMN_ID, Service.COLUMN_SERVICE, Service.COLUMN_TIMESTAMP,Service.COLUMN_EXPENSE,Service.COLUMN_PAYMENT,Service.COLUMN_DESCRIPTION,Service.COLUMN_CUSTOMER},
                Service.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Service note = new Service(
                cursor.getInt(cursor.getColumnIndex(Service.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE)),
                cursor.getString(cursor.getColumnIndex(Service.COLUMN_TIMESTAMP)),
                cursor.getInt(cursor.getColumnIndex(Service.COLUMN_EXPENSE)),
                cursor.getInt(cursor.getColumnIndex(Service.COLUMN_PAYMENT)),
                cursor.getString(cursor.getColumnIndex(Service.COLUMN_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(Service.COLUMN_CUSTOMER))

                );

        // close the db connection
        cursor.close();

        return note;
    }
    public Note getProduct(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Note.TABLE_NAME,
                new String[]{Note.COLUMN_ID, Note.COLUMN_PRODUCT, Note.COLUMN_TIMESTAMP,Note.COLUMN_PRONUM,Note.COLUMN_PRICEBUY,Note.COLUMN_PRICESOLD,Note.COLUMN_DESCRIPTION},
                Note.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Note note = new Note(
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_PRODUCT)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)),
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_PRONUM)),
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_PRICEBUY)),
                cursor.getInt(cursor.getColumnIndex(Note.COLUMN_PRICESOLD)),
                cursor.getString(cursor.getColumnIndex(Note.COLUMN_DESCRIPTION))

        );

        // close the db connection
        cursor.close();

        return note;
    }

   public List<Service> getAllNotes() {
        List<Service> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Service.TABLE_NAME + " ORDER BY " +
                Service.COLUMN_TIMESTAMP + " DESC" +" "+ " WHERE DATE(timeStamp) >= DATE('now') " ;

        SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery("select * from " + Service.TABLE_NAME + " " + " WHERE DATE(timeStamp) >= DATE('now') " + " ORDER BY " +Service.COLUMN_TIMESTAMP + " DESC"  ,
               null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Service note = new Service();
                note.setId(cursor.getInt(cursor.getColumnIndex(Service.COLUMN_ID)));
                note.setService(cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Service.COLUMN_TIMESTAMP)));
                note.setExpense(cursor.getInt(cursor.getColumnIndex(Service.COLUMN_EXPENSE)));
                note.setPayment(cursor.getInt(cursor.getColumnIndex(Service.COLUMN_PAYMENT)));
                note.setCustomer(cursor.getString(cursor.getColumnIndex(Service.COLUMN_CUSTOMER)));
                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }
    public List<Note> getAllNote() {
        List<Note> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Note.TABLE_NAME + " ORDER BY " +
                Note.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
       // Cursor cursor = db.rawQuery(selectQuery, null);
        Cursor cursor = db.rawQuery("select * from " + Note.TABLE_NAME + " " + " WHERE DATE(timeStamp) >= DATE('now') " + " ORDER BY " +Note.COLUMN_TIMESTAMP + " DESC"  ,
                null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setId(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_ID)));
                note.setProduct(cursor.getString(cursor.getColumnIndex(Note.COLUMN_PRODUCT)));
                note.setTimestamp(cursor.getString(cursor.getColumnIndex(Note.COLUMN_TIMESTAMP)));
                note.setPronum(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_PRONUM)));
                note.setPricebuy(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_PRICEBUY)));
                note.setPricesold(cursor.getInt(cursor.getColumnIndex(Note.COLUMN_PRICESOLD)));
                note.setDescription(cursor.getString(cursor.getColumnIndex(Note.COLUMN_DESCRIPTION)));
                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }

    public int getNotesCount() {
        String countQuery = "SELECT  * FROM " + Service.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
    public int getNotesCoun() {
        String countQuery = "SELECT  * FROM " + Note.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int updateNote(Service note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Service.COLUMN_NOTE, note.getNote());

        // updating row
        return db.update(Service.TABLE_NAME, values, Service.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
    }

    public void deleteNote(Service note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Service.TABLE_NAME, Service.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
    public void deleteProduct(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Note.TABLE_NAME, Note.COLUMN_ID + " = ?",
                new String[]{String.valueOf(note.getId())});
        db.close();
    }
    public Cursor getprotoday(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Note.TABLE_NAME + " " + " WHERE DATE(timeStamp) >= DATE('now') "  ,
                null);
        return res;

    }
    public Cursor getproyesterday(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Note.TABLE_NAME + " " + " WHERE DATE(timeStamp) >= DATE('now', '-1 day') "  ,
                null);
        return res;

    }
    public Cursor getproweek(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Note.TABLE_NAME + " " + " WHERE DATE(timeStamp) >= DATE('now', 'weekday 0', '-7 days')"  ,
                null);
        return res;

    }
    public Cursor getpromonth(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Note.TABLE_NAME + " " + " WHERE DATE(timeStamp) >= DATE('now','-30 days') "  ,
                null);
        return res;

    }
    public Cursor getpromonthsum(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select timestamp,id,pronum,pricebuy,pricesold,sum('pronum*(pricesold-pricebuy)') as pronum  from " + Note.TABLE_NAME + " " + " WHERE DATE(timeStamp) >= DATE('now','-30 days') "  ,
                null);
        return res;

    }
    public Cursor getproanydate(String a,String b){
        String ha,hb;
            ha=a+" 00:00:00";
            hb=b+" 00:00:00";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Note.TABLE_NAME + " " +
                " WHERE DATE(timeStamp) " +
                " BETWEEN ? AND ?", new String[] {
                a + " 00:00:00", b + " 23:59:59" },null);
        return res;

    }
    public Cursor getsertoday(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Service.TABLE_NAME + " " + " WHERE DATE(timeStamp) >= DATE('now') "  ,
                null);
        return res;

    }
    public Cursor getserweek(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Service.TABLE_NAME + " " +"WHERE DATE(timeStamp) >= DATE('now', 'weekday 0', '-7 days')",
                null);
        return res;

    }
    public Cursor getsermonth(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Service.TABLE_NAME + " " +" WHERE DATE(timeStamp) >= DATE('now','-30 days') ",
                null);
        return res;

    }
    public Cursor getsermonthsum(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Service.TABLE_NAME + " " +" WHERE DATE(timeStamp) >= DATE('now','-30 days')  GROUP BY date ORDER BY date DESC;",
                null);
        return res;

    }
    public Cursor getseryesterday(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Service.TABLE_NAME + " " +" WHERE DATE(timeStamp) >= DATE('now', '-1 day') ",
                null);
        return res;

    }
    public Cursor getseranydate(String a,String b){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Service.TABLE_NAME + " " +
                        " WHERE DATE(timeStamp) " +
                " BETWEEN ? AND ?", new String[] {
                a + " 00:00:00", b + " 23:59:59" },null);
        return res;

    }
}