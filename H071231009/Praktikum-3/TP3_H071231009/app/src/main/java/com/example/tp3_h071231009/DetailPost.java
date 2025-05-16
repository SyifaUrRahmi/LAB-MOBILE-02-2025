package com.example.tp3_h071231009;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DetailPost extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Integer> imageList;
    DetailPostAdapter detailPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        recyclerView = findViewById(R.id.recyclerPostingan);
        List<Post> postList = getIntent().getParcelableArrayListExtra("postList");
        int clickedPosition = getIntent().getIntExtra("clickedPosition", -1);


        if (postList != null && clickedPosition >= 0 && clickedPosition < postList.size()) {
            Post clickedPost = postList.remove(clickedPosition);
            postList.add(0, clickedPost);

            detailPostAdapter = new DetailPostAdapter(postList);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(detailPostAdapter);
            recyclerView.scrollToPosition(0);
        }

        ImageButton kembali = findViewById(R.id.kembali);
        kembali.setOnClickListener(v -> finish());
    }


}
