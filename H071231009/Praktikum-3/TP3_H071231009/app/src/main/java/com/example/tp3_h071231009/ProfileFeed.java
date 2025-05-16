package com.example.tp3_h071231009;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileFeed extends AppCompatActivity {

    ImageView imageProfil, post;
    TextView username, usernameHeader, jumlahPostingan, jumlahPengikut, jumlahMengikuti, bio;
    ImageButton kembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_feed);

        Intent intent = getIntent();
        String user = intent.getStringExtra("USERNAME");
        int fotoProfilResId = intent.getIntExtra("FOTOPROFIL", 0);
        int jumlahPost = intent.getIntExtra("POST", 0);
        int jumlahFollower = intent.getIntExtra("PENGIKUT", 0);
        int jumlahFollowing = intent.getIntExtra("MENGIKUTI", 0);
        int postImageResId = intent.getIntExtra("POSTGAMBAR", 0);

        // Hubungkan ke layout
        imageProfil = findViewById(R.id.imageProfil);
        post = findViewById(R.id.post);
        username = findViewById(R.id.username);
        usernameHeader = findViewById(R.id.usernameheader);
        jumlahPostingan = findViewById(R.id.jumlahPostingan);
        jumlahPengikut = findViewById(R.id.jumlahPengikut);
        jumlahMengikuti = findViewById(R.id.jumlahMengikuti);
        bio = findViewById(R.id.bio);
        kembali = findViewById(R.id.kembali);

        // Set data ke view
        username.setText(user);
        usernameHeader.setText(user);
        imageProfil.setImageResource(fotoProfilResId);
        imageProfil.setClipToOutline(true);
        post.setImageResource(postImageResId);
        jumlahPostingan.setText(String.valueOf(jumlahPost));
        jumlahPengikut.setText(String.valueOf(jumlahFollower));
        jumlahMengikuti.setText(String.valueOf(jumlahFollowing));
        bio.setText("Hai ini bio " + user);

        // Logika tombol kembali
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
