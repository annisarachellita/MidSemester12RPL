package com.example.rplrus06.midsemester12rpl.database;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by dicoding on 12/6/2016.
 */

public class MahasiswaModel implements Parcelable {
    private String id;
    private String name;
    private String nim;
    private String url;
    private String tanggal;


    public MahasiswaModel(String name, String nim, String url) {
        this.name = name;
        this.nim = nim;
        this.url = url;

    }

    public MahasiswaModel() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }


    public void settanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.nim);
        dest.writeString(this.url);
        dest.writeString(this.tanggal);
    }

    protected MahasiswaModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.nim = in.readString();
        this.url = in.readString();
        this.tanggal = in.readString();
    }

    public static final Creator<MahasiswaModel> CREATOR = new Creator<MahasiswaModel>() {
        @Override
        public MahasiswaModel createFromParcel(Parcel source) {
            return new MahasiswaModel(source);
        }

        @Override
        public MahasiswaModel[] newArray(int size) {
            return new MahasiswaModel[size];
        }
    };
}
