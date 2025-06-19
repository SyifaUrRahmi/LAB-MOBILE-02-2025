package com.example.librarybook;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoriteAdapter adapter;
    private List<Book> favoriteBooks = new ArrayList<>();
    private TextView emptyView;
    private ProgressBar progressbar;

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.rv_fav);
        emptyView = view.findViewById(R.id.empty_view);
        progressbar = view.findViewById(R.id.progressBar);
        progressbar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new FavoriteAdapter(requireContext(), favoriteBooks);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(book -> {
            Fragment detailFragment = BookDetailFragment.newInstance(book);
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Mulai background loading saat onCreateView

        executor.execute(() -> {
            try {
                Thread.sleep(1000); // simulasi delay loading
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<Book> newFavorites = BookManager.getFavoriteBooks();

            handler.post(() -> {
                favoriteBooks.clear();
                favoriteBooks.addAll(newFavorites);
                adapter.notifyDataSetChanged();

                progressbar.setVisibility(View.GONE);

                if (favoriteBooks.isEmpty()) {
                    emptyView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        progressbar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);

        executor.execute(() -> {
            try {
                Thread.sleep(1000); // simulasi delay loading
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List<Book> newFavorites = BookManager.getFavoriteBooks();

            handler.post(() -> {
                favoriteBooks.clear();
                favoriteBooks.addAll(newFavorites);
                adapter.notifyDataSetChanged();

                progressbar.setVisibility(View.GONE);

                if (favoriteBooks.isEmpty()) {
                    emptyView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                }
            });
        });
    }
}
