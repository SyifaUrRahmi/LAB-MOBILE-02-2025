package com.example.praktikum2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.imageview.ShapeableImageView;

public class EditProfileActivity extends AppCompatActivity {

    //untuk nama
    private TextView tvName;
    private String currentName;

    //untuk profile
    private static final int REQUEST_PICK_IMAGE = 10;
    private Uri imageUri;
    private ShapeableImageView imageProfile;

    //untuk nama pengguna
    private TextView tvUser;
    private String currentUsername;

    //untuk Bio
    private TextView tvBio;
    private String currentBio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //untuk profile
        imageProfile = findViewById(R.id.image_profile);

        String uriString = getIntent().getStringExtra("imageUri");
        if (uriString != null) {
            imageUri = Uri.parse(uriString);
            imageProfile.setImageURI(imageUri);
        }

        RelativeLayout fotoProfile = findViewById(R.id.foto_profile);
        fotoProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_PICK_IMAGE);
        });


        //untuk nama
        tvName = findViewById(R.id.tv_name);

        currentName = getIntent().getStringExtra("currentName");
        if (currentName != null) {
            tvName.setText(currentName);
        }

        LinearLayout fieldNama = findViewById(R.id.field_nama);
        fieldNama.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfileActivity.this, EditNamaActivity.class);
            intent.putExtra("currentName", tvName.getText().toString());
            startActivityForResult(intent, 1);
        });

        //untuk user name
        tvUser =findViewById(R.id.tv_username);

        currentUsername = getIntent().getStringExtra(("currentUsername"));
        if (currentUsername != null) {
            tvUser.setText(currentUsername);
        }

        LinearLayout fieldNamaUser = findViewById(R.id.field_username);
        fieldNamaUser.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfileActivity.this, EditUsernameActivity.class);
            intent.putExtra("currentUsername", tvUser.getText().toString());
            startActivityForResult(intent, 2);
        });

        //untuk user name
        tvBio =findViewById(R.id.tv_bio);

        currentBio = getIntent().getStringExtra(("currentBio"));
        if (currentBio != null) {
            tvBio.setText(currentBio);
        }

        LinearLayout fieldBio = findViewById(R.id.field_bio);
        fieldBio.setOnClickListener(v -> {
            Intent intent = new Intent(EditProfileActivity.this, EditBioActivity.class);
            intent.putExtra("currentBio", tvBio.getText().toString());
            startActivityForResult(intent, 3);
        });


        ImageView btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newName", ((TextView) findViewById(R.id.tv_name)).getText().toString());
            resultIntent.putExtra("newUsername", ((TextView) findViewById(R.id.tv_username)).getText().toString());
            resultIntent.putExtra("newBio", ((TextView) findViewById(R.id.tv_bio)).getText().toString());
            if (imageUri != null) {
                resultIntent.putExtra("imageUri", imageUri.toString());
            }
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            imageProfile.setImageURI(imageUri);
        }
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String newName = data.getStringExtra("newName");
            tvName.setText(newName);
        }
        if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
            String newUsername = data.getStringExtra("newUsername");
            tvUser.setText(newUsername);
        }

        if (requestCode == 3 && resultCode == RESULT_OK && data != null) {
            String newBio = data.getStringExtra("newBio");
            tvBio.setText(newBio);
        }
    }
}