package com.example.booklibrary;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String description;
    private int tahunTerbit;
    private String genre;
    private String imageUrl;
    private double rating;

    private boolean isFavorite;

    public Book(String title, String author, String description, int tahunTerbit, String genre, String imageUrl, double rating) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.tahunTerbit = tahunTerbit;
        this.genre = genre;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.isFavorite = false; // Default value for isFavorite
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public int getTahunTerbit() {
        return tahunTerbit;
    }
    public void setTahunTerbit(int tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }
    public boolean isFavorite() {
        return isFavorite;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


}
