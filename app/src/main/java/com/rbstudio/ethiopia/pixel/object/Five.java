package com.rbstudio.ethiopia.pixel.object;

public class Five {
    public static final String TABLE_NAME = "rent";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_Other = "other";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DESCRIPTION = "description";

    private int id;
    private int other;
    private String timestamp;
    private String description;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_Other + " INTEGER,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP ,"
                    + COLUMN_DESCRIPTION  + " TEXT"
                    + ")";

    public Five() {
    }

    public Five(int id, int other, String timestamp) {
        this.id = id;
        this.other = other;
        this.timestamp = timestamp;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getOther() {
        return other;
    }

    public void setOther(int other) {
        this.other = other;
    }
}