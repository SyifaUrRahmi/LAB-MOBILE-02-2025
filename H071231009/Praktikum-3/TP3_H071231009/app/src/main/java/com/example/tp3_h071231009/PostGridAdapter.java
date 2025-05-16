package com.example.tp3_h071231009;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostGridAdapter extends RecyclerView.Adapter<PostGridAdapter.PostViewHolder> {

    List<Post> postList;

    Context context;

    public PostGridAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.postgrid, parent, false);
        return new PostViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post post = postList.get(position);

        if (post.getImageResId() != 0) {
            holder.imageView.setImageResource(post.getImageResId());
        } else if (post.getImageUri() != null) {
            holder.imageView.setImageURI(post.getImageUri());
        } else {
            holder.imageView.setImageResource(R.drawable.contohsorotan);
        }

        holder.imageView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailPost.class);
            intent.putParcelableArrayListExtra("postList", new ArrayList<>(postList));
            intent.putExtra("clickedPosition", holder.getAdapterPosition());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.post);
        }
    }
}
