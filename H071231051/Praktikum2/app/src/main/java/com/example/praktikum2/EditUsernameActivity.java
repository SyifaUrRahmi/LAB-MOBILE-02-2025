package com.example.praktikum2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EditUsernameActivity extends AppCompatActivity {

    private EditText name_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nama_pengguna);

        name_user = findViewById(R.id.name_user);
        ImageView btnClose = findViewById(R.id.btn_close);
        ImageView btnSave = findViewById(R.id.btn_save);

        String currentUsername = getIntent().getStringExtra("currentUsername");
        name_user.setText(currentUsername);

        btnClose.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            String newUsername = name_user.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newUsername", newUsername);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
