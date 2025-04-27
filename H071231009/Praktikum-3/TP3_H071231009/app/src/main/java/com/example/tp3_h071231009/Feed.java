package com.example.tp3_h071231009;

import androidx.recyclerview.widget.RecyclerView;

import java.util.concurrent.ThreadLocalRandom;

public class Feed  {

    private int imageProfilResId;
    private String username;
    private int imageFotoFeedResId;
    private String usernameCaption;
    private String caption;
    private String tanggal;

    private int jumlahPost, jumlahPengikut, jumlahMengikuti;

    public Feed(int imageProfilResId, String username, int imageFotoFeedResId, String usernameCaption, String caption, String tanggal) {
        this.imageProfilResId = imageProfilResId;
        this.username = username;
        this.imageFotoFeedResId = imageFotoFeedResId;
        this.usernameCaption = usernameCaption;
        this.caption = caption;
        this.tanggal = tanggal;

        this.jumlahPost = ThreadLocalRandom.current().nextInt(1, 100);
        this.jumlahMengikuti = ThreadLocalRandom.current().nextInt(0, 5000);
        this.jumlahPengikut = ThreadLocalRandom.current().nextInt(0, 5000);
    }

    public int getJumlahPost() {

        return jumlahPost;
    }

    public void setJumlahPost(int jumlahPost) {

        this.jumlahPost = jumlahPost;
    }

    public int getJumlahPengikut() {

        return jumlahPengikut;
    }

    public void setJumlahPengikut(int jumlahPengikut) {
        this.jumlahPengikut = jumlahPengikut;
    }

    public int getJumlahMengikuti() {

        return jumlahMengikuti;
    }

    public void setJumlahMengikuti(
            int jumlahMengikuti) {
        this.jumlahMengikuti = jumlahMengikuti;
    }

    public int getImageProfilResId() {

        return imageProfilResId;
    }

    public void setImageProfilResId(int imageProfilResId) {
        this.imageProfilResId = imageProfilResId;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public int getImageFotoFeedResId() {

        return imageFotoFeedResId;
    }

    public void setImageFotoFeedResId(int imageFotoFeedResId) {
        this.imageFotoFeedResId = imageFotoFeedResId;
    }

    public String getUsernameCaption() {

        return usernameCaption;
    }

    public void setUsernameCaption(String usernameCaption) {
        this.usernameCaption = usernameCaption;
    }

    public String getCaption() {

        return caption;
    }

    public void setCaption(String caption) {

        this.caption = caption;
    }

    public String getTanggal() {

        return tanggal;
    }

    public void setTanggal(String tanggal) {

        this.tanggal = tanggal;
    }
}
