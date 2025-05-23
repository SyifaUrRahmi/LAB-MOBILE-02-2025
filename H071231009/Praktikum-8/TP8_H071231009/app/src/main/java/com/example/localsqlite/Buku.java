package com.example.localsqlite;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Buku implements Parcelable {
    private int id;
    private String judul, deskripsi;
    private String createdAt;
    private String updatedAt;

    public Buku() {
    }

    public Buku(int id, String judul, String deskripsi, String createdAt, String updatedAt) {
        this.id = id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected Buku(Parcel in) {
        id = in.readInt();
        judul = in.readString();
        deskripsi = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<Buku> CREATOR = new Creator<Buku>() {
        @Override
        public Buku createFromParcel(Parcel in) {
            return new Buku(in);
        }

        @Override
        public Buku[] newArray(int size) {
            return new Buku[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(judul);
        parcel.writeString(deskripsi);
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
    }
}
