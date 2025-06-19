package com.example.praktikum_6;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {
    private ApiService apiService;
    private int characterId;

    private ImageView avatarImageView;
    private TextView nameTextView;
    private TextView statusTextView;
    private TextView speciesTextView;
    private TextView genderTextView;
    private TextView originTextView;
    private TextView locationTextView;
    private TextView episodeCountTextView;

    // Add progress bar and content container
    private ProgressBar progressBar;
    private ScrollView contentContainer;

    // Threading components
    private ExecutorService executorService;
    private Handler mainHandler;
    private static final int MINIMUM_LOADING_TIME = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize threading components
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Character Detail");

        // Initialize views
        avatarImageView = findViewById(R.id.iv_detail_avatar);
        nameTextView = findViewById(R.id.tv_detail_name);
        statusTextView = findViewById(R.id.tv_detail_status);
        speciesTextView = findViewById(R.id.tv_detail_species);
        genderTextView = findViewById(R.id.tv_detail_gender);
        originTextView = findViewById(R.id.tv_detail_origin);
        locationTextView = findViewById(R.id.tv_detail_location);
        episodeCountTextView = findViewById(R.id.tv_detail_episode_count);

        // Initialize progress bar and content container
        progressBar = findViewById(R.id.progress_bar_detail);
        contentContainer = findViewById(R.id.content_container);

        // Show progress bar and hide content initially
        showLoading(true);

        // Get character ID from intent
        characterId = getIntent().getIntExtra("CHARACTER_ID", -1);

        if (characterId == -1) {
            Toast.makeText(this, "Character ID not found", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Create API service and load character details
        apiService = RetrofitClient.getClient().create(ApiService.class);
        loadCharacterDetail(characterId);
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            contentContainer.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            contentContainer.setVisibility(View.VISIBLE);
        }
    }

    private void loadCharacterDetail(int id) {
        // Record the start time to ensure minimum loading time
        final long startTime = System.currentTimeMillis();

        // Use background thread for API call
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Call<Character> call = apiService.getCharacterDetail(id);
                    call.enqueue(new Callback<Character>() {
                        @Override
                        public void onResponse(Call<Character> call, Response<Character> response) {
                            // Calculate how much time has passed
                            long elapsedTime = System.currentTimeMillis() - startTime;
                            long remainingDelay = Math.max(0, MINIMUM_LOADING_TIME - elapsedTime);

                            if (response.isSuccessful() && response.body() != null) {
                                final Character character = response.body();

                                // Apply delay before showing content
                                mainHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        displayCharacterDetails(character);
                                        showLoading(false);
                                    }
                                }, remainingDelay);
                            } else {
                                // Apply delay before showing error
                                mainHandler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        showLoading(false);
                                        Toast.makeText(DetailActivity.this,
                                                "Gagal mengambil detail: " + response.code(),
                                                Toast.LENGTH_LONG).show();
                                    }
                                }, remainingDelay);
                            }
                        }

                        @Override
                        public void onFailure(Call<Character> call, Throwable t) {
                            // Calculate how much time has passed
                            long elapsedTime = System.currentTimeMillis() - startTime;
                            long remainingDelay = Math.max(0, MINIMUM_LOADING_TIME - elapsedTime);

                            // Apply delay before showing error
                            mainHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    showLoading(false);
                                    Toast.makeText(DetailActivity.this,
                                            "Koneksi gagal: " + t.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                            }, remainingDelay);
                        }
                    });
                } catch (Exception e) {
                    // Calculate how much time has passed
                    long elapsedTime = System.currentTimeMillis() - startTime;
                    long remainingDelay = Math.max(0, MINIMUM_LOADING_TIME - elapsedTime);

                    // Apply delay before showing error
                    mainHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showLoading(false);
                            Toast.makeText(DetailActivity.this,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }, remainingDelay);
                }
            }
        });
    }

    private void displayCharacterDetails(Character character) {
        Picasso.get().load(character.getImage()).into(avatarImageView);
        nameTextView.setText(character.getName());
        statusTextView.setText("Status: " + character.getStatus());
        speciesTextView.setText("Species: " + character.getSpecies());
        genderTextView.setText("Gender: " + character.getGender());
        originTextView.setText("Origin: " + character.getOrigin().getName());
        locationTextView.setText("Location: " + character.getLocation().getName());

        // Count episodes
        int episodeCount = character.getEpisode() != null ? character.getEpisode().length : 0;
        episodeCountTextView.setText("Appears in " + episodeCount + " episodes");

        // Set title to character name
        getSupportActionBar().setTitle(character.getName());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}