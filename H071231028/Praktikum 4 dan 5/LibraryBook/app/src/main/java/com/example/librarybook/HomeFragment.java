package com.example.librarybook;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomeFragment extends Fragment {

    private EditText editTextSearch;
    private RecyclerView rvFeed;
    private BookAdapter adapter;
    private List<Book> filteredList;
    private ProgressBar progressbar;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi komponen
        editTextSearch = view.findViewById(R.id.editTextSearch);
        rvFeed = view.findViewById(R.id.rv_feed);
        progressbar = view.findViewById(R.id.progressBar);

        // Setup RecyclerView dengan Grid Layout
        int numColumns = 2;
        rvFeed.setLayoutManager(new GridLayoutManager(getContext(), numColumns));

        filteredList = new ArrayList<>(MainActivity.bookList);  // Menggunakan bookList dari MainActivity

        // Setup Adapter
        adapter = new BookAdapter(filteredList, new BookAdapter.OnBookClickListener() {
            @Override
            public void onBookClick(Book book) {
                // Menampilkan detail buku
                BookDetailFragment detailsFragment = BookDetailFragment.newInstance(book);
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, detailsFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        rvFeed.setAdapter(adapter);

        // Filter logic untuk pencarian
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterBooks(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

    // Fungsi untuk memfilter buku
    private void filterBooks(final String query) {
        progressbar.setVisibility(View.VISIBLE);
        rvFeed.setVisibility(View.GONE);// Tampilkan progress saat pencarian

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500); // Simulasi delay 500ms
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                List<Book> tempList = new ArrayList<>();

                if (query.isEmpty()) {
                    tempList.addAll(MainActivity.bookList);
                } else {
                    String lowerCaseQuery = query.toLowerCase();
                    for (Book book : MainActivity.bookList) {
                        if (book.getTitle().toLowerCase().contains(lowerCaseQuery)
                                || book.getAuthor().toLowerCase().contains(lowerCaseQuery)) {
                            tempList.add(book);
                        }
                    }
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        filteredList.clear();
                        filteredList.addAll(tempList);
                        adapter.notifyDataSetChanged();
                        rvFeed.setVisibility(View.VISIBLE);// Tampilkan progress saat pencarian
                        progressbar.setVisibility(View.GONE); // Sembunyikan loading
                    }
                });
            }
        });
    }


    public void updateBooksList() {
        filteredList.clear();
        filteredList.addAll(MainActivity.bookList);
        adapter.notifyDataSetChanged();
    }
}