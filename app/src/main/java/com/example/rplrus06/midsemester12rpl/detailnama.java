package com.example.rplrus06.midsemester12rpl;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rplrus06.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus06.midsemester12rpl.database.MahasiswaModel;

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
    private String id;

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
//        new myfavasyntask().execute();
        btnbaru = findViewById(R.id.btnbaru);
        mahasiswaHelper = new MahasiswaHelper(detailnama.this);


        if (bundle != null) {
            id = bundle.getString("id");
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
//                    Toast.makeText(getApplicationContext(), "Sukses Favorite", Toast.LENGTH_SHORT).show();

                    if (flag) {
                        MahasiswaModel mahasiswaModel = new MahasiswaModel(Nama,img_buku, Deskripsi);
                        mahasiswaHelper.insertTransaction(mahasiswaModel);
                        Toast.makeText(getApplicationContext(), "Sukses Favorite", Toast.LENGTH_SHORT).show();
                        fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_black_24dp));
                        flag = false;
                    } else if (!flag) {
                        int a = mahasiswaHelper.delete(Nama);
                        fab.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_favorite_border_black_24dp));
                        flag = true;
                    }

                }

            });

        }
    }
}

//     @SuppressLint("StaticFieldLeak")
//     public class myfavasyntask extends AsyncTask<Void, Void, Boolean> {
//
//
//            @Override
//            protected void onPreExecute() {
//
//            }
//
//
//            @Override
//            protected Boolean doInBackground(Void... params) {
//                Boolean suksesLoad = true;
//                mahasiswaHelper.open();
//                mahasiswaHelper.beginTransaction();
//
//                MahasiswaModel model  = new MahasiswaModel(Nama,img_buku,Deskripsi);
//
//                mahasiswaHelper.insertTransaction(model);
//                mahasiswaHelper.setTransactionSuccess();
//                mahasiswaHelper.endTransaction();
//                mahasiswaHelper.close();
////                Snackbar.make(View,"tersimpan",Toast.LENGTH_SHORT).setAction("save")
//
//                return suksesLoad;
//            }
//
//            @Override
//
//            protected void onPostExecute(Boolean suksesLoad) {
//                //fab.setEnabled(false);
//                super.onPostExecute(suksesLoad);
//            }
//        }
//    }




