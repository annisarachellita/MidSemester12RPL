package com.example.rplrus06.midsemester12rpl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.rplrus06.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus06.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;

public class favorite extends AppCompatActivity {
    MahasiswaHelper mahasiswaHelper;
    RecyclerView recyclerView;
    ArrayList<MahasiswaModel> mahasiswaModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mahasiswaHelper = new MahasiswaHelper(this);
        mahasiswaModelArrayList = new ArrayList<MahasiswaModel>();
        mahasiswaModelArrayList = mahasiswaHelper.getAllData();

        recyclerView = findViewById(R.id.recycleviewdata);

        ModelAdapter adapter = new ModelAdapter(getApplicationContext(), mahasiswaModelArrayList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
