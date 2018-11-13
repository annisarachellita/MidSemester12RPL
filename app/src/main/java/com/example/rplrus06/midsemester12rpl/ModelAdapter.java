package com.example.rplrus06.midsemester12rpl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.rplrus06.midsemester12rpl.database.DatabaseHelper;
import com.example.rplrus06.midsemester12rpl.database.MahasiswaHelper;
import com.example.rplrus06.midsemester12rpl.database.MahasiswaModel;

import java.util.ArrayList;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.Holder> {

    public ArrayList<MahasiswaModel> mahasiswaModelArrayList;

    Context context;
    MahasiswaHelper mahasiswaHelper;
    public ModelAdapter(Context context, ArrayList<MahasiswaModel> mahasiswaModelArrayList) {
        this.context = context;
        this.mahasiswaModelArrayList = mahasiswaModelArrayList;
        mahasiswaHelper  = new MahasiswaHelper(context);
    }

    @Override
    public ModelAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.itemlist, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(ModelAdapter.Holder holder, final int position) {
        final MahasiswaModel model = mahasiswaModelArrayList.get(position);

        holder.person_name.setText(mahasiswaModelArrayList.get(position).getName());

        Glide.with(context)
                .load(model.getNim())
                .into(holder.img_buku);

        holder.btnlihat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String Nama = mahasiswaModelArrayList.get(position).getName();

                final String Gambar = mahasiswaModelArrayList.get(position).getNim();
                final String Deskripsi = mahasiswaModelArrayList.get(position).getUrl();
                final String id = String.valueOf(mahasiswaModelArrayList.get(position).getId());
                Log.e("TAG", "onClick: " + Gambar);

                Intent intent = new Intent(context.getApplicationContext(), detailnama.class);
                intent.putExtra("Nama", Nama);
                intent.putExtra("Gambar", Gambar);
                intent.putExtra("Deskripsi", Deskripsi);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Log.e("TAG", "onClick: " + Gambar);
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
        holder.btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mahasiswaHelper.open();
                mahasiswaHelper.beginTransaction();
                mahasiswaHelper.delete(model.getId());
                mahasiswaHelper.setTransactionSuccess();
                mahasiswaHelper.endTransaction();
                mahasiswaHelper.close();
                mahasiswaModelArrayList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,mahasiswaModelArrayList.size());
            }
        });
    }


    public int getItemCount() {
        return mahasiswaModelArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView person_name;
        private TextView person_deskripsi;
        private ImageView img_buku;
        private TextView btnlihat, btnhapus,btnbaru;



        public Holder(View itemView) {
            super(itemView);

            person_name= (TextView) itemView.findViewById(R.id.person_name);
            person_deskripsi= (TextView) itemView.findViewById(R.id.person_deskripsi);

            img_buku = (ImageView) itemView.findViewById(R.id.imgholders);
            btnlihat = (Button) itemView.findViewById(R.id.btnlihat);
            btnhapus = (Button) itemView.findViewById(R.id.btnhapus);
            btnbaru = (Button) itemView.findViewById(R.id.btnbaru);


        }
    }
}
