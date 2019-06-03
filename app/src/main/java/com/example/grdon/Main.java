package com.example.grdon;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Currency;

public class Main extends AppCompatActivity {
    public static String USER_NAME;
Intent intent;
Cursor cursor;
Cursor cursor1;
SQLiteDatabase sqLiteDatabase;
SQLiteDatabase sqLiteDatabase1;
DBHelper dbHelper;
DbHelperAutoLogin dbHelperAutoLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        dbHelper = new DBHelper(this);
        dbHelperAutoLogin = new DbHelperAutoLogin(this);
        checkInLogin();
    }

    private void checkInLogin() {

        String login = "";
        String password = "";
        sqLiteDatabase = dbHelperAutoLogin.getWritableDatabase();
        cursor = sqLiteDatabase.query(DbHelperAutoLogin.DB_TABLE_NAME, null, null, null, null, null, null);


            while (cursor.moveToNext()) {

                int indexLogin = cursor.getColumnIndex(DbHelperAutoLogin.KEY_PHONE_LOGIN);
                int indexPasswordUser = cursor.getColumnIndex(DbHelperAutoLogin.KEY_USER_PASSWORD);
                login = cursor.getString(indexLogin);
                password = cursor.getString(indexPasswordUser);

                break;
            }

        sqLiteDatabase.close();

        sqLiteDatabase1 = dbHelper.getWritableDatabase();
        cursor1 = sqLiteDatabase1.query(DBHelper.TABLE_NAME, null, null, null, null, null, null);


            while (cursor1.moveToNext()) {

                int indexPhone = cursor1.getColumnIndex(DBHelper.KEY_PHONE);
                if (cursor1.getString(indexPhone).equals(login)) {
                    Toast.makeText(this, "fffffff", Toast.LENGTH_LONG).show();
                    int indexPassword = cursor1.getColumnIndex(DBHelper.KEY_PASSWORD);
                    if (cursor1.getString(indexPassword).equals(password)) {
                        int indexUserName = cursor1.getColumnIndex(DBHelper.KEY_NAME);
                        USER_NAME = cursor1.getString(indexUserName);
                        sqLiteDatabase1.close();
                        cursor1.close();
                        intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                } else {

                    intent = new Intent(this, LogIn.class);
                    startActivity(intent);
                }
            }


    }
    }

