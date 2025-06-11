package com.example.librarybook;

import java.util.ArrayList;
import java.util.List;

public class BookManager {

    private static final List<Book> favoriteBooks = new ArrayList<>();

    public static void addFavorite(Book book) {
        if (!favoriteBooks.contains(book)) {
            favoriteBooks.add(book);
        }
    }

    public static void removeFavorite(Book book) {
        favoriteBooks.remove(book);
    }

    public static boolean isFavorite(Book book) {
        return favoriteBooks.contains(book);
    }

    public static List<Book> getFavoriteBooks() {
        return new ArrayList<>(favoriteBooks); // agar tidak bisa dimodifikasi langsung
    }

}
