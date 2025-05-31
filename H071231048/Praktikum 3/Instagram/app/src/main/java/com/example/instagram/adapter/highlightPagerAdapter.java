package com.example.instagram.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.instagram.R;
import com.example.instagram.model.PhotoModel;

import java.util.List;

public class highlightPagerAdapter extends RecyclerView.Adapter<highlightPagerAdapter.ViewHolder> {

    private final Context context;
    private final List<PhotoModel> photos;

    public highlightPagerAdapter(Context context, List<PhotoModel> photos) {
        this.context = context;
        this.photos = photos;
    }

    @NonNull
    @Override
    public highlightPagerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_highlight, parent, false);
        return new highlightPagerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull highlightPagerAdapter.ViewHolder holder, int position) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;

        PhotoModel photo = photos.get(position);

        if (photo.getImageUrl() != null) {
            Glide.with(context)
                    .asBitmap()
                    .load(photo.getImageUrl())
                    .into(new CustomTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap bitmap, @Nullable Transition<? super Bitmap> transition) {
                            setImageWithAspectRatio(holder.imageView, bitmap);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            // ini cuma buat ngga error
                        }
                    });

        } else if (photo.getImageRes() != 0) {
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), photo.getImageRes(), options);
            if (bitmap != null) {
                setImageWithAspectRatio(holder.imageView, bitmap);
            }
        }
    }

    private void setImageWithAspectRatio(ImageView imageView, Bitmap bitmap) {
        int imgWidth = bitmap.getWidth();
        int imgHeight = bitmap.getHeight();
        float aspectRatio = (float) imgHeight / imgWidth;

        int screenWidth = context.getResources().getDisplayMetrics().widthPixels;
        int calculatedHeight = (int) (screenWidth * aspectRatio);

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = calculatedHeight;
        imageView.setLayoutParams(params);

        imageView.setImageBitmap(bitmap);
    }



    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.highlightFullImage);
        }
    }
}
