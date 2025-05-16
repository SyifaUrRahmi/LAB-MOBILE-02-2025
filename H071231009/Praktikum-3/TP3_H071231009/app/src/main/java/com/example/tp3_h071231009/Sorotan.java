package com.example.tp3_h071231009;

public class Sorotan {

    private int imageResId;
    private String nama;

    public Sorotan(int imageResId, String nama) {
        this.imageResId = imageResId;
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

}
