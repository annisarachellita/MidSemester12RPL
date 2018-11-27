package com.example.rplrus06.midsemester12rpl;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class control extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        SharedPreferences sharedPreferences = getSharedPreferences("isi", MODE_PRIVATE);
        String user = sharedPreferences.getString("username","");

        if (user.equals("")) {

            Intent intent = new Intent(control.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent = new Intent(control.this, HomeScreen.class);
            startActivity(intent);


            finish();

        }
    }
}

