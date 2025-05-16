package com.example.tp3_h071231009;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerMain;
    private MainAdapter mainAdapter;
    private List<MainItem> mainItemList;

    private ImageView profilFeed;

    private MainAdapter2 adapter;
    private List<MainItem2> mainItemList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View footerLayout = findViewById(R.id.footer);

        ImageButton home = findViewById(R.id.home);

        ImageButton profil = findViewById(R.id.profil);
        profil.setClipToOutline(true);

//        ImageButton tambah = findViewById(R.id.tambah);


        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UserProfil.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent, 1);
                finish();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, MainActivity.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent2);

            }
        });

//        tambah.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent3 = new Intent(MainActivity.this, TambahPost.class);
//                startActivityForResult(intent3, 1);
//            }
//        });

        recyclerMain = findViewById(R.id.recyclerMain);
        mainItemList = new ArrayList<>();


        mainItemList.add(new MainItem(MainItem.TYPE_HEADER));

        List<Sorotan> sorotanList = DataSorotan.getSorotanList();
        mainItemList.add(new MainItem(MainItem.TYPE_SOROTAN, sorotanList));


        List<Feed> feedList = DataFeed.getFeedList();
        for (Feed feed : feedList) {
            mainItemList.add(new MainItem(MainItem.TYPE_FEED, feed));
        }

        mainAdapter = new MainAdapter(this, mainItemList, new MainAdapter.OnProfilClickListener() {
            @Override
            public void onProfilClicked(Feed feed) {
                Intent intent = new Intent(MainActivity.this, ProfileFeed.class);
                intent.putExtra("USERNAME", feed.getUsername());
                intent.putExtra("FOTOPROFIL", feed.getImageProfilResId());
                intent.putExtra("POST", feed.getJumlahPost());
                intent.putExtra("PENGIKUT", feed.getJumlahPengikut());
                intent.putExtra("MENGIKUTI", feed.getJumlahMengikuti());
                intent.putExtra("POSTGAMBAR", feed.getImageFotoFeedResId());
                startActivity(intent);
            }
        });
        recyclerMain.setLayoutManager(new LinearLayoutManager(this));
        recyclerMain.setAdapter(mainAdapter);

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Post newPost = data.getParcelableExtra("newPost");
            if (newPost != null) {
                adapter.addPost(newPost);
            }
        }
    }

}