package com.example.rplrus06.midsemester12rpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RecycleviewHolders extends RecyclerView.ViewHolder {
    public TextView person_name;
    public TextView person_deskripsi;
    public ImageView img_buku;
    public TextView btnlihat;


    public RecycleviewHolders(View ItemView) {

        super(ItemView);

        person_name= (TextView) ItemView.findViewById(R.id.person_name);
        person_deskripsi= (TextView) ItemView.findViewById(R.id.person_deskripsi);

        img_buku = (ImageView) ItemView.findViewById(R.id.imgholders);
        btnlihat = (Button) ItemView.findViewById(R.id.btnlihat);

    }
}
