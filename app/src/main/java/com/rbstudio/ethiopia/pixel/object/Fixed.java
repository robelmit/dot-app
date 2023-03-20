package com.rbstudio.ethiopia.pixel.object;

public class Fixed {
    public static final String TABLE_NAME = "mat";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_YEAR = "year";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_Number = "number";

    private int id;
    private String name;
    private String timestamp;
    private int price;
    private int year;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    private String description;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP ,"
                    + COLUMN_PRICE  + " INTEGER,"
                    + COLUMN_Number  + " INTEGER,"
                    + COLUMN_YEAR  + " INTEGER,"
                    + COLUMN_DESCRIPTION  + " TEXT"
                    + ")";

    public Fixed() {
    }

    public Fixed(int id, String name, String timestamp, int price, int year, String description,int number) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.price = price;
        this.year = year;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getPrice() {
        return price;
    }

    public int getYear() {
        return year;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}