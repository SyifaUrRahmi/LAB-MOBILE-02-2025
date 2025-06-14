package com.example.praktikum_6;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CharacterAdapter.OnLoadMoreListener {
    private ApiService apiService;
    private RecyclerView recyclerView;
    private CharacterAdapter characterAdapter;
    private List<Character> characterList = new ArrayList<>();

    // Pagination variables
    private int currentPage = 1;
    private int totalPages = 1;
    private boolean isLoading = false;

    // Threading components
    private ExecutorService executorService;
    private Handler mainHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize threading components
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());

        apiService = RetrofitClient.getClient().create(ApiService.class);

        // Setup RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        characterAdapter = new CharacterAdapter(characterList);
        characterAdapter.setOnLoadMoreListener(this);
        recyclerView.setAdapter(characterAdapter);

        // Load initial data
        loadFirstPage();
    }

    private void loadFirstPage() {
        isLoading = true;

        // Use background thread for API call
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    // Getting characters for first page
                    Call<CharacterResponse> call = apiService.getCharacters(currentPage);
                    call.enqueue(new Callback<CharacterResponse>() {
                        @Override
                        public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                CharacterResponse characterResponse = response.body();
                                totalPages = characterResponse.getInfo().getPages();

                                characterList.clear();
                                characterList.addAll(characterResponse.getResults());

                                // Update UI on main thread
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        characterAdapter.notifyDataSetChanged();

                                        // Add loading footer if more pages exist
                                        if (currentPage < totalPages) {
                                            // Add null item for load more button
                                            characterList.add(null);
                                            characterAdapter.addLoadingFooter();
                                            characterAdapter.setHasMoreData(true);
                                        } else {
                                            // No more pages
                                            characterAdapter.setHasMoreData(false);
                                        }

                                        isLoading = false;
                                    }
                                });
                            } else {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,
                                                "Gagal mengambil data: " + response.code(),
                                                Toast.LENGTH_LONG).show();
                                        isLoading = false;
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<CharacterResponse> call, Throwable t) {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this,
                                            "Koneksi gagal: " + t.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                    isLoading = false;
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            isLoading = false;
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onLoadMore() {
        if (!isLoading && currentPage < totalPages) {
            isLoading = true;
            currentPage++;

            // Simulate delay for better UX and demonstrate progress bar
            mainHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadNextPage();
                }
            }, 1000);
        }
    }

    private void loadNextPage() {
        // Use background thread for API call
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Call<CharacterResponse> call = apiService.getCharacters(currentPage);
                    call.enqueue(new Callback<CharacterResponse>() {
                        @Override
                        public void onResponse(Call<CharacterResponse> call, Response<CharacterResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                // First remove loading item
                                if (characterList.size() > 0) {
                                    characterList.remove(characterList.size() - 1);
                                }

                                List<Character> result = response.body().getResults();

                                // Update the adapter on main thread
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        characterList.addAll(result);
                                        characterAdapter.notifyDataSetChanged();

                                        // Check if we've reached the last page
                                        if (currentPage < totalPages) {
                                            // Add loading footer again if more pages exist
                                            characterList.add(null);
                                            characterAdapter.resetLoadingState();
                                            characterAdapter.setHasMoreData(true);
                                        } else {
                                            // We've reached the last page, no more data
                                            characterAdapter.setHasMoreData(false);
                                        }

                                        isLoading = false;
                                    }
                                });
                            } else {
                                mainHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        characterAdapter.resetLoadingState();
                                        Toast.makeText(MainActivity.this,
                                                "Gagal mengambil data: " + response.code(),
                                                Toast.LENGTH_LONG).show();
                                        isLoading = false;
                                    }
                                });
                            }
                        }

                        @Override
                        public void onFailure(Call<CharacterResponse> call, Throwable t) {
                            mainHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    characterAdapter.resetLoadingState();
                                    Toast.makeText(MainActivity.this,
                                            "Koneksi gagal: " + t.getMessage(),
                                            Toast.LENGTH_LONG).show();
                                    isLoading = false;
                                }
                            });
                        }
                    });
                } catch (Exception e) {
                    mainHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            characterAdapter.resetLoadingState();
                            Toast.makeText(MainActivity.this,
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                            isLoading = false;
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}