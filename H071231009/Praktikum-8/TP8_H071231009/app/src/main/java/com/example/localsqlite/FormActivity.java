package com.example.localsqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.Locale;

public class FormActivity extends AppCompatActivity {

    public static final String EXTRA_BUKU = "extra_buku";
    public static final int RESULT_ADD = 101;
    public static final int RESULT_UPDATE = 201;
    public static final int RESULT_DELETE = 301;
    public static final int REQUEST_UPDATE = 200;

    private BukuHelper bukuHelper;
    private Buku buku;
    private TextView judul2;
    private EditText etJudul, etDeskripsi;
    private boolean isEdit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        judul2 = findViewById(R.id.judul);
        etJudul = findViewById(R.id.et_judul);
        etDeskripsi = findViewById(R.id.et_deskripsi);
        Button btnSave = findViewById(R.id.btn_save);
        ImageButton btnDelete = findViewById(R.id.btn_delete);
        ImageButton btnKembali = findViewById(R.id.kembali);

        btnKembali.setOnClickListener(v -> {
            String message = isEdit
                    ? "Apakah Anda ingin membatalkan perubahan pada form?"
                    : "Apakah Anda ingin membatalkan penambahan pada form?";

            new androidx.appcompat.app.AlertDialog.Builder(FormActivity.this)
                    .setTitle("Batal")
                    .setMessage(message)
                    .setPositiveButton("Ya", (dialog, which) -> finish())
                    .setNegativeButton("Tidak", null)
                    .show();
        });


        bukuHelper = BukuHelper.getInstance(getApplicationContext());
        bukuHelper.open();

        buku = getIntent().getParcelableExtra(EXTRA_BUKU);

        if (buku != null) {
            isEdit = true;
        } else {
            buku = new Buku();
        }

        String actionBarTitle;
        String buttonTitle;

        if (isEdit) {
            judul2.setText("Edit");
            actionBarTitle = "Edit Buku";
            buttonTitle = "Update";

            etJudul.setText(buku.getJudul());
            etDeskripsi.setText(buku.getDeskripsi());

            btnDelete.setVisibility(View.VISIBLE);
        } else {
            judul2.setText("Tambah");
            actionBarTitle = "Add Buku";
            buttonTitle = "Save";
            btnDelete.setVisibility(View.GONE);
        }

        btnSave.setText(buttonTitle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnSave.setOnClickListener(view -> saveBuku());
        btnDelete.setOnClickListener(v -> {
           String massege2 = "Apakah Anda yakin ingin menghapus item ini?";

           new AlertDialog.Builder(FormActivity.this)
                   .setTitle("Hapus")
                   .setMessage(massege2)
                   .setPositiveButton("Ya", (dialog, which) -> deleteBuku())
                   .setNegativeButton("Tidak", null)
                   .show();
        });
    }

    private void saveBuku() {
        String judul = etJudul.getText().toString().trim();
        String deskripsi = etDeskripsi.getText().toString().trim();

        if (judul.isEmpty()) {
            etJudul.setError("Please fill this field");
            return;
        }

        if (deskripsi.isEmpty()) {
            etDeskripsi.setError("Please fill this field");
            return;
        }

        String currentTime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).format(new Date());
        Log.d("TanggalDebug", currentTime);

        ContentValues values = new ContentValues();
        values.put(DatabaseContract.BukuColumns.JUDUL, judul);
        values.put(DatabaseContract.BukuColumns.DESKRIPSI, deskripsi);

        Intent intent = new Intent();
        buku.setJudul(judul);
        buku.setDeskripsi(deskripsi);

        if (isEdit) {
            values.put(DatabaseContract.BukuColumns.UPDATED_AT, currentTime);
            buku.setUpdatedAt(currentTime);
            intent.putExtra(EXTRA_BUKU, buku);

            long result = bukuHelper.update(String.valueOf(buku.getId()), values);
            if (result > 0) {
                setResult(RESULT_UPDATE, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to update data", Toast.LENGTH_SHORT).show();
            }
        } else {
            values.put(DatabaseContract.BukuColumns.CREATED_AT, currentTime);
            buku.setCreatedAt(currentTime);
            intent.putExtra(EXTRA_BUKU, buku);

            long result = bukuHelper.insert(values);
            if (result > 0) {
                buku.setId((int) result);
                setResult(RESULT_ADD, intent);
                finish();
            } else {
                Toast.makeText(this, "Failed to add data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void deleteBuku() {
        if (buku != null && buku.getId() > 0) {
            long result = bukuHelper.deleteById(String.valueOf(buku.getId()));
            if (result > 0) {
                setResult(RESULT_DELETE);
                finish();
            } else {
                Toast.makeText(this, "Failed to delete data", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Invalid book data", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bukuHelper != null) {
            bukuHelper.close();
        }
    }
}
