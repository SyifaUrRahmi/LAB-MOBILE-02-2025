package com.example.librarybook;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Membuat bookList static agar bisa diakses dari fragment manapun
    public static List<Book> bookList = new ArrayList<>();

    // Buat di MainActivity.java


    private ImageView homeImg, addImg, favImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // data dummy
        if (bookList.isEmpty()) {
            Uri image1 = Uri.parse("android.resource://com.example.librarybook/drawable/cover1");
            Uri image2 = Uri.parse("android.resource://com.example.librarybook/drawable/cover2");
            Uri image3 = Uri.parse("android.resource://com.example.librarybook/drawable/cover3");
            Uri image4 = Uri.parse("android.resource://com.example.librarybook/drawable/cover4");
            Uri image5 = Uri.parse("android.resource://com.example.librarybook/drawable/cover5");
            Uri image6 = Uri.parse("android.resource://com.example.librarybook/drawable/cover6");

            bookList.add(new Book("Aktif dan Kreatif", "Paulo Coelho", "Ayo belajar bermain dan berkreasi! Buku ini mengajak kamu untuk selalu aktif dan penuh ide kreatif.", image1));
            bookList.add(new Book("Hasil Karyaku", "J.Katty", "Buku penuh warna yang mengajarkan cara membuat karya-karya keren dengan tanganmu sendiri.", image2));
            bookList.add(new Book("Buku Aktivitas", "James Clear", "Serangkaian aktivitas seru yang akan membuatmu senang belajar sambil bermain.", image3));
            bookList.add(new Book("Pintar Berhitung", "Charles Duhigg", "Yuk, belajar berhitung lewat permainan seru! Buku ini akan membuatmu jadi jago berhitung.", image4));
            bookList.add(new Book("Terampil Menulis ", "Mark Manson", "Buku ini membantu kamu belajar menulis huruf dengan cara yang menyenankan dan mudah.", image5));
            bookList.add(new Book("Berhitung", "Tara Westover", "Seru belajar angka dan berhitung dengan cerita-cerita menyenankan!", image6));

        }


        // Inisialisasi elemen-elemen UI
        homeImg = findViewById(R.id.homeImg);
        addImg = findViewById(R.id.addImg);
        favImg = findViewById(R.id.postImg);

        // Set listener untuk Home icon
        homeImg.setOnClickListener(v -> replaceFragment(new HomeFragment()));

        // Set listener untuk Add icon
        addImg.setOnClickListener(v -> replaceFragment(new AddFragment()));

        favImg.setOnClickListener(view -> replaceFragment(new FavoriteFragment()));

        // Menampilkan HomeFragment pertama kali ketika aplikasi dijalankan
        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
        }
    }

    // Fungsi untuk mengganti fragment
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);  // Menambahkan ke back stack
        transaction.commit();  // Commit perubahan
    }
}
