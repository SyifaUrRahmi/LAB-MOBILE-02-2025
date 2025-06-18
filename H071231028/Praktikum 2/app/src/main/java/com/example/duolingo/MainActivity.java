package com.example.duolingo;

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

    private static final int REQUEST_CODE_EDIT = 1;

    private TextView namaBesar, username;
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inisialisasi view
        namaBesar = findViewById(R.id.namaBesar);
        username = findViewById(R.id.username);
        avatar = findViewById(R.id.Avatar);

        // Tombol settings
        ImageView settings = findViewById(R.id.settings);
        settings.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EditProfile.class);

            // Kirim data awal profile
            UserProfile currentProfile = new UserProfile(
                    namaBesar.getText().toString(),
                    username.getText().toString(),
                    "",     // password
                    "",     // email
                    null    // imageUri
            );
            intent.putExtra("userProfile", currentProfile);
            startActivityForResult(intent, REQUEST_CODE_EDIT);
        });
    }

    // Tangkap data kembali dari EditProfile
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EDIT && resultCode == RESULT_OK && data != null) {
            UserProfile userProfile = data.getParcelableExtra("userProfile");
            if (userProfile != null) {
                namaBesar.setText(userProfile.getName());
                username.setText(userProfile.getUserId());

                if (userProfile.getImageUri() != null) {
                    avatar.setImageURI(Uri.parse(userProfile.getImageUri()));
                } else {
                    avatar.setImageResource(R.drawable.vatar); // default avatar
                }
            }
        }
    }
}
