package com.rbstudio.ethiopia.pixel.object;

public class Loan {
    public static final String TABLE_NAME = "borrow";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_INTEREST = "interest";
    public static final String COLUMN_PAYMENT = "payment";
    public static final String COLUMN_PRINCIPAL = "principal";
    public static final String COLUMN_DESCRIPTION = "description";

    private int id;
    private String name;
    private String timestamp;
    private int amount;
    private int interest;
    private int payment;
    private int principal;
    private String description;



    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP ,"
                    + COLUMN_AMOUNT  + " INTEGER,"
                    + COLUMN_INTEREST  + " INTEGER,"
                    + COLUMN_PAYMENT  + " INTEGER,"
                    + COLUMN_PRINCIPAL  + " INTEGER,"
                    + COLUMN_DESCRIPTION  + " TEXT"
                    + ")";

    public Loan() {
    }

    public Loan(int id, String name, String timestamp, int amount, int interest, int payment, String description,int principal) {
        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.amount = amount;
        this.interest = interest;
        this.payment = payment;
        this.description = description;
        this.principal=principal;
    }

    public int getPrincipal() {
        return principal;
    }

    public void setPrincipal(int principal) {
        this.principal = principal;
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

    public int getAmount() {
        return amount;
    }

    public int getInterest() {
        return interest;
    }

    public int getPayment() {
        return payment;
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

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setInterest(int interest) {
        this.interest = interest;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}