package com.example.grdon;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends AppCompatActivity  {
    Intent intent;
 DBHelper dbHelper;
 DbHelperAutoLogin dbHelperAutoLogin;
 Cursor cursor;
 SQLiteDatabase sqLiteDatabase;
 EditText loginPhone;
 EditText loginPassword;
 Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);
        loginPhone = findViewById(R.id.login_area_for_phone_number);
        loginPassword = findViewById(R.id.login_area_for_password);
        loginButton = findViewById(R.id.button_login);
        dbHelper = new DBHelper(this);
        dbHelperAutoLogin = new DbHelperAutoLogin(this);


    }

    public void registracion(View view) {
        intent = new Intent(this,RegistracionActivity.class);
        startActivity(intent);
    }

    public void loginButton(View view) {
        String login = loginPhone.getText().toString();
        String password = loginPassword.getText().toString();
        sqLiteDatabase = dbHelper.getWritableDatabase();
        cursor = sqLiteDatabase.query(DBHelper.TABLE_NAME,null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int indexPhone = cursor.getColumnIndex(DBHelper.KEY_PHONE);
            if(cursor.getString(indexPhone).equals(login)){
                int indexPassword= cursor.getColumnIndex(DBHelper.KEY_PASSWORD);
                if(cursor.getString(indexPassword).equals(password)){
                    Toast.makeText(this,"Բարի գալուստ",Toast.LENGTH_LONG).show();
                    cursor.close();
                    autuLoginUserInfosAddNewDataBase(login,password);
                    break;
                }
            }
        }
    }

    private void autuLoginUserInfosAddNewDataBase(String login, String password) {
        ContentValues contentValues= new ContentValues();
       sqLiteDatabase = dbHelperAutoLogin.getWritableDatabase();
       contentValues.put(DbHelperAutoLogin.KEY_PHONE_LOGIN,login);
       contentValues.put(DbHelperAutoLogin.KEY_USER_PASSWORD,password);
        sqLiteDatabase.insert(DbHelperAutoLogin.DB_TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
       intent = new Intent(this,Main.class);
       startActivity(intent);


    }
}
