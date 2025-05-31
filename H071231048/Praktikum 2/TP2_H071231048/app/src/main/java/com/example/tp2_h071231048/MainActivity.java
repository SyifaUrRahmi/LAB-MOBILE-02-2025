package com.example.tp2_h071231048;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView usernameText;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        usernameText = findViewById(R.id.usernameText);
        profileImage = findViewById(R.id.profileImage);


        ImageView settingsIcon = findViewById(R.id.settingsIcon);
        settingsIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        updateProfileUI();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateProfileUI();
    }

    private void updateProfileUI() {
        usernameText.setText(Profile.getUsername());

        Uri imageUri = Profile.getProfileImageUri();
        if (imageUri != null) {
            profileImage.setImageURI(imageUri);
        } else {
            profileImage.setImageResource(R.drawable.cat);
        }
    }
}
