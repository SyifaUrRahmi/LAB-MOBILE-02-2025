package com.example.librarybook;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class BookDetailFragment extends Fragment {
    private static final String ARG_BOOK = "book";

    public static BookDetailFragment newInstance(Book book) {
        BookDetailFragment fragment = new BookDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_BOOK, book); // Menyimpan objek buku ke dalam bundle
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_details, container, false);

        Book book = (Book) getArguments().getSerializable(ARG_BOOK);

        TextView titleTextView = view.findViewById(R.id.book_title);
        TextView authorTextView = view.findViewById(R.id.book_author);
        TextView sinopsisTextView = view.findViewById(R.id.sinopsis);
        ImageView coverImageView = view.findViewById(R.id.book_cover);
        ImageButton favButton = view.findViewById(R.id.addButton);

        if (book != null) {
            titleTextView.setText(book.getTitle());
            authorTextView.setText(book.getAuthor());
            sinopsisTextView.setText(book.getSinopsis());

            Uri imageUri = book.getImageUri();
            if (imageUri != null) {
                coverImageView.setImageURI(imageUri);
            } else {
                coverImageView.setImageResource(R.drawable.cover);
            }


            boolean isFavorite = BookManager.isFavorite(book);
            updateFavButton(favButton, isFavorite);

            favButton.setOnClickListener(v -> {
                if (BookManager.isFavorite(book)) {
                    BookManager.removeFavorite(book);
                    updateFavButton(favButton, false);
                } else {
                    BookManager.addFavorite(book);
                    updateFavButton(favButton, true);
                }


            });
        }

        return view;
    }

    private void updateFavButton(ImageButton button, boolean isFavorite) {
        if (isFavorite) {
            button.setImageResource(R.drawable.deletebutton); // ikon hapus
        } else {
            button.setImageResource(R.drawable.addbutton); // ikon tambah
        }
    }


    }
