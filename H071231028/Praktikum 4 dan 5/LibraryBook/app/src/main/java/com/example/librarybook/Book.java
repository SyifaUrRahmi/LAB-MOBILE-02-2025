package com.example.librarybook;

import android.net.Uri;
import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private String title;
    private String author;
    private String sinopsis;
    private Uri imageUri;

    public Book(String title, String author, String sinopsis, Uri imageUri) {
        this.title = title;
        this.author = author;
        this.sinopsis = sinopsis;
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Book book = (Book) obj;
        return title.equals(book.title) && author.equals(book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }
}
