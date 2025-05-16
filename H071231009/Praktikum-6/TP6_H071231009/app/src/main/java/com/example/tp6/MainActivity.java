package com.example.tp6;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ApiService apiService;
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private TextView tvNoResult;
    private ImageView loadingIndicator, loadingIndicator2;
    private Button btnNext;

    private int currentPage = 1;
    private List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = RetrofitClient.getClient().create(ApiService.class);
        recyclerView = findViewById(R.id.recyclerView);
        tvNoResult = findViewById(R.id.tv_no_result);

        loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator2 = findViewById(R.id.loading_indicator1);
        btnNext = findViewById(R.id.btn_next);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);

        loadingIndicator2.setVisibility(View.VISIBLE);
        btnNext.setVisibility(View.GONE);
        Glide.with(this)
                .asGif()
                .load(R.drawable.loading)
                .into(loadingIndicator2);

        new Handler().postDelayed(() -> {
            loadPage(currentPage);
        }, 2000);

        btnNext.setOnClickListener(v -> {
            loadingIndicator.setVisibility(View.VISIBLE);
            Glide.with(this)
                    .asGif()
                    .load(R.drawable.loading)
                    .into(loadingIndicator);

            btnNext.setEnabled(false);

            new Handler().postDelayed(() -> {
                currentPage++;
                loadPage(currentPage);
            }, 3000);
        });
    }


    private void loadPage(int page) {
        Call<UserResponse> call = apiService.getUsers(page);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                loadingIndicator2.setVisibility(View.GONE);
                loadingIndicator.setVisibility(View.GONE);
                btnNext.setVisibility(View.VISIBLE);
                btnNext.setEnabled(true);

                if (response.isSuccessful() && response.body() != null) {
                    List<User> newUsers = response.body().getResults();
                    if (newUsers != null && !newUsers.isEmpty()) {
                        userList.addAll(newUsers);
                        userAdapter.notifyDataSetChanged();
                        tvNoResult.setVisibility(View.GONE);
                    } else {
                        tvNoResult.setVisibility(View.VISIBLE);
                    }
                } else {
                    tvNoResult.setVisibility(View.VISIBLE);
                    Log.e("API", "Response failed or empty");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                loadingIndicator2.setVisibility(View.GONE);
                loadingIndicator.setVisibility(View.GONE);
                btnNext.setEnabled(true);

                if (!isNetworkAvailable()) {
                    setContentView(R.layout.error);

                    ImageButton btnLoad = findViewById(R.id.btn_load);
                    btnLoad.setOnClickListener(v -> {
                        if (isNetworkAvailable()) {
                            recreate();
                        } else {
                            Toast.makeText(MainActivity.this, "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    tvNoResult.setVisibility(View.VISIBLE);
                }
            }

        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
