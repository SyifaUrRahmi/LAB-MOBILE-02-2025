package com.example.tp3_h071231009;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_SOROTAN = 1;
    private static final int VIEW_TYPE_BAWAH = 2;

    private final Context context;
    private final List<MainItem2> itemList;
    private final List<Post> postList = new ArrayList<>();
    private final PostGridAdapter postGridAdapter;
    private final OnTambahPostClickListener onTambahPostClickListener;

    public MainAdapter2(Context context, List<MainItem2> itemList, OnTambahPostClickListener listener) {
        this.context = context;
        this.itemList = itemList != null ? itemList : new ArrayList<>();
        this.onTambahPostClickListener = listener;
        this.postGridAdapter = new PostGridAdapter(postList, context);

        // Data awal
        postList.add(new Post(R.drawable.contohsorotan, "Caption1", "2024-04-21"));
        postList.add(new Post(R.drawable.sorotan1, "Caption2", "2024-04-11"));
        postList.add(new Post(R.drawable.sorotan2, "Caption3", "2024-04-13"));
        postList.add(new Post(R.drawable.sorotan3, "Caption4", "2024-04-09"));
    }

    public interface OnTambahPostClickListener {
        void onTambahPostClicked();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return itemList.get(position).getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        if (viewType == VIEW_TYPE_HEADER) {
            View view = inflater.inflate(R.layout.item_header_profil, parent, false);
            return new HeaderViewHolder(view, onTambahPostClickListener);
        } else if (viewType == VIEW_TYPE_SOROTAN) {
            View view = inflater.inflate(R.layout.sorotanuser_item_card, parent, false);
            return new ProfilSorotanViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.bagianbawah_item, parent, false);
            return new BawahViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainItem2 item = itemList.get(position);

        if (holder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) holder).imageProfil.setClipToOutline(true);
        } else if (holder instanceof ProfilSorotanViewHolder) {
            List<ProfilSorotan> sorotanList = item.getProfilSorotanList();
            if (sorotanList == null) sorotanList = new ArrayList<>();

            ProfilSorotanAdapter adapter = new ProfilSorotanAdapter(context, sorotanList);
            ProfilSorotanViewHolder sorotanHolder = (ProfilSorotanViewHolder) holder;

            sorotanHolder.recyclerView.setLayoutManager(
                    new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            sorotanHolder.recyclerView.setAdapter(adapter);
        } else if (holder instanceof BawahViewHolder) {
            BawahViewHolder bawahHolder = (BawahViewHolder) holder;
            bawahHolder.recyclerPostGrid.setLayoutManager(new GridLayoutManager(context, 3));
            bawahHolder.recyclerPostGrid.setAdapter(postGridAdapter);
        }
    }

    public void addPost(Post newPost) {
        postList.add(0, newPost);
        postGridAdapter.notifyItemInserted(0);
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        ImageView imageProfil;

        public HeaderViewHolder(@NonNull View itemView, OnTambahPostClickListener listener) {
            super(itemView);
            imageProfil = itemView.findViewById(R.id.imageProfil);

            ImageButton tambahPost = itemView.findViewById(R.id.tambahPost);
            tambahPost.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onTambahPostClicked();
                }
            });
        }
    }

    public static class ProfilSorotanViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public ProfilSorotanViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerSorotanUser);
        }
    }

    public static class BawahViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerPostGrid;

        public BawahViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerPostGrid = itemView.findViewById(R.id.recyclerPostGrid);
        }
    }
}
