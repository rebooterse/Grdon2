package com.example.grdon;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistracionActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edName;
    EditText edLastName;
    EditText edPhone;
    EditText edPassword;
    EditText edRepeatePasswords;
    Button registracionButtonAdd;
    DBHelper dbHelper;
    String name;
    String lastName;
    String phone;
    String password;
    String repeatepassword;
   Pattern pattern ;
   Matcher matcher;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registracion_activity);
        edName = findViewById(R.id.registracion_name_label);
        edLastName = findViewById(R.id.registracion_lastname_label);
        edPhone = findViewById(R.id.registracion_phone_number_label);
        edPassword = findViewById(R.id.registracion_password_label);
        edRepeatePasswords = findViewById(R.id.registracion_password_repeat_label);
        registracionButtonAdd = findViewById(R.id.registracion_buttom_add);
        registracionButtonAdd.setOnClickListener(this);
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {
        pattern =Pattern.compile ("[^a-z]",Pattern.CASE_INSENSITIVE);
        name = edName.getText().toString();
        lastName = edLastName.getText().toString();
        phone = edPhone.getText().toString();
        password = edPassword.getText().toString();
        repeatepassword = edRepeatePasswords.getText().toString();

        if (checkOutRegistracionLabels()==1) {
           addContactsInfosDataBase(name,lastName,phone,password);

        }
        Intent intent = new Intent(this,LogIn.class);
        startActivity(intent);

    }
    private int checkOutRegistracionLabels(){

        if (checkOutRegistracionNameLabel()==1){
            if (checkOutRegistracionLastNameLabel() == 1){
                if (checkOutRegistracionPhoneLabel()==1){
                    if (checkOutRegistracionPasswordLabel()==1){
                        if (checkOutRegistracionRepeatPasswordLabel()==1){
                            return 1;
                        }

                    }

                }
            }

        }
       return 0;


    }
    private int checkOutRegistracionNameLabel(){
     if (name.equals("")){
         Toast.makeText(this,"Անվան դաշտը հարկավոր է լրացնել",Toast.LENGTH_LONG).show();
         return 0;
     }
        if (name.length()<3){
            Toast.makeText(this,"Անունը պետք է պարունակի առնվազն 3 տառ",Toast.LENGTH_LONG).show();
            return 0;

        }
     matcher = pattern.matcher(name);
     if(matcher.find()){
         Toast.makeText(this,"Անվան դաշտը չպետք է պարունակի հատուկ նշաններ և թվեր",Toast.LENGTH_LONG).show();
         return 0;

     }
     return 1;

    }
    private int checkOutRegistracionLastNameLabel(){
        if (lastName.equals("")){
            Toast.makeText(this,"Ազգանվան դաշտը հարկավոր է լրացնել",Toast.LENGTH_LONG).show();
            return 0;
        }
        if (lastName.length()<3){
            Toast.makeText(this,"Ազգանունը պետք է պարունակի առնվազն 3 տառ",Toast.LENGTH_LONG).show();
            return 0;

        }
        matcher = pattern.matcher(lastName);
        if(matcher.find()){
            Toast.makeText(this,"Ազգանվան դաշտը չպետք է պարունակի հատուկ նշաններ և թվեր",Toast.LENGTH_LONG).show();
            return 0;

        }
        return 1;

    }
    private int checkOutRegistracionPhoneLabel(){
        if (phone.equals("")){
            Toast.makeText(this,"Հեռաոխասահամարի դաշտը հարկավոր է լրացնել",Toast.LENGTH_LONG).show();
            return 0;
        }
        if (phone.length()<9){
            Toast.makeText(this,"Հեռախոսահամարը պետք է պարունակի 9 թիվ",Toast.LENGTH_LONG).show();
            return 0;

        }
        pattern = Pattern.compile("[^0-9]",Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(phone);
        if(matcher.find()){
            Toast.makeText(this,"Հեռախոսահամարը պետք է պարունակի միայն թվեր",Toast.LENGTH_LONG).show();
            return 0;

        }
        return 1;

    }
    private int checkOutRegistracionPasswordLabel(){
        if (password.equals("")){
            Toast.makeText(this,"Գաղտնտբառի դաշտը հարկավոր է լրացնել",Toast.LENGTH_LONG).show();
            return 0;
        }
        if (password.length()<9){
            Toast.makeText(this,"Գաղտնաբառը պետք է պարունակի առնվազն 9 նիշ",Toast.LENGTH_LONG).show();
            return 0;

        }
        if(password.contains(" ")){
            Toast.makeText(this,"Գաղտնաբառը չպետք է պարունակի բացատ",Toast.LENGTH_LONG).show();
            return 0;

        }
        return 1;

    }
    private int checkOutRegistracionRepeatPasswordLabel(){
        if (repeatepassword.equals("")){
            Toast.makeText(this,"Կրկնեք գաղտնաբառը",Toast.LENGTH_LONG).show();
            return 0;
        }
        if (!password.equals(repeatepassword)){
            Toast.makeText(this,"Գաղտնաբառերը չեն համընկնում",Toast.LENGTH_LONG).show();
            return 0;

        }

        return 1;

    }
    private void addContactsInfosDataBase(String name,String lastName,String phone,String password){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_NAME, name);
        contentValues.put(DBHelper.KEY_LAST_NAME, lastName);
        contentValues.put(DBHelper.KEY_PHONE, phone);
        contentValues.put(DBHelper.KEY_PASSWORD, password);
        sqLiteDatabase.insert(DBHelper.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();


    }
}
