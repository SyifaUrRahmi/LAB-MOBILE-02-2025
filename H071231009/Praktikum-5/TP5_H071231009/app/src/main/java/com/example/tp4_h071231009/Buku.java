package com.example.tp4_h071231009;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Buku implements Parcelable {
    private String judul;
    private String penulis;
    private int tahunTerbit;
    private String blurb;
    private String genre;
    private Double rating;
    private String uriGambar;
    private int gambar;
    private static List<Buku> listSemuaBuku = new ArrayList<>();
    private static List<Buku> listFavorite = new ArrayList<>();
    private static boolean dummyDataAdded = false;
    private boolean isFavorite;

    public Buku(String judul, String penulis, int tahunTerbit, String blurb, String genre, Double rating, String gambarUri) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.blurb = blurb;
        this.genre = genre;
        this.rating = rating;
        this.gambar = 0;
        this.isFavorite = false;
        this.uriGambar = gambarUri;
    }


    public Buku(String judul, String penulis, int tahunTerbit, String blurb, String genre, Double rating, int gambar) {
        this.judul = judul;
        this.penulis = penulis;
        this.tahunTerbit = tahunTerbit;
        this.blurb = blurb;
        this.genre = genre;
        this.rating = rating;
        this.gambar = gambar;
        this.isFavorite = false;
        this.uriGambar = null;
    }


    protected Buku(Parcel in) {
        judul = in.readString();
        penulis = in.readString();
        tahunTerbit = in.readInt();
        blurb = in.readString();
        genre = in.readString();
        if (in.readByte() == 0) {
            rating = null;
        } else {
            rating = in.readDouble();
        }
        gambar = in.readInt();
        isFavorite = in.readByte() != 0;
        uriGambar = in.readString();
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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(penulis);
        dest.writeInt(tahunTerbit);
        dest.writeString(blurb);
        dest.writeString(genre);
        if (rating == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(rating);
        }
        dest.writeInt(gambar);
        dest.writeByte((byte) (isFavorite ? 1 : 0));
        dest.writeString(uriGambar);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public String getBlurb() {
        return blurb;
    }

    public String getGenre() {
        return genre;
    }

    public Double getRating() {
        return rating;
    }

    public int getGambar() {
        return gambar;
    }

    public String getUriGambar() {
        return uriGambar;
    }

    public Uri getUri() {
        if (uriGambar != null) {
            return Uri.parse(uriGambar);
        }
        return null;
    }

    public static List<Buku> getFavoriteList() {
        return new ArrayList<>(listFavorite);
    }

    public static void addFavorite(Buku buku) {
        if (!listFavorite.contains(buku)) {
            listFavorite.add(buku);
            buku.setFavorite(true);
        }
    }

    public static void removeFavorite(Buku buku) {
        listFavorite.remove(buku);
        buku.setFavorite(false);
    }

    public static boolean isFavorite(Buku buku) {
        return listFavorite.contains(buku);
    }

    public static List<Buku> getAllBooks() {
        if (!dummyDataAdded) {
            listSemuaBuku.addAll(BukuData.getListData());
            dummyDataAdded = true;
        }
        return new ArrayList<>(listSemuaBuku);
    }
    public static void addBook(Buku buku) {
        listSemuaBuku.add(0, buku);
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Buku buku = (Buku) o;


        return judul.equals(buku.judul) && penulis.equals(buku.penulis);
    }

    @Override
    public int hashCode() {
        int result = judul.hashCode();
        result = 31 * result + penulis.hashCode();
        return result;
    }
}