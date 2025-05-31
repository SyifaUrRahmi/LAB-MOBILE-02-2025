package com.example.tp2_h071231048;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private EditText editUsername;
    private ImageView editProfileImage;
    private Button saveButton;

    private Uri selectedImageUri;

    ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    editProfileImage.setImageURI(uri);
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        editUsername = findViewById(R.id.et_username);
        editProfileImage = findViewById(R.id.iv_avatar);
        saveButton = findViewById(R.id.btn_save);


        editUsername.setText(Profile.getUsername());

        if (Profile.getProfileImageUri() != null) {
            editProfileImage.setImageURI(Profile.getProfileImageUri());
        }

        editProfileImage.setOnClickListener(v -> {
            pickImageLauncher.launch("image/*");
        });

        saveButton.setOnClickListener(v -> {
            String newUsername = editUsername.getText().toString().trim();
            Profile.setUsername(newUsername);

            if (selectedImageUri != null) {
                Profile.setProfileImageUri(selectedImageUri);
            }


            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);

            startActivity(intent);
            finish();
        });
    }
}
