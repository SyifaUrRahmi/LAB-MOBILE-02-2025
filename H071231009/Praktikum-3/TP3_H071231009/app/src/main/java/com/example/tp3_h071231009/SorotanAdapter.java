package com.example.tp3_h071231009;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SorotanAdapter extends RecyclerView.Adapter<SorotanAdapter.ViewHolder> {

    private Context context;
    private List<Sorotan> sorotanList;

    public SorotanAdapter(Context context, List<Sorotan> sorotanList) {
        this.context = context;
        this.sorotanList = sorotanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sorotan_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Sorotan sorotan = sorotanList.get(position);
        holder.imageView.setImageResource(sorotan.getImageResId());
        holder.textView.setText(sorotan.getNama());


        if (position == 0) {
            holder.iconTambah.setVisibility(View.VISIBLE);
            holder.frameBorder.setBackground(null);

        } else {
            holder.iconTambah.setVisibility(View.GONE);
            holder.frameBorder.setBackgroundResource(R.drawable.bg_border_story);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String[] tanggalList = {"18 April 2025", "19 April 2025", "20 April 2025", "21 April 2025"};
                    String randomTanggal = tanggalList[(int) (Math.random() * tanggalList.length)];

                    Intent intent = new Intent(context, DetailSorotan.class);
                    intent.putExtra("foto", sorotan.getImageResId());
                    intent.putExtra("foto2", sorotan.getImageResId());
                    intent.putExtra("nama", sorotan.getNama());
                    intent.putExtra("tanggal", randomTanggal);
                    context.startActivity(intent);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return sorotanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, iconTambah;
        TextView textView;
        FrameLayout frameBorder;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSorotan);
            imageView.setClipToOutline(true);
            textView = itemView.findViewById(R.id.textSorotan);
            iconTambah = itemView.findViewById(R.id.iconTambah);
            frameBorder = itemView.findViewById(R.id.frameBorder);
        }
    }

    private int dpToPx(int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round(dp * density);
    }

}
