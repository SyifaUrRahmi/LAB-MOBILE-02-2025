package com.example.booklibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.booklibrary.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private BookAdapter bookAdapter;
    private List<Book> bookList;
    private BookRepository bookRepository;

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        bookRepository = BookRepository.getInstance();
        bookList = bookRepository.getBooks();

        bookAdapter = new BookAdapter(getContext(), bookList, book -> {

            DetailFragment detailFragment = DetailFragment.newInstance(book.getTitle(), book.getDescription(), book.getImageUrl());
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });


        binding.bookRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.bookRecyclerView.setAdapter(bookAdapter);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterBooks(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterBooks(newText);
                return true;
            }
        });

        bookAdapter.notifyDataSetChanged();

        return binding.getRoot();
    }

    private void showLoadingAndFilter(String query) {
        binding.progressBar.setVisibility(View.VISIBLE);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            filterBooks(query);
            binding.progressBar.setVisibility(View.GONE);
        }, 2000);
    }

    private void filterBooks(String query) {
        List<Book> filteredList = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(book);
            }
        }
        bookAdapter.filterList(filteredList);
    }
}