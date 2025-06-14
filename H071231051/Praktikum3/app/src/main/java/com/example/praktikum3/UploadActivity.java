package com.example.praktikum3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UploadActivity extends AppCompatActivity {
    private ImageView uploadImage;
    private EditText captionEditText;
    private Button uploadButton;
    private Button cancelButton;
    private BottomNav bottomNav;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        bottomNav = findViewById(R.id.bottomNavView);

        if (bottomNav != null) {
            bottomNav.setActiveScreen(BottomNav.SCREEN_UPLOAD);
        }

        ArrayList<Post> posts = new ArrayList<>();

        uploadImage = findViewById(R.id.uploadImage);
        captionEditText = findViewById(R.id.captionEditText);
        uploadButton = findViewById(R.id.uploadButton);

        uploadImage.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 100);
        });

        uploadButton.setOnClickListener(v -> {
            String caption = captionEditText.getText().toString();
            Uri imageUri = (Uri) uploadImage.getTag();

            if (imageUri != null && !caption.isEmpty()) {
                String packageName = getPackageName(); // atau context.getPackageName()
                Uri imagePost = Uri.parse("android.resource://" + packageName + imageUri);
                posts.add(new Post(imagePost.toString(), caption));
                Intent intent = new Intent(UploadActivity.this, ProfileActivity.class );
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bottomNav != null) {
            bottomNav.setActiveScreen(BottomNav.SCREEN_UPLOAD);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                getContentResolver().takePersistableUriPermission(
                        imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION
                );
                uploadImage.setImageURI(imageUri);
                uploadImage.setTag(imageUri);
            }
        }
    }
}