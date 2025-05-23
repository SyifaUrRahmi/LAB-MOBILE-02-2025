package com.example.localsqlite;

import android.database.Cursor;

import java.util.ArrayList;

public class MappingHelper {
    public static ArrayList<Buku> mapCursorToArrayList(Cursor cursor) {
        ArrayList<Buku> bukus = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseContract.BukuColumns._ID));
            String judul =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.BukuColumns.JUDUL));
            String deskripsi =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.BukuColumns.DESKRIPSI));
            String createAt =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.BukuColumns.CREATED_AT));
            String updateAt =
                    cursor.getString(cursor.getColumnIndexOrThrow(DatabaseContract.BukuColumns.UPDATED_AT));
            bukus.add(new Buku(id, judul,deskripsi,createAt,updateAt));
        }
        return bukus;
    }
}
