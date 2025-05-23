package com.example.localsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class BukuHelper {
    private static final String DATABASE_TABLE = DatabaseContract.TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static SQLiteDatabase database;
    private static volatile BukuHelper INSTANCE;

    private BukuHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static BukuHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new BukuHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (database.isOpen()) {
            database.close();
        }
    }

    public Cursor queryAll() {
        return database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                DatabaseContract.BukuColumns._ID + " ASC"
        );
    }

    public Cursor queryById(String id) {
        return database.query(
                DATABASE_TABLE,
                null,
                DatabaseContract.BukuColumns._ID + " = ?",
                new String[]{id},
                null,
                null,
                null,
                null
        );
    }

    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int update(String id, ContentValues values) {
        return database.update(DATABASE_TABLE, values, DatabaseContract.BukuColumns._ID + " = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, DatabaseContract.BukuColumns._ID + " = ?", new String[]{id});
    }

    public ArrayList<Buku> searchByJudul(String keyword) {
        Cursor cursor = database.query(
                DATABASE_TABLE,
                null,
                DatabaseContract.BukuColumns.JUDUL + " LIKE ?",
                new String[]{"%" + keyword + "%"},
                null,
                null,
                DatabaseContract.BukuColumns._ID + " ASC"
        );

        ArrayList<Buku> list = new ArrayList<>();
        if (cursor != null) {
            list = MappingHelper.mapCursorToArrayList(cursor);
            cursor.close();
        }

        return list;
    }

}
