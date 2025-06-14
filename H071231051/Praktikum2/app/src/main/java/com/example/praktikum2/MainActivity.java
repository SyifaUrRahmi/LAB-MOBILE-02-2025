package com.example.praktikum2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_EDIT_PROFILE = 100;
    private TextView tvMainName;

    private String currentName = "Hanni Pham | NewJeans";

    private ShapeableImageView imageProfileMain;
    private Uri imageUri;

    private TextView tvMainUsername;

    private String currentUsername = "hanni_";

    private TextView tvMainBio;

    private String currentBio = "Vietnamese-Australian \uD83C\uDDFB\uD83C\uDDF3\uD83C\uDDE6\uD83C\uDDFA \nSmiling through life and loving every beat \uD83C\uDFB6 \nBunnies, you’re my energy! \uD83D\uDC30\uD83D\uDC99 \n@newjeans_official";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageProfileMain = findViewById(R.id.foto_profile);

        tvMainName = findViewById(R.id.name);
        tvMainName.setText(currentName);

        tvMainUsername = findViewById(R.id.account_name);
        tvMainUsername.setText(currentUsername);

        tvMainBio = findViewById(R.id.bio);
        tvMainBio.setText(currentBio);

        Button btnEdit = findViewById(R.id.edit);
        btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditProfileActivity.class);
            intent.putExtra("currentName", currentName);
            intent.putExtra("currentUsername", currentUsername);
            intent.putExtra("currentBio", currentBio);

            // Cek apakah imageUri ada atau tidak sebelum dikirim
            if (imageUri != null) {
                intent.putExtra("imageUri", imageUri.toString());
            }
            startActivityForResult(intent, REQUEST_EDIT_PROFILE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_EDIT_PROFILE && resultCode == RESULT_OK && data != null) {
            String newName = data.getStringExtra("newName");
            String uriStr = data.getStringExtra("imageUri");
            String newUsername = data.getStringExtra("newUsername");
            String newBio = data.getStringExtra("newBio");

            if (newName != null) {
                currentName = newName;
                tvMainName.setText(newName);
            }

            if (newUsername != null) {
                currentUsername = newUsername;
                tvMainUsername.setText(newUsername);
            }

            if (newBio != null) {
                currentBio = newBio;
                tvMainBio.setText(newBio);
            }


            if (uriStr != null) {
                imageUri = Uri.parse(uriStr);
                imageProfileMain.setImageURI(imageUri);
            }
        }
    }
}
