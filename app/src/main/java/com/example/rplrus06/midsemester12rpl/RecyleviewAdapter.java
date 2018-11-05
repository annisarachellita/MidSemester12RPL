package com.example.rplrus06.midsemester12rpl;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyleviewAdapter extends RecyclerView.Adapter<RecyleviewAdapter.MyViewHolder> {
    private List<ItemObject> moviesList;
    Context context;
    String url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView image;
        Button btnlihat, btnbaru;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.person_name);
            image = (ImageView) view.findViewById(R.id.imgholders);
            btnlihat = (Button) view.findViewById(R.id.btnlihat);
            btnbaru = (Button) view.findViewById(R.id.btnbaru);

            btnbaru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent shareintent = new Intent();
                    shareintent.setAction(Intent.ACTION_SEND);
                    shareintent.putExtra(Intent.EXTRA_TEXT, "Your Text Here");
                    shareintent.setType("text/plain");





                }


            });
        }
    }


    public RecyleviewAdapter(List<ItemObject> moviesList, Context context) {
        this.context = context;
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final ItemObject movie = moviesList.get(position);
        holder.title.setText(movie.getNama());


        Glide.with(context).load(url+movie.getGambar()).into(holder.image);
        holder.btnlihat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String Nama = movie.getNama();
                final  String Gambar = url+movie.getGambar();
                final  String Deskripsi = movie.getDeskripsi();

                Intent intent = new Intent(context.getApplicationContext(),detailnama.class);
                intent.putExtra("Nama",Nama);
                intent.putExtra("Gambar",Gambar);
                intent.putExtra("Deskripsi",Deskripsi);
                context.startActivity(intent);
            }
        });
        holder.btnbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareintent = new Intent();
                shareintent.setType("text/plain");
                String shareBody = "Your Body Here";
                String shareSub = "Your Subject Here";
                shareintent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                shareintent.putExtra(Intent.EXTRA_TEXT,shareBody);

                context.startActivity(Intent.createChooser(shareintent,"Share Using"));






            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}