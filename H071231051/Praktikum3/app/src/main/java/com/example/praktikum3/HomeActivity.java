package com.example.praktikum3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FeedAdapter feedAdapter;
    private List<FeedPost> postList;
    private BottomNav bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottomNavView);

        if (bottomNav != null) {
            bottomNav.setActiveScreen(BottomNav.SCREEN_HOME);
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        postList = DataSource.getDummyFeedPosts(getPackageName());
        feedAdapter = new FeedAdapter(this, postList);
        recyclerView.setAdapter(feedAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (bottomNav != null) {
            bottomNav.setActiveScreen(BottomNav.SCREEN_HOME);
        }
    }
}