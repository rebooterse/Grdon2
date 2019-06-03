package com.example.grdon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ContactsDBASA";
    public static final String TABLE_NAME = "Contacts";
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME= "name";
    public static final String KEY_LAST_NAME= "lastname";
    public static final String KEY_PHONE= "phone";
    public static final String KEY_PASSWORD= "password";


    public DBHelper( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +"(" + KEY_ID + " integer primary key," + KEY_NAME + " text," + KEY_LAST_NAME + " text," + KEY_PHONE
        + " text," + KEY_PASSWORD +" text"+ ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);

    }
}
