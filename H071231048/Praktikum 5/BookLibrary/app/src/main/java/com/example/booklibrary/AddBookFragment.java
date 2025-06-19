package com.example.booklibrary;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.booklibrary.databinding.FragmentAddBookBinding;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddBookFragment extends Fragment {

    private FragmentAddBookBinding binding;
    private Uri selectedImageUri;
    private ExecutorService executorService;

    public AddBookFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        executorService = Executors.newCachedThreadPool();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBookBinding.inflate(inflater, container, false);

        binding.selectImageButton.setOnClickListener(v -> openImagePicker());

        binding.postImageButton.setOnClickListener(v -> postImage());

        return binding.getRoot();
    }

    private void postImage() {
        String title = binding.bookTitleInput.getText().toString().trim();
        String author = binding.bookAuthorInput.getText().toString().trim();
        String description = binding.bookDescriptionInput.getText().toString().trim();




        if (selectedImageUri == null) {
            Toast.makeText(requireContext(), "Please select an image", Toast.LENGTH_SHORT).show();
            return;
        }

        if (title.isEmpty() || description.isEmpty()) {
            Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double rating;
        try {
            rating = Double.parseDouble(binding.bookTitleRating.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(requireContext(), "Invalid rating", Toast.LENGTH_SHORT).show();
            return;
        }

        executorService.execute( () -> {
            BookRepository bookRepository = BookRepository.getInstance();
            bookRepository.addBook(new Book(
                    title,
                    author,
                    description,
                    2023,
                    "Unknown Genre",
                    selectedImageUri.toString(),
                    rating
            ));
        });

        requireActivity().runOnUiThread(() -> {
            Toast.makeText(requireContext(), "Book added successfully!", Toast.LENGTH_SHORT).show();
            clearInputs();


            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new HomeFragment())
                    .commit();
        });

    }

    private void clearInputs() {
        binding.bookTitleInput.setText("");
        binding.bookAuthorInput.setText("");
        binding.bookDescriptionInput.setText("");
        binding.bookTitleRating.setText("");
        binding.selectedImageView.setImageResource(R.drawable.cet); // Set to a placeholder image
        selectedImageUri = null;
    }

    private void openImagePicker() {
        imagePickerLauncher.launch("image/*");
    }

    private final ActivityResultLauncher<String> imagePickerLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    selectedImageUri = uri;
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uri);
                        binding.selectedImageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Toast.makeText(requireContext(), "Failed to load image", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        executorService.shutdown();
    }
}