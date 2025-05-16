package com.example.tp4_h071231009;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FavoriteFragment extends Fragment {

    private RecyclerView recyclerView;
    private BukuAdapter bukuAdapter;
    private EditText searchInput;
    private Spinner spinnerGenre, spinnerTahun, spinnerRating;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView = view.findViewById(R.id.recycler_main);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        List<Buku> bukuList = Buku.getFavoriteList();
        bukuAdapter = new BukuAdapter(bukuList, buku -> {

        });
        recyclerView.setAdapter(bukuAdapter);

        spinnerGenre = view.findViewById(R.id.spinner_genre);
        spinnerTahun = view.findViewById(R.id.spinner_tahun);
        spinnerRating = view.findViewById(R.id.spinner_rating);
        searchInput = view.findViewById(R.id.search);

        ArrayAdapter<String> genreAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item,
                new String[]{"Genre", "Komedi", "Fantasi", "Romansa", "Aksi", "horor"});
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenre.setAdapter(genreAdapter);

        ArrayAdapter<String> tahunAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item,
                new String[]{"Tahun", "Terbaru", "Terlama"});
        tahunAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTahun.setAdapter(tahunAdapter);

        ArrayAdapter<String> ratingAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item,
                new String[]{"Rating", "Tertinggi", "Terendah"});
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRating.setAdapter(ratingAdapter);

        AdapterView.OnItemSelectedListener filterListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View itemView, int position, long id) {
                filterData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        spinnerGenre.setOnItemSelectedListener(filterListener);
        spinnerTahun.setOnItemSelectedListener(filterListener);
        spinnerRating.setOnItemSelectedListener(filterListener);

        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                filterData();
            }
        });

        return view;
    }

    private void filterData() {
        String genre = spinnerGenre.getSelectedItem().toString();
        String tahunSort = spinnerTahun.getSelectedItem().toString();
        String ratingSort = spinnerRating.getSelectedItem().toString();
        String searchText = searchInput.getText().toString().toLowerCase().trim();

        List<Buku> allBooks = Buku.getFavoriteList();
        List<Buku> filtered = new ArrayList<>();

        for (Buku buku : allBooks) {
            boolean matchGenre = genre.equals("Genre") || buku.getGenre().equalsIgnoreCase(genre);
            boolean matchSearch = searchText.isEmpty()
                    || buku.getJudul().toLowerCase().contains(searchText)
                    || buku.getPenulis().toLowerCase().contains(searchText);

            if (matchGenre && matchSearch) {
                filtered.add(buku);
            }
        }

        if (tahunSort.equals("Terbaru")) {
            Collections.sort(filtered, (a, b) -> Integer.compare(b.getTahunTerbit(), a.getTahunTerbit()));
        } else if (tahunSort.equals("Terlama")) {
            Collections.sort(filtered, (a, b) -> Integer.compare(a.getTahunTerbit(), b.getTahunTerbit()));
        }

        if (ratingSort.equals("Tertinggi")) {
            Collections.sort(filtered, (a, b) -> Double.compare(b.getRating(), a.getRating()));
        } else if (ratingSort.equals("Terendah")) {
            Collections.sort(filtered, (a, b) -> Double.compare(a.getRating(), b.getRating()));
        }

        bukuAdapter.setFilteredList(filtered);
    }
    @Override
    public void onResume() {
        super.onResume();
        updateFavoriteList();
    }


    public void updateFavoriteList() {
        List<Buku> updatedList = Buku.getFavoriteList();
        bukuAdapter.setFilteredList(updatedList);
    }

}
