package com.mobil.pronap.cash0.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    public static final int DATABASE_VERSION= 1;

    // Database Name
    public static final String DATABASE_NAME = "cash_db";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Transaction.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("    DROP TABLE IF EXISTS " + Transaction.TABLE_NAME);

        // Create tables again
        onCreate(db);

    }


    public long insertDetail ( String detail,Double price){

        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Transaction.COLUMN_DETAIL,detail);
        values.put(Transaction.COLUMN_PRICE,price);

        // insert row
        long id = db.insert(Transaction.TABLE_NAME,null,values);

        // close db connection
        db.close();
        // return newly inserted row id
        return id;

    }

    public Transaction getTransaction(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Transaction.TABLE_NAME,
                new String[]{Transaction.COLUMN_ID, Transaction.COLUMN_DETAIL,Transaction.COLUMN_PRICE, Transaction.COLUMN_TIMESTAMP},
                Transaction.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare note object
        Transaction transaction = new Transaction(
                cursor.getInt(cursor.getColumnIndex(Transaction.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Transaction.COLUMN_DETAIL)),
                cursor.getString(cursor.getColumnIndex(Transaction.COLUMN_TIMESTAMP)),
                cursor.getDouble(cursor.getColumnIndex(Transaction.COLUMN_PRICE)));

        // close the db connection
        cursor.close();

        return transaction;
    }

    public List<Transaction> getAllDetails() {
        List<Transaction> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Transaction.TABLE_NAME + " ORDER BY " +
                Transaction.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();
                transaction.setId(cursor.getInt(cursor.getColumnIndex(Transaction.COLUMN_ID)));
                transaction.setDetail(cursor.getString(cursor.getColumnIndex(Transaction.COLUMN_DETAIL)));
                transaction.setPrice(cursor.getDouble(cursor.getColumnIndex(Transaction.COLUMN_PRICE)));
                transaction.setTimestamp(cursor.getString(cursor.getColumnIndex(Transaction.COLUMN_TIMESTAMP)));

                notes.add(transaction);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return details list
        return notes;
    }

    public int getDetailsCount() {
        String countQuery = "SELECT  * FROM " + Transaction.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }


}
