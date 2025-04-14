package com.example.tp1;

import android.app.Activity;
import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.annotation.Nullable;

import java.io.ByteArrayOutputStream;


public class MainActivity2 extends AppCompatActivity {

    private static final int REQUEST_GALLERY = 1;
    private static final int REQUEST_CAMERA = 2;
    private static final int REQUEST_PERMISSION = 100;

    ImageView imageView;
    TextView username1;

    private Uri imageUri;
    TextView email1;
    TextView nomor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);


        //Tombol Kembali
        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("USERNAME", username1.getText().toString());
            resultIntent.putExtra("EMAIL", email1.getText().toString());
            resultIntent.putExtra("NOMOR", nomor1.getText().toString());

            if (imageUri != null) {
                resultIntent.putExtra("IMAGE_URI", imageUri.toString());
                Toast.makeText(this, "Gambar dari Galeri diterima.", Toast.LENGTH_SHORT).show();
            }

            BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
            if (drawable != null) {
                Bitmap bitmap = drawable.getBitmap();
                if (bitmap != null && imageUri == null) { // Cek agar tidak kirim dua-duanya
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();
                    resultIntent.putExtra("IMAGE_BITMAP", byteArray);
                    Toast.makeText(this, "Gambar dari Kamera diterima.", Toast.LENGTH_SHORT).show();
                }
            }

            setResult(RESULT_OK, resultIntent);
            finish();
        });


        // Intent Ganti Username
        RelativeLayout relativeLayout4 = findViewById(R.id.relativ4);
        relativeLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity2.this, GantiUsername.class);
                String usernameLama= username1.getText().toString();
                intent4.putExtra("USERNAMELAMA", usernameLama);
                startActivityForResult(intent4, 3);
            }
        });

        username1 = findViewById(R.id.username1);
        String username = getIntent().getStringExtra("USERNAME");
        if (username != null && !username.isEmpty()) {
            username1.setText(username);
        } else {
            username1.setText("Pasang");
        }
        // End Intent Ganti Username

        // Intent Ganti Nomor
        nomor1 = findViewById(R.id.gantinomor1);
        RelativeLayout relativeLayout5 = findViewById(R.id.relativ5);
        relativeLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity2", "Ganti Nomor clicked");
                Intent intent5 = new Intent(MainActivity2.this, GantiNomor.class);
                String nomorLama= nomor1.getText().toString();
                intent5.putExtra("NOMOR", nomorLama);
                startActivityForResult(intent5, 4);

            }
        });

        if (savedInstanceState == null) {
            String nomor = getIntent().getStringExtra("NOMOR");
            if (nomor == null) {
                nomor = "0895330153087"; // default
            }
            nomor1.setText(nomor);
        }
        // End Intent Ganti Nomor

        // Intent Ganti Email
        RelativeLayout relativeLayout6 = findViewById(R.id.relativ6);
        email1 = findViewById(R.id.email1);

        relativeLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(MainActivity2.this, GantiEmail.class);

                // Kirim email lama jika sudah ada
                String currentEmail = email1.getText().toString();
                intent6.putExtra("CURRENT_EMAIL", currentEmail);
                startActivityForResult(intent6, 5);
            }
        });

        // Ambil data email dari intent (setelah kembali dari GantiEmail)
        String email = getIntent().getStringExtra("EMAIL");
        if (email != null && !email.isEmpty()) {
            email1.setText(email);
        } else {
            email1.setText("Tambahkan");
        }
        // End Intent Ganti Email

        // Ganti Profil
        RelativeLayout relativeLayout = findViewById(R.id.relativ2);
        imageView = findViewById(R.id.profil4);
        imageView.setClipToOutline(true);

        relativeLayout.setOnClickListener(view -> {
            String[] options = {"Galery", "Camera"};
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
            builder.setTitle("Pilih Sumber Gambar");
            builder.setItems(options, (dialog, which) -> {
                switch (which) {
                    case 0: // Galeri
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    REQUEST_PERMISSION);
                        } else {
                            Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(galleryIntent, REQUEST_GALLERY);
                        }
                        break;

                    case 1: // Foto atau Kamera
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(this,
                                    new String[]{Manifest.permission.CAMERA},
                                    REQUEST_PERMISSION);
                        } else {
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(cameraIntent, REQUEST_CAMERA);
                        }
                        break;
                }
            });

            builder.show();
        });

        String imageUriString = getIntent().getStringExtra("IMAGE_URI");
        byte[] imageBytes = getIntent().getByteArrayExtra("IMAGE_BITMAP");

        if (imageUriString != null && !imageUriString.isEmpty()) {
            // gambar dari galeri
            imageUri = Uri.parse(imageUriString);
            imageView.setImageURI(imageUri);
            imageView.setTag(imageUriString);
        } else if (imageBytes != null && imageBytes.length > 0) {
            // gambar dari kamera
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            imageView.setImageBitmap(bitmap);
            imageView.setTag(null);
        } else {
            imageView.setImageResource(R.drawable.gambar1);
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(this, "onActivityResult dipanggil. requestCode: " + requestCode + ", resultCode: " + resultCode, Toast.LENGTH_LONG).show();
        Toast.makeText(this, "requestCode: " + requestCode + "\ndata: " + (data != null ? "ada" : "null"), Toast.LENGTH_LONG).show();

        Log.d("MainActivity2", "onActivityResult called");
        Log.d("MainActivity2", "requestCode: " + requestCode + ", resultCode: " + resultCode);

        Intent resultIntent = new Intent();

        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case REQUEST_GALLERY:
                    imageUri = data.getData();
                    if (imageUri != null) {
                        imageView.setImageURI(imageUri);
                        imageView.setTag(imageUri.toString());
                        resultIntent.putExtra("IMAGE_URI", imageUri.toString()); // Kirim image URI
                        Toast.makeText(this, "Gambar dari galeryy diterima.", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case REQUEST_CAMERA:
                    imageView.setImageURI(null);
                    imageView.setImageBitmap(null);
                    if (data.getExtras() != null) {
                        Bitmap photo = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(photo);
                        imageUri = null;

                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] byteArray = stream.toByteArray();
                        resultIntent.putExtra("IMAGE_BITMAP", byteArray);
                        Toast.makeText(this, "Gambar dari kamera diterima.", Toast.LENGTH_SHORT).show();
                    }
                    break;

                case 3:
                    String usernameBaru = data.getStringExtra("USERNAME");
                    if (usernameBaru != null && !usernameBaru.isEmpty()) {
                        username1.setText(usernameBaru);
                        resultIntent.putExtra("USERNAME", usernameBaru);
                    }
                    break;

                case 4:
                    String nomorBaru = data.getStringExtra("NOMOR");
                    Log.d("MainActivity2", "Dapat nomorBaru: " + nomorBaru);
                    Toast.makeText(this, "Nomor diterima: " + nomorBaru, Toast.LENGTH_SHORT).show();
                    if (nomorBaru != null && !nomorBaru.isEmpty()) {
                        nomor1.setText(nomorBaru);
                        resultIntent.putExtra("NOMOR", nomorBaru);
                    }
                    break;

                case 5:
                    String emailBaru = data.getStringExtra("EMAIL");
                    if (emailBaru != null && !emailBaru.isEmpty()) {
                        email1.setText(emailBaru);
                        resultIntent.putExtra("EMAIL", emailBaru);
                    }
                    break;
            }


            if (!resultIntent.getExtras().isEmpty()) {
                setResult(RESULT_OK, resultIntent);
            }
        }
    }


}