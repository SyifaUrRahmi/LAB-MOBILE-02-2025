package com.example.tp4_h071231009;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private ImageView imageView, kembali, bintang1, bintang2, bintang3, bintang4, bintang5;
    private TextView judul, penulis, tahunTerbit, genre, rating, blurb;
    private Button btnTambah;

    private Buku buku;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inisialisasi View
        bintang1 = findViewById(R.id.bintang1);
        bintang2 = findViewById(R.id.bintang2);
        bintang3 = findViewById(R.id.bintang3);
        bintang4 = findViewById(R.id.bintang4);
        bintang5 = findViewById(R.id.bintang5);
        kembali = findViewById(R.id.kembali);
        imageView = findViewById(R.id.img_buku);
        judul = findViewById(R.id.judul);
        penulis = findViewById(R.id.penulis);
        tahunTerbit = findViewById(R.id.tahunTerbit);
        genre = findViewById(R.id.genre);
        rating = findViewById(R.id.rating);
        blurb = findViewById(R.id.blurb);
        btnTambah = findViewById(R.id.btnTambah);

        kembali.setOnClickListener(v -> finish());

        buku = getIntent().getParcelableExtra("buku");

        if (buku != null) {
            if (buku.getGambar() != 0) {
                imageView.setImageResource(buku.getGambar());
            } else if (buku.getUriGambar() != null) {
                imageView.setImageURI(buku.getUri());
            }

            judul.setText(buku.getJudul());
            penulis.setText(buku.getPenulis());
            tahunTerbit.setText(String.valueOf(buku.getTahunTerbit()));
            genre.setText(buku.getGenre());
            rating.setText(String.valueOf(buku.getRating()));
            blurb.setText(buku.getBlurb());

            ImageView[] stars = {bintang1, bintang2, bintang3, bintang4, bintang5};
            setRatingStars(buku.getRating().floatValue(), stars);

            updateButtonText();

            btnTambah.setOnClickListener(v -> {
                if (Buku.isFavorite(buku)) {
                    Buku.removeFavorite(buku);
                } else {
                    Buku.addFavorite(buku);
                }
                updateButtonText();
            });
        }
    }

    private void updateButtonText() {
        if (Buku.isFavorite(buku)) {
            btnTambah.setText("Hapus Favorit");
        } else {
            btnTambah.setText("Tambah ke Favorit");
        }
    }

    private void setRatingStars(float rating, ImageView[] stars) {
        for (int i = 0; i < stars.length; i++) {
            if (rating >= i + 1) {
                stars[i].setImageResource(R.drawable.bintang_full);
            } else if (rating >= i + 0.5) {
                stars[i].setImageResource(R.drawable.bintang_setengah);
            } else {
                stars[i].setImageResource(R.drawable.bintang_kosong);
            }
        }
    }
}
