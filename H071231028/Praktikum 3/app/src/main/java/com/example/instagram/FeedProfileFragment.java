package com.example.instagram;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class FeedProfileFragment extends Fragment {
    private ImageView content;
    private TextView caption;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_profile, container, false);

        content = view.findViewById(R.id.content);
        caption = view.findViewById(R.id.caption);

        if (getArguments() != null) {
            String imageUri = getArguments().getString("imageUri");
            String captionText = getArguments().getString("caption");

            Glide.with(this).load(Uri.parse(imageUri)).into(content);
            caption.setText(captionText);
        }

        return view;
    }
}
