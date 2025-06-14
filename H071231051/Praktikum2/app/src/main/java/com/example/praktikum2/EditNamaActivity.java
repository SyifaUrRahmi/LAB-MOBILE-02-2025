package com.example.praktikum2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EditNamaActivity extends AppCompatActivity {

    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_nama);

        etName = findViewById(R.id.et_name);
        ImageView btnClose = findViewById(R.id.btn_close);
        ImageView btnSave = findViewById(R.id.btn_save);

        String currentName = getIntent().getStringExtra("currentName");
        etName.setText(currentName);

        btnClose.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            String newName = etName.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newName", newName);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
