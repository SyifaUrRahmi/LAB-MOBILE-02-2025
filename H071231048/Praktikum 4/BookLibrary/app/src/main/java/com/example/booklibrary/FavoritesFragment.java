package com.example.booklibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);

        // Get favorite books from the repository
        BookRepository bookRepository = BookRepository.getInstance();
        favoriteBooks = new ArrayList<>();
        for (Book book : bookRepository.getBooks()) {
            if (book.isFavorite()) {
                favoriteBooks.add(book);
            }
        }

        // Show "No Favorite Books" message if the list is empty
        if (favoriteBooks.isEmpty()) {
            binding.noFavoritesText.setVisibility(View.VISIBLE);
            binding.favoritesRecyclerView.setVisibility(View.GONE);
        } else {
            binding.noFavoritesText.setVisibility(View.GONE);
            binding.favoritesRecyclerView.setVisibility(View.VISIBLE);
        }

        // Set up RecyclerView
        bookAdapter = new BookAdapter(requireContext(), favoriteBooks, book -> {
            DetailFragment detailFragment = DetailFragment.newInstance(book.getTitle(), book.getDescription(), book.getImageUrl());
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        binding.favoritesRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.favoritesRecyclerView.setAdapter(bookAdapter);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}