package com.example.tp1;

import android.content.Intent;
import android.os.Bundle;
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

public class GantiEmail extends AppCompatActivity {

    EditText editemail;
    Button simpanemail;

    ImageButton buttonkembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ganti_email);

        editemail = findViewById(R.id.editemail);
        simpanemail = findViewById(R.id.simpanemail);

        // untuk email lama
        String previousEmail = getIntent().getStringExtra("CURRENT_EMAIL");
        if (previousEmail != null && !previousEmail.equals("Tambahkan")) {
            editemail.setText(previousEmail);
        }

        simpanemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editemail.getText().toString().trim();


                if (email.endsWith("@gmail.com")) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("EMAIL", email);
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    Toast.makeText(GantiEmail.this, "Email harus menggunakan @gmail.com", Toast.LENGTH_SHORT).show();
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