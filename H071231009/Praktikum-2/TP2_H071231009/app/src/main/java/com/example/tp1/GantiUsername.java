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

public class GantiUsername extends AppCompatActivity {
    EditText editUser;
    Button simpanUser;

    ImageButton buttonkembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ganti_username);

        editUser = findViewById(R.id.editUser);
        simpanUser = findViewById(R.id.simpanuser);

        String usernameLama = getIntent().getStringExtra("USERNAMELAMA");
        if (usernameLama != null && !usernameLama.equals("Pasang")) {
            editUser.setText(usernameLama);
        }

        simpanUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editUser.getText().toString();

                if (username.isEmpty()) {
                    Toast.makeText(GantiUsername.this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Intent resultUser = new Intent();
                    resultUser.putExtra("USERNAME", username);
                    setResult(RESULT_OK, resultUser);
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