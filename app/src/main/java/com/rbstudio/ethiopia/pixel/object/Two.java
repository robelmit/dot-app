package com.rbstudio.ethiopia.pixel.object;

public class Two {
    public static final String TABLE_NAME = "rent";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SUPPLY = "supply";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DESCRIPTION = "description";

    private int id;
    private String supply;
    private String timestamp;
    private String description;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_SUPPLY + " INTEGER,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP ,"
                    + COLUMN_DESCRIPTION  + " TEXT"
                    + ")";

    public Two() {
    }

    public Two(int id, String supply, String timestamp) {
        this.id = id;
        this.supply = supply;
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

    public String getSupply() {
        return supply;
    }

    public void setSupply(String supply) {
        this.supply = supply;
    }
}