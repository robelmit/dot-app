package com.rbstudio.ethiopia.pixel;

public class Note {
    public static final String TABLE_NAME = "product";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCT = "product";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_PRONUM = "pronum";
    public static final String COLUMN_PRICEBUY = "pricebuy";
    public static final String COLUMN_PRICESOLD = "pricesold";
    public static final String COLUMN_DESCRIPTION = "description";


    private int id;
    private String product;
    private String timestamp;
    private int pronum;
    private int pricebuy;
    private int pricesold;
    private String description;
    private String customer;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCT + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP ,"
                    + COLUMN_PRONUM  + " INTEGER , "
                    + COLUMN_PRICEBUY  + " INTEGER,"
                    + COLUMN_PRICESOLD  + " INTEGER , "
                    + COLUMN_DESCRIPTION  + " TEXT"
                    + ")";

    public Note() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getProduct() {
        return product;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getPronum() {
        return pronum;
    }

    public int getPricebuy() {
        return pricebuy;
    }

    public int getPricesold() {
        return pricesold;
    }

    public String getDescription() {
        return description;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setPronum(int pronum) {
        this.pronum = pronum;
    }

    public void setPricebuy(int pricebuy) {
        this.pricebuy = pricebuy;
    }

    public void setPricesold(int pricesold) {
        this.pricesold = pricesold;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Note(int id, String product, String timestamp, int pronum, int pricebuy, int pricesold, String description) {
        this.id = id;
        this.product = product;
        this.timestamp = timestamp;
        this.pronum = pronum;
        this.pricebuy = pricebuy;
        this.pricesold = pricesold;
        this.description = description;
    }

}