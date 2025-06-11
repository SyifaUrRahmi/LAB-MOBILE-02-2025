package com.example.praktikum08;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FormActivity extends AppCompatActivity {

    private EditText etTitle, etContent;
    private Button btnSave, btnDelete;
    private NoteHelper noteHelper;

    private boolean isEdit = false;
    private int noteId = -1; // default ID jika belum ada

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etTitle = findViewById(R.id.etTitle);
        etContent = findViewById(R.id.etContent);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);
        ImageButton btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> finish());



        noteHelper = new NoteHelper(this);

        // Ambil data dari intent jika edit
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("id")) {
            isEdit = true;
            noteId = intent.getIntExtra("id", -1);
            String title = intent.getStringExtra("title");
            String content = intent.getStringExtra("content");

            etTitle.setText(title);
            etContent.setText(content);
            btnSave.setText("Update");
            btnDelete.setVisibility(View.VISIBLE);
        }



        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String content = etContent.getText().toString().trim();

            if (title.isEmpty()) {
                etTitle.setError("Title is required");
                return;
            }

            if (isEdit) {
                noteHelper.updateNote(noteId, title, content);
            } else {
                noteHelper.insertNote(title, content);
            }


            finish(); // kembali ke MainActivity
        });

        btnDelete.setOnClickListener(v -> {
            if (isEdit && noteId != -1) {
                noteHelper.deleteNote(noteId);
                finish(); // balik ke MainActivity
            }
        });

    }
}
