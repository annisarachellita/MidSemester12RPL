package com.example.rplrus06.midsemester12rpl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


public class RecyleAdapterRetro extends RecyclerView.Adapter<RecyleAdapterRetro.Holder> {
    private ArrayList<Result> itemObjectArrayList;
    Context context;

    public RecyleAdapterRetro(Context context, ArrayList<Result> itemObjectArrayList) {
        this.context = context;
        this.itemObjectArrayList = itemObjectArrayList;
    }

    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlist, parent, false);
        Holder rcv = new Holder(layoutView);
        return rcv;
    }

    public void onBindViewHolder(final Holder holder, final int position) {
        final Result ItemObject = itemObjectArrayList.get(position);
        Glide.with(context)
                .load(onlyurl.url + ItemObject.getPosterPath())
                .into(holder.img_buku);
        holder.person_name.setText(itemObjectArrayList.get(position).getTitle());
        holder.btnlihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Nama = itemObjectArrayList.get(position).getTitle();
                final String Deskripsi = itemObjectArrayList.get(position).getOverview();
                final String Gambar = onlyurl.url + itemObjectArrayList.get(position).getPosterPath();
                // final String id = idolArrayList.get(position).getId();
                Intent i = new Intent(context.getApplicationContext(), detailnama.class);
                i.putExtra("Nama", Nama);
                i.putExtra("Gambar", Gambar);
                i.putExtra("Deskripsi", Deskripsi);
                //i.putExtra("id",id);
                context.startActivity(i);
            }
        });
        holder.btnbaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my application");
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });
        holder.btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(
                        context);
                builder.setTitle("Delete");
                builder.setCancelable(true);
                builder.setMessage("are you sure to delete this item?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        itemObjectArrayList.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, itemObjectArrayList.size());
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create();
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: " + itemObjectArrayList.size());
        return itemObjectArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        private TextView person_name;
        private TextView person_deskripsi;
        private ImageView img_buku;
        private TextView btnlihat, btnhapus, btnbaru;

        public Holder(View itemView) {
            super(itemView);

            person_name = (TextView) itemView.findViewById(R.id.person_name);
            person_deskripsi = (TextView) itemView.findViewById(R.id.person_deskripsi);

            img_buku = (ImageView) itemView.findViewById(R.id.imgholders);
            btnlihat = (Button) itemView.findViewById(R.id.btnlihat);
            btnhapus = (Button) itemView.findViewById(R.id.btnhapus);
            btnbaru = (Button) itemView.findViewById(R.id.btnbaru);


        }
    }
}




