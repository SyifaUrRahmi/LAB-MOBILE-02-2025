package com.example.tp3_h071231009;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserProfil extends AppCompatActivity {

    private static final int REQUEST_TAMBAH_POST = 1;

    private RecyclerView recyclerView;
    private MainAdapter2 adapter;
    private List<MainItem2> mainItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);

        ImageButton home = findViewById(R.id.home);
        ImageButton profil = findViewById(R.id.profil);
//        ImageButton tambah = findViewById(R.id.tambah);



        profil.setClipToOutline(true);

        profil.setOnClickListener(v -> {
            Intent intent = new Intent(UserProfil.this, UserProfil.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        home.setOnClickListener(v -> {
            Intent intent2 = new Intent(UserProfil.this, MainActivity.class);
            intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent2);
        });

//        tambah.setOnClickListener(v -> {
//            Intent intent3 = new Intent(UserProfil.this, TambahPost.class);
//            startActivityForResult(intent3, REQUEST_TAMBAH_POST); // Pakai result
//        });



        recyclerView = findViewById(R.id.recyclerMainUser);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainItemList = new ArrayList<>();
        adapter = new MainAdapter2(this, mainItemList, () -> {
            Intent intent = new Intent(UserProfil.this, TambahPost.class);
            startActivityForResult(intent, REQUEST_TAMBAH_POST);
        });
        recyclerView.setAdapter(adapter);



        loadMainItems();
    }

    private void loadMainItems() {
        // Header
        mainItemList.add(new MainItem2(MainItem2.TYPE_HEADER));

        // Sorotan
        List<ProfilSorotan> profilSorotanList = ProfilSorotanData.getProfilSorotanList();
        mainItemList.add(new MainItem2(MainItem2.TYPE_SOROTAN, profilSorotanList));

        // Bagian Bawah (post grid)
        mainItemList.add(new MainItem2(MainItem2.TYPE_BAWAH));

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TAMBAH_POST && resultCode == RESULT_OK && data != null) {
            Post newPost = data.getParcelableExtra("newPost");
            if (newPost != null) {
                adapter.addPost(newPost);
            }
        }
    }
}