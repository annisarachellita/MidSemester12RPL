package com.example.rplrus06.midsemester12rpl;

import android.widget.ImageView;

public class ItemObject {
    private String Nama;
    private String Gambar;
    private String Deskripsi;
    private String btntrailer;
    private String btnbaru;
    private String tanggal;
    private String id;

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getGambar() {
        return Gambar;
    }

    public void setGambar(String gambar) {
        this.Gambar = gambar;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getBtntrailer() {
        return btntrailer;
    }

    public void setBtntrailer(String btntrailer) {
        this.btntrailer = btntrailer;
    }

    public String getBtnbaru() {
        return btnbaru;
    }

    public void setBtnbaru(String btnbaru) {
        this.btnbaru = btnbaru;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal= tanggal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
