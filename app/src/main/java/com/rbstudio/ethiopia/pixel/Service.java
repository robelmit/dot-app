package com.rbstudio.ethiopia.pixel;

public class Service {
    public static final String TABLE_NAME = "serv";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SERVICE = "service";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_EXPENSE = "expense";
    public static final String COLUMN_PAYMENT = "payment";
    public static final String COLUMN_CUSTOMER = "customer";

    private int id;
    private String service;
    private String timestamp;
    private int expense;
    private int payment;
    private String description;
    private String customer;


    // Create table SQL query
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



    public Service() {
    }

    public Service(int id, String service, String timestamp, int expense, int payment, String description,String cust) {
        this.id = id;
        this.service = service;
        this.timestamp = timestamp;
        this.expense = expense;
        this.payment = payment;
        this.description = description;
        this.customer=cust;
    }

    public int getExpense() {
        return expense;
    }

    public int getPayment() {
        return payment;
    }

    public String getService() {
        return service;
    }

    public String getDescription() {
        return description;
    }


    public int getId() {
        return id;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}