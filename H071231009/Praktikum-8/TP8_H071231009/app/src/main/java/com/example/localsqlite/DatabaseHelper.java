package com.example.localsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Buku.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_BUKU =
            String.format(
                    "CREATE TABLE %s (" +
                            "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT NOT NULL, " +
                            "%s TEXT, " +
                            "%s TEXT" +
                            ")",
                    DatabaseContract.TABLE_NAME,
                    DatabaseContract.BukuColumns._ID,
                    DatabaseContract.BukuColumns.JUDUL,
                    DatabaseContract.BukuColumns.DESKRIPSI,
                    DatabaseContract.BukuColumns.CREATED_AT,
                    DatabaseContract.BukuColumns.UPDATED_AT
            );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_BUKU);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_NAME);
        onCreate(db);
    }
}
