package com.example.booklibrary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.booklibrary.databinding.FragmentDetailBinding;

public class DetailFragment extends Fragment {

    private static final String ARG_TITLE = "title";
    private static final String ARG_DESC = "description";
    private static final String ARG_IMAGE = "imageUrl";
    private String title;
    private String description;
    private String imageUrl;
    private double rating;
    private boolean isFavorite;

    private FragmentDetailBinding binding;

    public DetailFragment() {
        // Required empty public constructor
    }

//    public static DetailFragment newInstance(String title, String description) {
//        DetailFragment fragment = new DetailFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_TITLE, title);
//        args.putString(ARG_DESC, description);
//        fragment.setArguments(args);
//        return fragment;
//    }

    public static DetailFragment newInstance(String title, String description, String imageUrl) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESC, description);
        args.putString(ARG_IMAGE, imageUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESC);
            imageUrl = getArguments().getString(ARG_IMAGE); // boleh null
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);

        BookRepository bookRepository = BookRepository.getInstance(); // Use singleton instance
        for (Book book : bookRepository.getBooks()) {
            if (book.getTitle().equals(title)) {
                description = book.getDescription();
                imageUrl = book.getImageUrl();
                rating = book.getRating();
                isFavorite = book.isFavorite();
                break;
            }
        }

        if (isFavorite) {
            binding.bookFavorited.setImageResource(R.drawable.ic_saved_active);
        } else {
            binding.bookFavorited.setImageResource(R.drawable.ic_saved_unactive);
        }

        binding.bookFavorited.setOnClickListener(v -> {
            isFavorite = !isFavorite;
            binding.bookFavorited.setImageResource(isFavorite ? R.drawable.ic_saved_active : R.drawable.ic_saved_unactive);
            bookRepository.updateFavoriteStatus(title, isFavorite);
        });

        binding.bookDescription.setText(description);
        binding.bookRating.setText(String.valueOf(rating));
        binding.bookTitle.setText(title);
        binding.bookAuthor.setText(description);

        if (imageUrl != null) {
            Glide.with(requireContext())
                    .load(imageUrl)
                    .into(binding.bookCover);
        }

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
