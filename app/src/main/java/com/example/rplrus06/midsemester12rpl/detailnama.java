package com.example.rplrus06.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rplrus06.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus06.midsemester12rpl.database.MahasiswaModel;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class detailnama extends AppCompatActivity {
    ImageView imgview;
    TextView person_name, person_deskripsi;
    String Nama;
    FloatingActionButton fab;
    boolean flag = true;

    String Deskripsi;
    String img_buku;
    Button btntrailer;
    Button btnbaru;
    MahasiswaHelper mahasiswaHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailnama);
        imgview = findViewById(R.id.imgview);
        person_name = findViewById(R.id.person_name);
        person_deskripsi = findViewById(R.id.person_deskripsi);
        btntrailer = findViewById(R.id.btntrailer);
        fab = findViewById(R.id.fab);
        Intent i = getIntent();
        Bundle bundle = i.getExtras();
        new myfavasyntask().execute();
        btnbaru = findViewById(R.id.btnbaru);
        mahasiswaHelper = new MahasiswaHelper(detailnama.this);


        if (bundle != null) {

            Nama = bundle.getString("Nama");
            Deskripsi = bundle.getString("Deskripsi");
            img_buku = bundle.getString("Gambar");
            person_name.setText(Nama);
            person_deskripsi.setText(Deskripsi);

            Glide.with(getApplicationContext()).load(img_buku).into(imgview);
            btntrailer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent webIntent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://www.youtube.com/watch?v=xLCn88bfW1o"));
                    startActivity(webIntent);
                }
            });
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (flag) {
                        fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_black_24dp));
                        flag = false;
                    } else if (!flag) {
                        fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_border_black_24dp));
                        flag = true;
                    }

                }

            });

        }
    }
        @SuppressLint("StaticFieldLeak")
        public class myfavasyntask extends AsyncTask<Void, Void, Boolean> {


            @Override
            protected void onPreExecute() {

            }


            @Override
            protected Boolean doInBackground(Void... params) {
                Boolean suksesLoad = true;
                mahasiswaHelper.open();
                mahasiswaHelper.beginTransaction();

                MahasiswaModel model  = new MahasiswaModel(Nama,img_buku,Deskripsi);

                mahasiswaHelper.insertTransaction(model);
                mahasiswaHelper.setTransactionSuccess();
                mahasiswaHelper.endTransaction();
                mahasiswaHelper.close();

                return suksesLoad;
            }

            @Override
            protected void onPostExecute(Boolean suksesLoad) {
                fab.setEnabled(false);
                super.onPostExecute(suksesLoad);
            }
        }
    }




