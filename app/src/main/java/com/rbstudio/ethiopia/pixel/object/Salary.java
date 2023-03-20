package com.rbstudio.ethiopia.pixel.object;

public class Salary {
    public static final String TABLE_NAME = "sala";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_SALARY = "salary";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_JOBYPE = "jobtype";
    public static final String COLUMN_Tax = "tax";

    private int id;
    private String name;
    private String timestamp;
    private String sex;
    private int salary;
    private String description;
    private  int tax;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP ,"
                    + COLUMN_SEX  + " TEXT,"
                    + COLUMN_JOBYPE  + " TEXT,"
                    + COLUMN_SALARY  + " INTEGER,"
                    + COLUMN_Tax  + " INTEGER,"
                    + COLUMN_DESCRIPTION  + " TEXT"
                    + ")";

    public Salary() {
    }

    public Salary(int id, String name, String timestamp, String sex, int salary, String description,int tax) {

        this.id = id;
        this.name = name;
        this.timestamp = timestamp;
        this.sex = sex;
        this.salary = salary;
        this.description = description;
        this.tax=tax;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
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

    public String getSex() {
        return sex;
    }

    public int getSalary() {
        return salary;
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

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}