package com.example.librarybook;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.io.IOException;

public class AddFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private LinearLayout bookCoverLayout;
    private EditText bookNameEditText, authorNameEditText, sinopsisEditText;
    private ImageButton addButton;
    private Uri selectedImageUri;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false); // Pastikan nama layout benar
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inisialisasi elemen UI
        bookCoverLayout = view.findViewById(R.id.book_cover);
        bookNameEditText = view.findViewById(R.id.bookName);
        authorNameEditText = view.findViewById(R.id.AuthorName);
        sinopsisEditText = view.findViewById(R.id.sinopsis);
        addButton = view.findViewById(R.id.addButton);

        bookCoverLayout.setOnClickListener(v -> openImagePicker());

        addButton.setOnClickListener(v -> {
            String bookName = bookNameEditText.getText().toString().trim();
            String authorName = authorNameEditText.getText().toString().trim();
            String sinopsis = sinopsisEditText.getText().toString().trim();

            if (bookName.isEmpty() || authorName.isEmpty() || sinopsis.isEmpty()) {
                Toast.makeText(getContext(), "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Menambahkan buku ke list di MainActivity
            Book newBook = new Book(bookName, authorName, sinopsis, selectedImageUri);

            MainActivity.bookList.add(newBook);

            // Memperbarui HomeFragment untuk menampilkan buku yang baru ditambahkan
            HomeFragment homeFragment = (HomeFragment) getActivity().getSupportFragmentManager()
                    .findFragmentByTag(HomeFragment.class.getSimpleName());
            if (homeFragment != null) {
                homeFragment.updateBooksList(); // Memperbarui data di HomeFragment
            }

            // Kembali ke HomeFragment dan tampilkan buku yang baru ditambahkan
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        });
    }

    // Fungsi untuk memilih gambar
    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    // Menangani hasil dari pemilihan gambar
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImageUri);
                bookCoverLayout.setBackground(new android.graphics.drawable.BitmapDrawable(getResources(), bitmap));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Menghapus data dari form setelah penambahan
    private void clearFields() {
        bookNameEditText.setText("");
        authorNameEditText.setText("");
        sinopsisEditText.setText("");
        bookCoverLayout.setBackgroundResource(R.drawable.add_item);
    }
}
