package com.example.librarybook;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import android.net.Uri;
import android.widget.ImageView;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> favoriteBooks ;
    private OnBookClickListener listener;

    public interface OnBookClickListener {
        void onBookClick(Book book);
    }

    public BookAdapter(List<Book> bookList, OnBookClickListener listener) {
        this.favoriteBooks  = bookList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = favoriteBooks .get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return favoriteBooks .size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView authorTextView;
        private ImageView coverImageView; // Tambahan

        public BookViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.book_title);
            authorTextView = itemView.findViewById(R.id.book_author);
            coverImageView = itemView.findViewById(R.id.image_cover);
            // Tambahan
        }

        public void bind(final Book book) {
            titleTextView.setText(book.getTitle());
            authorTextView.setText(book.getAuthor());

            // Tampilkan gambar dari URI
            if (book.getImageUri() != null) {
                coverImageView.setImageURI(book.getImageUri());
            } else {
                coverImageView.setImageResource(R.drawable.cover);
            }

            itemView.setOnClickListener(v -> listener.onBookClick(book));
        }
    }
}