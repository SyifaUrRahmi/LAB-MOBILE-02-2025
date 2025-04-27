package com.example.tp3_h071231009;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DetailPostAdapter extends RecyclerView.Adapter<DetailPostAdapter.ViewHolder> {

    List<Post> postList;

    public DetailPostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = postList.get(position);

        Uri uri = post.getImageUri();
        if (uri != null && uri.toString() != null && !uri.toString().isEmpty()) {
            Glide.with(holder.fotoFeed.getContext())
                    .load(uri)
                    .placeholder(R.drawable.adel)
                    .error(R.drawable.ucup)
                    .into(holder.fotoFeed);
        } else {
            holder.fotoFeed.setImageResource(post.getImageResId());
        }

        holder.caption.setText(post.getCaption());
        holder.tanggal.setText(post.getDate());
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fotoFeed, foto_profil;
        TextView caption, tanggal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoFeed = itemView.findViewById(R.id.fotofeed);
            caption = itemView.findViewById(R.id.caption);
            tanggal = itemView.findViewById(R.id.tanggal);
            foto_profil = itemView.findViewById(R.id.profilFeed);
            foto_profil.setClipToOutline(true);
        }
    }
}

