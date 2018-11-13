package com.example.rplrus06.midsemester12rpl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Home extends AppCompatActivity {
    public ArrayList<ItemObject> itemObjectArrayList = new ArrayList<>();
    RecyclerView recyclerView;
    ItemObject itemObject;


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                moveTaskToBack(true);
                SharedPreferences sharedPreferences = getSharedPreferences("isi", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
                Intent intent = new Intent(Home.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            case R.id.favorite:
                Intent intent1 = new Intent(Home.this, favorite.class);
                startActivity(intent1);
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        recyclerView = findViewById(R.id.recyclerview);
        //  prepareMovieData();
        new LogData().execute();
        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));
        RecyleviewAdapter adapter = new RecyleviewAdapter(itemObjectArrayList, Home.this);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("StaticFieldLeak")
    public class LogData extends AsyncTask<Void, Void, JSONObject> {

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject;
            try {
                String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=6dbf9720c5c718413528f2d495965a3f";
                System.out.println(url);
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(url);
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                InputStream inputStream = httpEntity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        inputStream, "iso-8859-1"
                ), 8);
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                inputStream.close();
                String json = stringBuilder.toString();
                jsonObject = new JSONObject(json);
            } catch (Exception e) {
                jsonObject = null;
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            Log.d("Hasiljson", "onPostExecute:" + jsonObject.toString());

            if (jsonObject != null)
                try {
                    itemObjectArrayList = new ArrayList<>();
                    JSONArray aktualData = jsonObject.getJSONArray("results");
                    for (int x = 0; x < aktualData.length(); x++) {
                        ItemObject itemObject = new ItemObject();
                        itemObject.setId(aktualData.getJSONObject(x).getString("id"));
                        itemObject.setNama(aktualData.getJSONObject(x).getString("title"));
                        itemObject.setGambar(aktualData.getJSONObject(x).getString("poster_path"));
                        itemObject.setDeskripsi(aktualData.getJSONObject(x).getString("overview"));
                        itemObject.setTanggal(aktualData.getJSONObject(x).getString("release_date"));
                        itemObjectArrayList.add(itemObject);
                    }
                    RecyleviewAdapter adapter = new RecyleviewAdapter(itemObjectArrayList,Home.this);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setNestedScrollingEnabled(false);
                    recyclerView.setVisibility(View.VISIBLE);
                    recyclerView.setAdapter(adapter);
                } catch (Exception ignored) {
                    Log.d("lalaku", "onPostExecute: " + ignored.toString());
                }
            else {
            }
            super.onPostExecute(jsonObject);
        }
    }
//    private void prepareMovieData() {
//        ItemObject movie1 = new ItemObject();
//        movie1.setNama("Shaun The Sheep");
//        movie1.setGambar("https://vignette.wikia.nocookie.net/shaunthesheep/images/1/12/TimmyTime.jpg/revision/latest?cb=20111031054904");
//        movie1.setDeskripsi("Shaun The Sheep adalah kartun anak anak yang sangat lucu,kartun ini biasa tayang di tv yaitu mnctv tayang setiap hari. shaun the sheep juga menjadi tayangan anak anak yang sangat disukai. Shaun The Sheep juga merupakan tayangan yang bagus");
//        itemObjectArrayList.add(movie1);
//
//        ItemObject movie2 = new ItemObject();
//        movie2.setNama("Danur");
//        movie2.setGambar("https://vignette.wikia.nocookie.net/shaunthesheep/images/1/12/TimmyTime.jpg/revision/latest?cb=20111031054904");
//        movie2.setDeskripsi("Danur adalah film horor yang sangat menakutkan,film ini tayang di bioskop kesayangan anda. danur juga menjadi tayangan remaja yang sangat disukai. danur juga merupakan tayangan yang bagus");
//
//        itemObjectArrayList.add(movie2);
//
//        ItemObject movie3 = new ItemObject();
//        movie3.setNama("LLS 2");
//        movie3.setGambar("https://statik.tempo.co/data/2016/06/10/id_514127/514127_620.jpg");
//        movie3.setDeskripsi("LLS 2 adalah film remaja yang sangat romantis,film ini biasa tayang di bioskop kesayangan anda. lls2 juga menjadi tayangan remaja yang sangat disukai. London Love Story 2 juga merupakan tayangan yang bagus");
//        itemObjectArrayList.add(movie3);
//
//        ItemObject movie4 = new ItemObject();
//        movie4.setNama("Paris In Love");
//        movie4.setGambar("https://vignette.wikia.nocookie.net/shaunthesheep/images/1/12/TimmyTime.jpg/revision/latest?cb=20111031054904");
//        movie4.setDeskripsi("Paris In Love adalah film remaja yang sangat romantis,film ini biasa tayang di tv yaitu sctv tayang setiap hari. Paris In Love juga menjadi tayangan remaja yang sangat disukai. Paris In Love juga merupakan tayangan yang bagus");
//        itemObjectArrayList.add(movie4);
//
//        ItemObject movie5 = new ItemObject();
//        movie5.setNama("Spongebob");
//        movie5.setGambar("https://vignette.wikia.nocookie.net/shaunthesheep/images/1/12/TimmyTime.jpg/revision/latest?cb=20111031054904");
//        movie5.setDeskripsi("Spongebob adalah kartun anak anak yang sangat lucu,kartun ini biasa tayang di tv yaitu globaltv tayang setiap hari. shaun the sheep juga menjadi tayangan anak anak yang sangat disukai. Spongebob juga merupakan tayangan yang bagus");
//        itemObjectArrayList.add(movie5);
}

//}









