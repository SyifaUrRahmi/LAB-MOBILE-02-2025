package com.example.booklibrary;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.booklibrary.databinding.ItemBookBinding;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> bookList;  // Jangan inisialisasi di sini
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }

    public BookAdapter(Context context, List<Book> bookList, OnItemClickListener listener) {
        this.context = context;
        // Pastikan bookList tidak null
        this.bookList = bookList != null ? bookList : new ArrayList<>();
        this.listener = listener;
    }

    public void filterList(List<Book> filteredList) {
        this.bookList = filteredList != null ? filteredList : new ArrayList<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        ItemBookBinding binding = ItemBookBinding.inflate(inflater, parent, false);
        return new BookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.binding.bookAuthor.setText(book.getAuthor());
        holder.binding.bookTitle.setText(book.getTitle());

        holder.binding.progressBar.setVisibility(View.VISIBLE);

        // Pastikan Glide bekerja dengan benar dan tidak crash
        Glide.with(context)
                .load(book.getImageUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        holder.binding.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource, @NonNull Object model, Target<Drawable> target, @NonNull DataSource dataSource, boolean isFirstResource) {
                        holder.binding.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                        .into(holder.binding.bookCover);

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(book);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    static class BookViewHolder extends RecyclerView.ViewHolder {
        ItemBookBinding binding;

        public BookViewHolder(@NonNull ItemBookBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
