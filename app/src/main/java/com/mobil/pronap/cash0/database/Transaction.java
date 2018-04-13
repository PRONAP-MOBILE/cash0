package com.mobil.pronap.cash0.database;

public class Transaction {
    private int id;
    private String detail;
    private String timestamp;
    private Double price;

    public static final String TABLE_NAME = "transactions";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DETAIL = "detail";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_PRICE = "price";


    // Create table SQL query
    public static final String CREATE_TABLE = " CREATE TABLE " + TABLE_NAME + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DETAIL + "TEXT,"
                    + COLUMN_PRICE + "NUMBER,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Transaction(int id, String detail, String timestamp, Double price) {
        this.id = id;
        this.detail = detail;
        this.timestamp = timestamp;
        this.price = price;
    }

    public Transaction(){

    }
}
