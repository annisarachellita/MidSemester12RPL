package com.example.rplrus06.midsemester12rpl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.widget.LinearLayout;

import com.example.rplrus06.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus06.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;

public class favorite extends AppCompatActivity {
    private MahasiswaHelper mahasiswaHelper;
    private LinearLayoutManager layout;
    RecyclerView recyclerView;
    private ArrayList<MahasiswaModel>models;
    ModelAdapter adapter;
    LinearLayout recyclerviewdata,textdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);


        layout = new LinearLayoutManager(favorite.this);
        recyclerView.setLayoutManager(layout);

        if (models.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            textdata.setVisibility(View.VISIBLE);
        }else{
            recyclerView.setVisibility(View.VISIBLE);
            textdata.setVisibility(View.GONE);
        }
        mahasiswaHelper= new MahasiswaHelper(getApplicationContext());
        mahasiswaHelper.open();

        models = mahasiswaHelper.getAllData();
        Intent i = new Intent(favorite.this,MainActivity.class);
        startActivity(i);


        adapter = new ModelAdapter(getApplicationContext(),models);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recyclerView.setAdapter(adapter);
    }
}
