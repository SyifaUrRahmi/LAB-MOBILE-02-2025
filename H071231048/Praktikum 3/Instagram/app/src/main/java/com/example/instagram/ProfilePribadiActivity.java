package com.example.instagram;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.instagram.adapter.FeedAdapter;
import com.example.instagram.adapter.ProfileAdapter;
import com.example.instagram.model.FeedModel;
import com.example.instagram.model.PhotoModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePribadiActivity extends AppCompatActivity {
    CircleImageView halamanAkun;
    private RecyclerView recyclerView;
    private FeedAdapter feedAdapter;
    private List<FeedModel> userFeedsOnUpdate = FeedRepository.getFeedList();
    private List<FeedModel> allFeeds;
    private List<FeedModel> userFeeds;

    ProfileAdapter profilePribadiAdapter;

    private ImageView fotoProfil, fotoHighlight, backHome, postingFoto;
    private TextView namaHighlight, tvFollowersCount, tvPostinganCount, tvFollowingCount, bioProfile, tvUsernameProfile, tvUsernameProfile2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_pribadi);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        backHome = findViewById(R.id.backHome);
        tvUsernameProfile = findViewById(R.id.tvUsernameProfile);
        tvUsernameProfile2 = findViewById(R.id.tvUsernameProfile2);
        fotoProfil = findViewById(R.id.fotoProfil);
        bioProfile = findViewById(R.id.bioProfile);
        fotoHighlight = findViewById(R.id.fotoHighlight);
        namaHighlight = findViewById(R.id.namaHighlight);
        tvFollowersCount = findViewById(R.id.tvFollowersCount);
        tvPostinganCount = findViewById(R.id.tvPostinganCount);
        tvFollowingCount = findViewById(R.id.tvFollowingCount);
        recyclerView = findViewById(R.id.profile_feed);
        halamanAkun = findViewById(R.id.halamanAkun);

        postingFoto = findViewById(R.id.postingFoto);

        postingFoto.setOnClickListener( v -> {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
            startActivityForResult(Intent.createChooser(intent, "Pilih Foto"), 101);
        });

        backHome.setOnClickListener( v -> {
            finish();
        });

        allFeeds = getIntent().getParcelableArrayListExtra("feedList");
        userFeeds = new ArrayList<>();

        String username = "andiaaisar_";

        for (FeedModel feed : allFeeds) {
            if (feed.getUsername().equals(username)) {
                userFeeds.add(feed);
            }
        }


        if (!userFeeds.isEmpty()) {
            FeedModel firstFeed = userFeeds.get(0);
            tvUsernameProfile.setText(firstFeed.getUsername());
            tvUsernameProfile2.setText(firstFeed.getUsername());
            fotoProfil.setImageResource(firstFeed.getProfileImage());
            bioProfile.setText(firstFeed.getBio());
            halamanAkun.setImageResource(firstFeed.getProfileImage());
            fotoHighlight.setImageResource(firstFeed.getHighlightImage());
            namaHighlight.setText(firstFeed.getHighlightName());
            tvFollowersCount.setText(String.valueOf(formatNumber(firstFeed.getFollowerCount())));
            tvPostinganCount.setText(String.valueOf(formatNumber(firstFeed.getPostCount())));
            tvFollowingCount.setText(String.valueOf(formatNumber(firstFeed.getFollowingCount())));
        } else {
            tvUsernameProfile.setText("andiaaisar_");
            fotoProfil.setImageResource(R.drawable.aisar);
            bioProfile.setText("Faizz\n" +
                    "he/him\n" +
                    "\uD83C\uDF49 Atychiphobia | Undergraduate Student");
            fotoHighlight.setImageResource(R.drawable.highlightreyriz);
            namaHighlight.setText("Achievement");
            tvFollowersCount.setText("950");
            tvPostinganCount.setText(String.valueOf(formatNumber(0)));
            tvFollowingCount.setText("311");
            halamanAkun.setImageResource(R.drawable.reyriz);
        }




        int numberOfColumns = 3;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        profilePribadiAdapter = new ProfileAdapter(this, userFeeds);
        recyclerView.setAdapter(profilePribadiAdapter);



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == RESULT_OK && data != null) {
            ArrayList<Uri> selectedImages = new ArrayList<>();

            if (data.getClipData() != null) {
                int count = Math.min(data.getClipData().getItemCount(), 6); // max 6
                for (int i = 0; i < count; i++) {
                    selectedImages.add(data.getClipData().getItemAt(i).getUri());
                }
            } else if (data.getData() != null) {
                selectedImages.add(data.getData());
            }

            ArrayList<PhotoModel> photoModels = new ArrayList<>();
            for (Uri uri : selectedImages) {
                try {
                    Uri localUri = copyUriToInternalStorage(uri);
                    photoModels.add(new PhotoModel(localUri));
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Gagal menyimpan gambar", Toast.LENGTH_SHORT).show();
                }
            }

            Intent intent = new Intent(this, CaptionActivity.class);
            intent.putParcelableArrayListExtra("selectedPhotos", photoModels);
            startActivityForResult(intent, 102);
        } else if (requestCode == 102 && resultCode == RESULT_OK && data != null) {
            ArrayList<PhotoModel> selectedPhotos = data.getParcelableArrayListExtra("selectedPhotos");
            String caption = data.getStringExtra("caption");

            FeedModel newFeed = new FeedModel(
                    R.drawable.reyriz,
                    "reyriz_",
                    selectedPhotos,
                    caption,
                    0,
                    0,
                    "Faizz\n" +
                            "he/him\n" +
                            "\uD83C\uDF49 Atychiphobia | Undergraduate Student",
                    "Achievement",
                    R.drawable.highlightreyriz,
                    selectedPhotos,FeedRepository.getPostCount(), 999, 311
            );


            FeedRepository.addFeed(newFeed);
            profilePribadiAdapter.notifyItemInserted(profilePribadiAdapter.getItemCount() - 1);
            recyclerView.scrollToPosition(0);

            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        userFeeds.clear();
        allFeeds = FeedRepository.getFeedList(); // ambil ulang
        for (FeedModel feed : allFeeds) {
            if (feed.getUsername().equals("reyriz_")) {
                userFeeds.add(feed);
            }
        }

        profilePribadiAdapter.notifyDataSetChanged();

    }

    private Uri copyUriToInternalStorage(Uri uri) throws IOException {
        InputStream inputStream = getContentResolver().openInputStream(uri);
        if (inputStream == null) throw new FileNotFoundException("Unable to open input stream");

        File directory = new File(getFilesDir(), "images");
        if (!directory.exists()) directory.mkdirs();

        String fileName = "img_" + System.currentTimeMillis() + ".jpg";
        File outFile = new File(directory, fileName);

        try (OutputStream outputStream = new FileOutputStream(outFile)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
        }

        return Uri.fromFile(outFile);
    }


    private String formatNumber(int number) {
        if (number >= 1_000_000) {
            return number / 1_000_000 + "M";
        } else if (number >= 1_000) {
            return number / 1_000 + "K";
        } else {
            return String.valueOf(number);
        }
    }

    // Method yang bisa kamu panggil nanti jika postingan baru ditambahkan:
    public void addNewPost(FeedModel newFeed) {
        userFeeds.add(0, newFeed);
        feedAdapter.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }
}
