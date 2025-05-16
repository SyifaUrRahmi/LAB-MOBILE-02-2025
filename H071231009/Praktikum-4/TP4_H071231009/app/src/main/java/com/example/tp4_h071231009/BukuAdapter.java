package com.example.tp4_h071231009;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.BukuViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Buku buku);
    }

    private List<Buku> bukuList;
    private final OnItemClickListener listener;

    public BukuAdapter(List<Buku> bukuList, OnItemClickListener listener) {
        this.bukuList = bukuList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public BukuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_buku, parent, false);
        return new BukuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BukuViewHolder holder, int position) {
        Buku buku = bukuList.get(position);
        holder.bind(buku, listener);

        // Set bintang
        ImageView[] stars = {
                holder.bintang1,
                holder.bintang2,
                holder.bintang3,
                holder.bintang4,
                holder.bintang5
        };
        setRatingStars(buku.getRating().floatValue(), stars);

    }

    @Override
    public int getItemCount() {
        return bukuList.size();
    }

    public void updateList(List<Buku> newList) {
        bukuList = newList;
        notifyDataSetChanged();
    }

    static class BukuViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBuku, bintang1, bintang2, bintang3, bintang4, bintang5;
        TextView judul, penulis;

        BukuViewHolder(View itemView) {
            super(itemView);
            imgBuku = itemView.findViewById(R.id.img_buku);
            judul = itemView.findViewById(R.id.judul);
            penulis = itemView.findViewById(R.id.penulis);

            bintang1 = itemView.findViewById(R.id.bintang1);
            bintang2 = itemView.findViewById(R.id.bintang2);
            bintang3 = itemView.findViewById(R.id.bintang3);
            bintang4 = itemView.findViewById(R.id.bintang4);
            bintang5 = itemView.findViewById(R.id.bintang5);

        }

        void bind(final Buku buku, final OnItemClickListener listener) {

            if (buku.getGambar() != 0) {
                imgBuku.setImageResource(buku.getGambar());
            } else if (buku.getUriGambar() != null) {
                imgBuku.setImageURI(buku.getUri());
            }


            judul.setText(buku.getJudul());
            penulis.setText(buku.getPenulis());

            itemView.setOnClickListener(v -> listener.onItemClick(buku));

            imgBuku.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class );
                intent.putExtra("buku", buku);
                itemView.getContext().startActivity(intent);


            });
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

    public void setFilteredList(List<Buku> filteredList) {
        this.bukuList = filteredList;
        notifyDataSetChanged();
    }

}
