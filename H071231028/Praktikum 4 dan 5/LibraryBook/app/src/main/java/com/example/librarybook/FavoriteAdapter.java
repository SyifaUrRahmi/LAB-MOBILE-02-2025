package com.example.librarybook;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavViewHolder> {
    private final Context context;
    private final List<Book> favoriteBooks;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public FavoriteAdapter(Context context, List<Book> favoriteBooks) {
        this.context = context;
        this.favoriteBooks = favoriteBooks;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fav_item, parent, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder holder, int position) {
        Book book = favoriteBooks.get(position);
        holder.titleTextView.setText(book.getTitle());
        holder.authorTextView.setText(book.getAuthor());
        holder.sinopsisTextView.setText(book.getSinopsis());

        Uri imageUri = book.getImageUri();
        if (imageUri != null) {
            holder.coverImageView.setImageURI(imageUri);
        } else {
            holder.coverImageView.setImageResource(R.drawable.cover);
        }

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(book);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteBooks.size();
    }

    static class FavViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, authorTextView, sinopsisTextView;
        ImageView coverImageView;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.book_title);
            authorTextView = itemView.findViewById(R.id.book_author);
            sinopsisTextView = itemView.findViewById(R.id.sinopsis);
            coverImageView = itemView.findViewById(R.id.book_cover);
        }
    }
}
