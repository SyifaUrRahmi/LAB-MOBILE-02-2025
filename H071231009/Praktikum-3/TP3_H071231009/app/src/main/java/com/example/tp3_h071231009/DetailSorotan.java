package com.example.tp3_h071231009;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailSorotan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sorotan);

        ImageView imageProfil = findViewById(R.id.imageProfil);
        imageProfil.setClipToOutline(true);
        ImageView imageSorotan = findViewById(R.id.imagesorotan);
        TextView namaSorotan = findViewById(R.id.namasorotan);
        TextView tanggalSorotan = findViewById(R.id.tanggalsorotan);


        int fotoResId = getIntent().getIntExtra("foto", R.drawable.contohsorotan);
        int fotoResId2 = getIntent().getIntExtra("foto2", R.drawable.contohsorotan);
        String nama = getIntent().getStringExtra("nama");
        String tanggal = getIntent().getStringExtra("tanggal");


        imageProfil.setImageResource(fotoResId2);
        imageSorotan.setImageResource(fotoResId);
        namaSorotan.setText(nama);
        tanggalSorotan.setText(tanggal);
    }
}
