package com.example.rplrus06.midsemester12rpl.database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;


import java.util.ArrayList;
import java.util.jar.Attributes;

import static android.provider.BaseColumns._ID;
import static com.example.rplrus06.midsemester12rpl.database.DatabaseContract.MahasiswaColumns.NAMA;
import static com.example.rplrus06.midsemester12rpl.database.DatabaseContract.MahasiswaColumns.NIM;
import static com.example.rplrus06.midsemester12rpl.database.DatabaseContract.MahasiswaColumns.TANGGAL;
import static com.example.rplrus06.midsemester12rpl.database.DatabaseContract.MahasiswaColumns.URL;
import static com.example.rplrus06.midsemester12rpl.database.DatabaseContract.TABLE_NAME;

/**
 * Created by dicoding on 12/1/2016.
 */

public class MahasiswaHelper {

    private Context context;
    private DatabaseHelper dataBaseHelper;
    String Nama;
    String Gambar;
    String Deskripsi;

    private SQLiteDatabase database;

    public MahasiswaHelper(Context context) {
        dataBaseHelper = new DatabaseHelper(context);
    }

    public MahasiswaHelper open() throws SQLException {
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dataBaseHelper.close();
    }

    /**
     * Gunakan method ini untuk cari NIM berdasarkan nama mahasiswa
     *
     * @param nama nama yang dicari
     * @return NIM dari mahasiswa
     */
    public ArrayList<MahasiswaModel> getDataByNama(String nama) {
        String result = "";
        Cursor cursor = database.query(TABLE_NAME, null, NAMA + " LIKE ?", new String[]{nama + "%"}, null, null, _ID + " ASC", null);
        cursor.moveToFirst();
        ArrayList<MahasiswaModel> arrayList = new ArrayList<>();
        MahasiswaModel mahasiswaModel;
        if (cursor.getCount() > 0) {
            do {
                // mahasiswaModel = new MahasiswaModel(_ID, Nama, Gambar, Deskripsi);
                //mahasiswaModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
//                mahasiswaModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
//                mahasiswaModel.setNim(cursor.getString(cursor.getColumnIndexOrThrow(NIM)));
//                mahasiswaModel.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(URL)));
//                mahasiswaModel.settanggal(cursor.getString(cursor1.getColumnIndexOrThrow(TANGGAL)));

//                arrayList.add(mahasiswaModel);
//                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    /**
     * Gunakan method ini untuk mendapatkan semua data mahasiswa
     *
     * @return hasil query mahasiswa model di dalam arraylist
     */
    public ArrayList<MahasiswaModel> getAllData() {
        database = dataBaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from " + TABLE_NAME, null);

        cursor.moveToFirst();
        ArrayList<MahasiswaModel> arrayList = new ArrayList<>();
        MahasiswaModel dataModel;
        if (cursor.getCount() > 0) {
            do {
                dataModel = new MahasiswaModel();
                dataModel.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAMA)));
                dataModel.setNim(cursor.getString(cursor.getColumnIndexOrThrow(NIM)));
                dataModel.setUrl(cursor.getString(cursor.getColumnIndexOrThrow(URL)));
                arrayList.add(dataModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    /**
     * Gunakan method ini untuk memulai sesi query transaction
     */
    public void beginTransaction() {
        database.beginTransaction();
    }

    /**
     * Gunakan method ini jika query transaction berhasil, jika error jangan panggil method ini
     */
    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    /**
     * Gunakan method ini untuk mengakhiri sesi query transaction
     */
    public void endTransaction() {
        database.endTransaction();
    }

    /**
     * Gunakan method ini untuk query insert di dalam transaction
     *
     * @param mahasiswaModel inputan model mahasiswa
     */
    public void insertTransaction(MahasiswaModel mahasiswaModel) {
        database = dataBaseHelper.getWritableDatabase();
        String sql = "INSERT INTO " + TABLE_NAME + " (" + NAMA + ", " + NIM + ", " + URL + ") VALUES (?,?,?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, mahasiswaModel.getName());
        stmt.bindString(2, mahasiswaModel.getNim());
        stmt.bindString(3, mahasiswaModel.getUrl());
        stmt.execute();
        stmt.clearBindings();
        Log.d("sukses", "insertTransaction: ");
    }

    public int delete(String id) {
        database = dataBaseHelper.getWritableDatabase();
        String[] whereArgs = {id};

        int count = database.delete(TABLE_NAME, _ID + " = ?", whereArgs);
        return count;
    }

    public void insertTransaction() {
    }
}

