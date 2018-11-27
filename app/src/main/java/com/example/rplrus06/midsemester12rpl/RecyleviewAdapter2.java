package com.example.rplrus06.midsemester12rpl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class RecyleviewAdapter2 extends RecyclerView.Adapter<RecyleviewAdapter2.MyViewHolder> {
    private List<Result> moviesList;
    Context context;
    String url = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description;
        public ImageView image;
        Button btnlihat, btnbaru, btnhapus;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.person_name);
            image = (ImageView) view.findViewById(R.id.imgholders);
            btnlihat = (Button) view.findViewById(R.id.btnlihat);
            btnbaru = (Button) view.findViewById(R.id.btnbaru);
            btnhapus = (Button) view.findViewById(R.id.btnhapus);

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


    public RecyleviewAdapter2(List<Result> moviesList, Context context) {
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
        final Result movie = moviesList.get(position);
        holder.title.setText(movie.getTitle());


        Glide.with(context).load(url + movie.getPosterPath()).into(holder.image);
        holder.btnlihat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String Nama = movie.getTitle();
                final String Gambar = url + movie.getPosterPath();
                final String Deskripsi = movie.getOverview();
                final int id = movie.getId();
                Intent intent = new Intent(context.getApplicationContext(), detailnama.class);
                intent.putExtra("id",id);
                intent.putExtra("Nama", Nama);
                intent.putExtra("Gambar", Gambar);
                intent.putExtra("Deskripsi", Deskripsi);
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
                shareintent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                shareintent.putExtra(Intent.EXTRA_TEXT, shareBody);


                context.startActivity(Intent.createChooser(shareintent, "Share Using"));


            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}