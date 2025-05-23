package com.example.localsqlite;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvStudents;
    private BukuAdapter adapter;
    private BukuHelper bukuHelper;

    private TextView noData;
    private EditText search;

    private final int REQUEST_ADD = 100;
    private final int REQUEST_UPDATE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Student List");
        }

        rvStudents = findViewById(R.id.rv_students);
        FloatingActionButton fabAdd = findViewById(R.id.fab_add);
        noData = findViewById(R.id.noData);
        search = findViewById(R.id.search);

        rvStudents.setLayoutManager(new LinearLayoutManager(this));
        adapter = new BukuAdapter(this);
        rvStudents.setAdapter(adapter);

        bukuHelper = BukuHelper.getInstance(getApplicationContext());

        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, FormActivity.class);
            startActivityForResult(intent, REQUEST_ADD);
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchByTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        loadBuku();
    }

    private void loadBuku() {
        new LoadBukuAsync(this, students -> {
            adapter.setStudents(students);
            noData.setVisibility(students.isEmpty() ? View.VISIBLE : View.GONE);
        }).execute();
    }

    private void searchByTitle(String keyword) {
        ImageView loading = findViewById(R.id.loading);

        Glide.with(this)
                .asGif()
                .load(R.drawable.loading)
                .into(loading);
        loading.setVisibility(View.VISIBLE);

        adapter.clear();
        noData.setVisibility(View.GONE);

        new Thread(() -> {
            bukuHelper.open();
            ArrayList<Buku> result = bukuHelper.searchByJudul(keyword);

            try {
                Thread.sleep(3000);  // delay 3 detik
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                loading.setVisibility(View.GONE);

                adapter.setStudents(result);
                noData.setVisibility(result.isEmpty() ? View.VISIBLE : View.GONE);
            });
        }).start();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_ADD) {
            if (resultCode == FormActivity.RESULT_ADD) {
                showToast("Student added successfully");
                loadBuku();
            }
        } else if (requestCode == REQUEST_UPDATE) {
            if (resultCode == FormActivity.RESULT_UPDATE) {
                showToast("Student updated successfully");
                loadBuku();
            } else if (resultCode == FormActivity.RESULT_DELETE) {
                showToast("Student deleted successfully");
                loadBuku();
            }
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (bukuHelper != null) {
            bukuHelper.close();
        }
    }

    private static class LoadBukuAsync {
        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadBukuCallback> weakCallback;

        private LoadBukuAsync(Context context, LoadBukuCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        void execute() {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executor.execute(() -> {
                Context context = weakContext.get();
                if (context != null) {
                    BukuHelper bukuHelper = BukuHelper.getInstance(context);
                    bukuHelper.open();

                    Cursor studentsCursor = bukuHelper.queryAll();
                    ArrayList<Buku> bukus = MappingHelper.mapCursorToArrayList(studentsCursor);

                    studentsCursor.close();

                    handler.post(() -> {
                        LoadBukuCallback callback = weakCallback.get();
                        if (callback != null) {
                            callback.postExecute(bukus);
                        }
                    });
                }
            });
        }
    }

    interface LoadBukuCallback {
        void postExecute(ArrayList<Buku> bukus);
    }


}
