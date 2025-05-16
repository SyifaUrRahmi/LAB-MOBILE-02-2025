package com.example.tp6;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private ImageView img, loadingIndicator;
    private TextView name, species, status, gender;
    private View contentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        img = findViewById(R.id.img);
        name = findViewById(R.id.nama);
        status = findViewById(R.id.status);
        species = findViewById(R.id.species);
        gender = findViewById(R.id.gender);
        loadingIndicator = findViewById(R.id.loading_indicator);
        contentContainer = findViewById(R.id.content_container);

        // Pastikan hanya loading yang terlihat
        loadingIndicator.setVisibility(View.VISIBLE);
        contentContainer.setVisibility(View.GONE);

        // Tampilkan loading gif
        Glide.with(this)
                .asGif()
                .load(R.drawable.loading)
                .into(loadingIndicator);

        User user = getIntent().getParcelableExtra("user_data");

        // Simulasi proses background
        new Thread(() -> {
            try {
                Thread.sleep(1500); // Delay simulasi
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            runOnUiThread(() -> {
                loadingIndicator.setVisibility(View.GONE);

                if (user != null) {
                    contentContainer.setVisibility(View.VISIBLE);

                    name.setText(user.getName());
                    species.setText(user.getSpecies());
                    Picasso.get().load(user.getImage()).into(img);
                    status.setText(user.getStatus());
                    gender.setText(user.getGender());
                }
            });
        }).start();
    }
}
