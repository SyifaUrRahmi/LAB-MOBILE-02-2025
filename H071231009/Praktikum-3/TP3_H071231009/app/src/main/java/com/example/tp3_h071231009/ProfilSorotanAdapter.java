package com.example.tp3_h071231009;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProfilSorotanAdapter extends RecyclerView.Adapter<ProfilSorotanAdapter.ViewHolder> {

    private Context context;
    private List<ProfilSorotan> profilSorotanList;

    public ProfilSorotanAdapter(Context context, List<ProfilSorotan> profilSorotanList) {
        this.context = context;
        this.profilSorotanList = profilSorotanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sorotanuser_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProfilSorotan profilSorotan = profilSorotanList.get(position);
        holder.imageView.setImageResource(profilSorotan.getImageResIdSorotan());
        holder.textView.setText(profilSorotan.getNamaSorotan());


        holder.frameBorder.setBackgroundResource(R.drawable.bg_border_sorotan);


        ViewGroup.LayoutParams params = holder.imageView.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        holder.imageView.setLayoutParams(params);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] tanggalList = {"18 April 2025", "19 April 2025", "20 April 2025", "21 April 2025"};
                String randomTanggal = tanggalList[(int) (Math.random() * tanggalList.length)];

                Intent intent = new Intent(context, DetailSorotan.class);
                intent.putExtra("foto", profilSorotan.getImageResIdSorotan());
                intent.putExtra("nama", profilSorotan.getNamaSorotan());
                intent.putExtra("tanggal", randomTanggal);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return profilSorotanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        FrameLayout frameBorder;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSorotan);
            imageView.setClipToOutline(true);
            textView = itemView.findViewById(R.id.textSorotan);
            frameBorder = itemView.findViewById(R.id.frameBorder);
        }
    }
}
