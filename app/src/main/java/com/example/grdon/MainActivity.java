package com.example.grdon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = findViewById(R.id.user_name);
        userName.setText("Barev Aziz jan,qo anunn e " + Main.USER_NAME);
    }

    public void buttonGo(View view) {
        Intent intent = new Intent(this,LogIn.class);
        startActivity(intent);
    }
}
