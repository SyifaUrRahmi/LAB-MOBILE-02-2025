package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GantiNomor extends AppCompatActivity {
    EditText editnomor;
    Button simpannomor;

    ImageButton buttonkembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ganti_nomor);

        editnomor = findViewById(R.id.editnomor);
        simpannomor = findViewById(R.id.simpannomor);

        // menampilkan nomor lama
        String nomorLama = getIntent().getStringExtra("NOMOR");
        if (nomorLama != null && !nomorLama.isEmpty()) {
            editnomor.setText(nomorLama);
        }

        simpannomor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomor = editnomor.getText().toString().trim();

                if (nomor.isEmpty()) {
                    Toast.makeText(GantiNomor.this, "Nomor tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if (!nomor.matches("\\d+")) {
                    Toast.makeText(GantiNomor.this, "Nomor hanya boleh berupa angka", Toast.LENGTH_SHORT).show();
                } else {
                    Log.d("GantiNomor", "Nomor dikirim ke MainActivity2: " + nomor);
                    Toast.makeText(GantiNomor.this, "Mengirim nomor: " + nomor, Toast.LENGTH_SHORT).show();

                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("NOMOR", nomor);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

        // tombol kembali:
        buttonkembali = findViewById(R.id.backButton);
        buttonkembali.setOnClickListener(v -> {
            finish(); // Hanya kembali tanpa kirim data
        });



    }
}