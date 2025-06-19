package com.example.booklibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.booklibrary.databinding.FragmentFavoritesBinding;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private FragmentFavoritesBinding binding;
    private BookAdapter bookAdapter;
    private List<Book> favoriteBooks;

    public FavoritesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        BookRepository bookRepository = BookRepository.getInstance();
        favoriteBooks = new ArrayList<>();
        for (Book book : bookRepository.getBooks()) {
            if (book.isFavorite()) {
                favoriteBooks.add(book);
            }
        }


        bookAdapter = new BookAdapter(requireContext(), favoriteBooks, book -> {
            DetailFragment detailFragment = DetailFragment.newInstance(book.getTitle(), book.getDescription(), book.getImageUrl());
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        showLoadingFavorites();

        return binding.getRoot();
    }

    private void showLoadingFavorites() {
        binding.progressBar.setVisibility(View.VISIBLE);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            binding.progressBar.setVisibility(View.GONE);
            binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            binding.favoritesRecyclerView.setAdapter(bookAdapter);
            if (favoriteBooks.isEmpty()) {
                binding.noFavoritesText.setVisibility(View.VISIBLE);
                binding.favoritesRecyclerView.setVisibility(View.GONE);
            } else {
                binding.noFavoritesText.setVisibility(View.GONE);
                binding.favoritesRecyclerView.setVisibility(View.VISIBLE);
            }
        }, 2000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}