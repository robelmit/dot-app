package com.rbstudio.ethiopia.pixel.object;

public class One {
    public static final String TABLE_NAME = "rent";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_RENT = "rent";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DESCRIPTION = "description";

    private int id;
    private int rent;
    private String timestamp;
    private String description;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_RENT + " INTEGER,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP ,"
                    + COLUMN_DESCRIPTION  + " TEXT"
                    + ")";

    public One() {
    }

    public One(int id, int rent, String timestamp) {
        this.id = id;
        this.rent = rent;
        this.timestamp = timestamp;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRent() {
        return rent;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}