package com.example.tp3_h071231009;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TambahPost extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Uri selectedImageUri;
    private ImageButton imgButton;
    private EditText captionEditText;
    private Button postButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_post);

        imgButton = findViewById(R.id.img_btn);
        captionEditText = findViewById(R.id.caption);
        postButton = findViewById(R.id.btn_post);

        imgButton.setOnClickListener(v -> openGallery());

        postButton.setOnClickListener(v -> {
            String caption = captionEditText.getText().toString().trim();

            if (selectedImageUri == null) {
                Toast.makeText(this, "Pilih gambar terlebih dahulu", Toast.LENGTH_SHORT).show();
                return;
            }

            String formattedDate = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                    .format(new Date());

            Post newPost = new Post(selectedImageUri.toString(), caption, formattedDate);

            Intent intent = new Intent();
            intent.putExtra("newPost", newPost);
            setResult(RESULT_OK, intent);
            finish();
        });


        findViewById(R.id.kembali).setOnClickListener(v -> finish());
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            imgButton.setImageURI(selectedImageUri);
        }
    }
}