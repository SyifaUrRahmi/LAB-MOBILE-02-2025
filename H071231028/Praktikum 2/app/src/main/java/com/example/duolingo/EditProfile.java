package com.example.duolingo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class EditProfile extends AppCompatActivity {

    TextInputEditText etName, etID, emailInput;
    Button btnSave;
    ImageView imgProfile;
    TextView tvAvatar;
    Uri selectedImageUri = null;

    private static final int PICK_IMAGE_REQUEST = 1;
    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Edit Profile");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Init view
        etName = findViewById(R.id.etName);
        etID = findViewById(R.id.etID);
        emailInput = findViewById(R.id.emailInput);
        btnSave = findViewById(R.id.btnSave);
        imgProfile = findViewById(R.id.imgProfile);
        tvAvatar = findViewById(R.id.tvAvatar);

        // Ambil userProfile dari intent
        userProfile = getIntent().getParcelableExtra("userProfile");

        if (userProfile != null) {
            etName.setText(userProfile.getName());
            etID.setText(userProfile.getUserId());
            emailInput.setText(userProfile.getEmail());

            if (userProfile.getImageUri() != null) {
                selectedImageUri = Uri.parse(userProfile.getImageUri());
                imgProfile.setImageURI(selectedImageUri);
            }
        }

        // Ubah avatar
        tvAvatar.setOnClickListener(v -> {
            Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(pickPhoto, PICK_IMAGE_REQUEST);
        });

        // Simpan perubahan
        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String userId = etID.getText().toString();
            String email = emailInput.getText().toString();

            // Update userProfile
            userProfile.setName(name);
            userProfile.setUserId(userId);
            userProfile.setEmail(email);
            if (selectedImageUri != null) {
                userProfile.setImageUri(selectedImageUri.toString());
            }

            // Kirim balik
            Intent resultIntent = new Intent();
            resultIntent.putExtra("userProfile", userProfile);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            imgProfile.setImageURI(selectedImageUri);
        }
    }
}
