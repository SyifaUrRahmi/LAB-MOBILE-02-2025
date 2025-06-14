package com.example.praktikum2;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class EditBioActivity extends AppCompatActivity {
    private EditText edit_bio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_bio);

        edit_bio = findViewById(R.id.edit_bio);
        ImageView btnClose = findViewById(R.id.btn_close);
        ImageView btnSave = findViewById(R.id.btn_save);

        String currentBio = getIntent().getStringExtra("currentBio");
        edit_bio.setText(currentBio);

        btnClose.setOnClickListener(v -> finish());

        btnSave.setOnClickListener(v -> {
            String newBio = edit_bio.getText().toString();
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newBio", newBio);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
