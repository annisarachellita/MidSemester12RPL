package com.example.rplrus06.midsemester12rpl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rplrus06.midsemester12rpl.database.MahasiswaHelper;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class twoFragment extends Fragment {
    public ArrayList<Result> itemObjectArrayList=new ArrayList<>();
    public RecyclerView recyclerView;
//    MahasiswaHelper mahasiswaHelper;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_two, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.Rview_upcoming);
        //mahasiswaHelper = new MahasiswaHelper(view.getContext());
        load_data_upcoming();
        return view;
    }

    private void load_data_upcoming() {
        json_api service = retrofit.getRetrofitInstance().create(json_api.class);
        Call<JsonRespons> call = service.getJsonNowPlaying();
        call.enqueue(new Callback<JsonRespons>() {
            @Override
            public void onResponse(Call<JsonRespons> call, Response<JsonRespons> response) {
                JsonRespons jsonRespons = response.body();
                itemObjectArrayList = new ArrayList<>(Arrays.asList(jsonRespons.getResults()));
                Log.d("responku", "onResponse: " + jsonRespons.getResults());
                RecyleAdapterRetro adapter = new RecyleAdapterRetro(view.getContext(), itemObjectArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<JsonRespons> call, Throwable t) {
                Log.e("gagal", "onFailure: "+t.getMessage() );
            }
        });
    }


}