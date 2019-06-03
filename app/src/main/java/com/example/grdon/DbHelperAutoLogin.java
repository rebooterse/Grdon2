package com.example.grdon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelperAutoLogin extends SQLiteOpenHelper {
    public static final String DB_NAME = "DB_AUTOLOGINBASA";
    public static final String KEY_PHONE_LOGIN = "DB_LOGIN";
    public static final String KEY_USER_PASSWORD = "DB_PASSWORD";
    public static final String DB_TABLE_NAME = "DB_USERS_TABLE_NAME";
    public static final int DB_VERSION = 1;
    public DbHelperAutoLogin( Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {db.execSQL("create table " + DB_TABLE_NAME +"(" +  KEY_PHONE_LOGIN + " text,"  + KEY_USER_PASSWORD +" text"+ ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + DB_TABLE_NAME);
        onCreate(db);

    }
}
