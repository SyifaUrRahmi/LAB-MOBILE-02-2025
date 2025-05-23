package com.example.localsqlite;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.StudentViewHolder> {
    private final ArrayList<Buku> bukus = new ArrayList<>();
    private final Activity activity;

    public BukuAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setStudents(ArrayList<Buku> bukus) {
        this.bukus.clear();
        if (bukus.size() > 0) {
            this.bukus.addAll(bukus);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buku, parent,
                false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.bind(bukus.get(position));
    }

    @Override
    public int getItemCount() {
        return bukus.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {
        final TextView tvJudul, tvDeskripsi, tanggal;

        final CardView cardView;

        StudentViewHolder(View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tanggal);
            tvJudul = itemView.findViewById(R.id.tv_item_judul);
            tvDeskripsi = itemView.findViewById(R.id.tv_item_deskripsi);
            cardView = itemView.findViewById(R.id.card_view);
        }

        void bind(Buku buku) {
            tvJudul.setText(buku.getJudul());
            tvDeskripsi.setText(buku.getDeskripsi());

            String waktu;
            if (buku.getUpdatedAt() != null && !buku.getUpdatedAt().isEmpty()) {
                waktu = "Updated at " + buku.getUpdatedAt();
            } else if (buku.getCreatedAt() != null && !buku.getCreatedAt().isEmpty()) {
                waktu = "Created at " + buku.getCreatedAt();
            } else {
                waktu = "-";
            }
            tanggal.setText(waktu);

            cardView.setOnClickListener(v -> {
                Intent intent = new Intent(activity, FormActivity.class);
                intent.putExtra(FormActivity.EXTRA_BUKU, buku);
                activity.startActivityForResult(intent, FormActivity.REQUEST_UPDATE);
            });
        }
    }

    public void clear() {
        bukus.clear();
        notifyDataSetChanged();
    }
}