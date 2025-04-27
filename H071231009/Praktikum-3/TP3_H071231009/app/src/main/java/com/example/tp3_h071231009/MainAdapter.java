package com.example.tp3_h071231009;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_SOROTAN = 1;
    private static final int VIEW_TYPE_FEED = 2;

    private final Context context;
    private final List<MainItem> itemList;
    private final OnProfilClickListener profilClickListener;

    public interface OnProfilClickListener {
        void onProfilClicked(Feed feed);
    }

    public MainAdapter(Context context, List<MainItem> itemList, OnProfilClickListener profilClickListener ) {
        this.context = context;
        this.itemList = itemList != null ? itemList : new ArrayList<>();
        this.profilClickListener = profilClickListener;
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
            View view = inflater.inflate(R.layout.item_header, parent, false);
            return new HeaderViewHolder(view);
        } else if (viewType == VIEW_TYPE_SOROTAN) {
            View view = inflater.inflate(R.layout.sorotan_item_card, parent, false);
            return new SorotanViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.feed_item, parent, false);
            return new FeedViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MainItem item = itemList.get(position);

        if (holder instanceof HeaderViewHolder) {

        } else if (holder instanceof SorotanViewHolder) {
            List<Sorotan> sorotanList = item.getSorotanList();
            if (sorotanList == null) sorotanList = new ArrayList<>();

            SorotanAdapter adapter = new SorotanAdapter(context, sorotanList);
            SorotanViewHolder sorotanHolder = (SorotanViewHolder) holder;
            sorotanHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            sorotanHolder.recyclerView.setAdapter(adapter);
        } else if (holder instanceof FeedViewHolder) {
            Feed feed = item.getFeed();
            if (feed != null) {
                ((FeedViewHolder) holder).bind(feed, profilClickListener);
            }
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public static class SorotanViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public SorotanViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.recyclerSorotan);
        }
    }

    public static class FeedViewHolder extends RecyclerView.ViewHolder {
        ImageButton profilFeed;
        TextView username;
        ImageView fotoFeed;
        TextView suka, usernameCaption, caption, tanggal;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);
            profilFeed = itemView.findViewById(R.id.profilFeed);
            profilFeed.setClipToOutline(true);
            username = itemView.findViewById(R.id.username);
            fotoFeed = itemView.findViewById(R.id.fotofeed);
            fotoFeed.setClipToOutline(true);
            usernameCaption = itemView.findViewById(R.id.usernameCaption);
            caption = itemView.findViewById(R.id.caption);
            tanggal = itemView.findViewById(R.id.tanggal);
        }
        public void bind(Feed feed, OnProfilClickListener listener) {
            profilFeed.setImageResource(feed.getImageProfilResId());
            fotoFeed.setImageResource(feed.getImageFotoFeedResId());
            username.setText(feed.getUsername());
            usernameCaption.setText(feed.getUsernameCaption());
            caption.setText(feed.getCaption());
            tanggal.setText(feed.getTanggal());

            View.OnClickListener clickListener = v -> {
                if (listener != null) {
                    listener.onProfilClicked(feed);
                }
            };

            profilFeed.setOnClickListener(clickListener);
            username.setOnClickListener(clickListener);
        }
    }


}
