package com.example.tp4_h071231009;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class TambahFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private ImageButton imageButton;
    private EditText etJudul, etPenulis, etTahunTerbit, etRating, etDeskripsi;
    private Spinner spinnerGenre;
    private Button btnSimpan;
    private Uri selectedImageUri;

    public TambahFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tambah, container, false);

        imageButton = view.findViewById(R.id.img_buku);
        etJudul = view.findViewById(R.id.judul);
        etPenulis = view.findViewById(R.id.penulis);
        etTahunTerbit = view.findViewById(R.id.tahunTerbit);
        etRating = view.findViewById(R.id.rating);
        etDeskripsi = view.findViewById(R.id.deskripsi);
        spinnerGenre = view.findViewById(R.id.genre);
        btnSimpan = view.findViewById(R.id.btn_simpan);

        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item,
                new String[]{"Genre", "Komedi", "Fantasi", "Romansa", "Aksi", "horor"});
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(genreAdapter);

        imageButton.setOnClickListener(v -> openGallery());

        btnSimpan.setOnClickListener(v -> {
            String judul = etJudul.getText().toString();
            String penulis = etPenulis.getText().toString();
            String tahunStr = etTahunTerbit.getText().toString();
            String ratingStr = etRating.getText().toString();
            String deskripsi = etDeskripsi.getText().toString();
            String genre = spinnerGenre.getSelectedItem().toString();

            if (judul.isEmpty() || penulis.isEmpty() || tahunStr.isEmpty() ||
                    ratingStr.isEmpty() || deskripsi.isEmpty() || genre.equals("Genre") || selectedImageUri == null) {
                Toast.makeText(getContext(), "Semua data harus diisi", Toast.LENGTH_SHORT).show();
                return;
            }
            int tahun = Integer.parseInt(tahunStr);
            double rating = Double.parseDouble(ratingStr);
            int tahunSekarang = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

            if (rating < 1 || rating > 5) {
                Toast.makeText(getContext(), "Rating harus antara 1 sampai 5", Toast.LENGTH_SHORT).show();
                return;
            }

            if (tahun > tahunSekarang) {
                Toast.makeText(getContext(), "Tahun tidak boleh lebih dari tahun sekarang", Toast.LENGTH_SHORT).show();
                return;
            }

            Buku buku = new Buku(judul,penulis, tahun, deskripsi, genre, rating, selectedImageUri.toString());
            Buku.addBook(buku);

            Toast.makeText(getContext(), "Buku berhasil ditambahkan", Toast.LENGTH_SHORT).show();

            etJudul.setText("");
            etPenulis.setText("");
            etTahunTerbit.setText("");
            etRating.setText("");
            etDeskripsi.setText("");
            imageButton.setImageResource(R.drawable.baseline_add_photo_alternate_24);
            selectedImageUri = null;

            HomeFragment homeFragment = new HomeFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return view;
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImageUri);
                imageButton.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
