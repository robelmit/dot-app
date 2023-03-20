package com.rbstudio.ethiopia.pixel.object;

public class Three {
    public static final String TABLE_NAME = "rent";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ADVERTIZE = "advert";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DESCRIPTION = "description";

    private int id;
    private int advert;
    private String timestamp;
    private String description;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_ADVERTIZE + " INTEGER,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP ,"
                    + COLUMN_DESCRIPTION  + " TEXT"
                    + ")";

    public Three() {
    }

    public Three(int id, int advert, String timestamp) {
        this.id = id;
        this.advert = advert;
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

    public int getAdvert() {
        return advert;
    }

    public void setAdvert(int advert) {
        this.advert = advert;
    }
}