package com.example.tp1;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    RelativeLayout layoutPengaturan;
    ImageView profil;
    TextView nomor;
    private String username = "";
    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        layoutPengaturan = findViewById(R.id.layoutPengaturan);
        nomor = findViewById(R.id.nomor);
        profil = findViewById(R.id.profil); // Tambahkan ini
        profil.setClipToOutline(true);

        profil.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("NOMOR", nomor.getText().toString());
            intent.putExtra("USERNAME", username);
            intent.putExtra("EMAIL", email);

            // dari galeri
            if (profil.getTag() != null) {
                intent.putExtra("IMAGE_URI", profil.getTag().toString());
            } else {
                // dari kamera
                profil.setDrawingCacheEnabled(true);
                profil.buildDrawingCache();
                Bitmap bitmap = profil.getDrawingCache();

                if (bitmap != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    intent.putExtra("IMAGE_BITMAP", byteArray);
                }

                profil.setDrawingCacheEnabled(false);
            }

            startActivityForResult(intent, 4);
        });

        layoutPengaturan.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("NOMOR", nomor.getText().toString());
            intent.putExtra("USERNAME", username);
            intent.putExtra("EMAIL", email);

            // dari galeri
            if (profil.getTag() != null) {
                intent.putExtra("IMAGE_URI", profil.getTag().toString());
            } else {
                // dari kamera
                profil.setDrawingCacheEnabled(true);
                profil.buildDrawingCache();
                Bitmap bitmap = profil.getDrawingCache();

                if (bitmap != null) {
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    intent.putExtra("IMAGE_BITMAP", byteArray);
                }

                profil.setDrawingCacheEnabled(false); // bersihkan cache
            }

            startActivityForResult(intent, 4);
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 4 && resultCode == RESULT_OK && data != null) {
            String nomorBaru = data.getStringExtra("NOMOR");
            String usernameBaru = data.getStringExtra("USERNAME");
            String emailBaru = data.getStringExtra("EMAIL");

            // gambar dari kamera
            byte[] imageBytes = data.getByteArrayExtra("IMAGE_BITMAP");
            if (imageBytes != null) {
                Log.d("ImageSource", "Gambar diterima dari kamera");
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                profil.setImageBitmap(bitmap); // Mengatur gambar dari Bitmap
                profil.setTag(null); // Tidak ada URI, jadi tag-nya null
                Toast.makeText(this, "Gambar dari Kamera diterima!", Toast.LENGTH_SHORT).show(); // Menampilkan Toast
            } else {
                // dari galeri
                String profileUriString = data.getStringExtra("IMAGE_URI");
                if (profileUriString != null) {
                    Log.d("ImageSource", "Gambar diterima dari galeri");
                    Uri profileUri = Uri.parse(profileUriString);
                    profil.setImageURI(profileUri); // Mengatur gambar dari URI
                    profil.setTag(profileUriString); // Set tag dengan URI
                    Toast.makeText(this, "Gambar dari Galeri diterima!", Toast.LENGTH_SHORT).show(); // Menampilkan Toast
                }
            }

            if (nomorBaru != null) {
                nomor.setText(nomorBaru);
                Toast.makeText(this, "Nomor Baru: " + nomorBaru, Toast.LENGTH_SHORT).show();
            }
            if (usernameBaru != null) {
                username = usernameBaru;
                Toast.makeText(this, "Username Baru: " + usernameBaru, Toast.LENGTH_SHORT).show();
            }
            if (emailBaru != null) {
                email = emailBaru;
                Toast.makeText(this, "Email Baru: " + emailBaru, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Data tidak diterima!", Toast.LENGTH_SHORT).show(); // Menampilkan Toast jika data tidak diterima
        }
    }


}
