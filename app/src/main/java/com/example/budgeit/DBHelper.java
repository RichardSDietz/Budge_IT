package com.example.budgeit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Expense.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Expensedetails(month TEXT, year REAL,transType TEXT, amount REAL, description TEXT primary key)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists Expensedetails");
    }

    //Insert Data
    public Boolean insertexpensedata(String month, Integer year, String transType, Float amount, String description)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("month", month);
        contentValues.put("year",year);
        contentValues.put("transType",transType);
        contentValues.put("amount",amount);
        contentValues.put("description",description);
        long result=DB.insert("Expensedetails", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    //Get Data
    public Cursor getdata()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Expensedetails", null);
        return cursor;
    }

}