package com.example.praktikum_6;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    private List<Character> characterList;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean isLoadingAdded = false;
    private boolean hasMoreData = true; // Add flag to track if more data is available

    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public CharacterAdapter(List<Character> characterList) {
        this.characterList = characterList;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_item, parent, false);
            return new CharacterViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_load_more, parent, false);
            return new LoadMoreViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterViewHolder) {
            Character character = characterList.get(position);
            ((CharacterViewHolder) holder).bind(character);
        } else if (holder instanceof LoadMoreViewHolder) {
            LoadMoreViewHolder loadMoreViewHolder = (LoadMoreViewHolder) holder;
            if (hasMoreData) {
                loadMoreViewHolder.btnLoadMore.setVisibility(View.VISIBLE);
                loadMoreViewHolder.progressBar.setVisibility(View.GONE);

                loadMoreViewHolder.btnLoadMore.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (onLoadMoreListener != null) {
                            loadMoreViewHolder.btnLoadMore.setVisibility(View.GONE);
                            loadMoreViewHolder.progressBar.setVisibility(View.VISIBLE);
                            onLoadMoreListener.onLoadMore();
                        }
                    }
                });
            } else {
                // Hide both button and progress bar when no more data
                loadMoreViewHolder.btnLoadMore.setVisibility(View.GONE);
                loadMoreViewHolder.progressBar.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return characterList == null ? 0 : characterList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == characterList.size() - 1 && isLoadingAdded) ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }

    public void addItems(List<Character> characters) {
        characterList.addAll(characters);
        notifyDataSetChanged();
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        notifyItemInserted(characterList.size() - 1);
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = characterList.size() - 1;
        if (position >= 0) {
            Character item = getItem(position);
            if (item == null) {
                characterList.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public void clear() {
        characterList.clear();
        notifyDataSetChanged();
    }

    public void resetLoadingState() {
        notifyItemChanged(characterList.size() - 1);
    }

    private Character getItem(int position) {
        return characterList.get(position);
    }

    // Add method to set the status of more data availability
    public void setHasMoreData(boolean hasMoreData) {
        this.hasMoreData = hasMoreData;
        if (isLoadingAdded) {
            notifyItemChanged(characterList.size() - 1);
        }
    }

    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        private ImageView avatarImageView;
        private TextView nameTextView;
        private TextView speciesTextView;
        private TextView statusTextView;
        private CardView cardView;

        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.iv_avatar);
            nameTextView = itemView.findViewById(R.id.tv_name);
            speciesTextView = itemView.findViewById(R.id.tv_species);
            statusTextView = itemView.findViewById(R.id.tv_status);
            cardView = itemView.findViewById(R.id.card_view);
        }

        public void bind(final Character character) {
            Picasso.get().load(character.getImage()).into(avatarImageView);
            nameTextView.setText(character.getName());
            speciesTextView.setText(character.getSpecies());
            statusTextView.setText(character.getStatus());

            // Set click listener to open detail activity
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra("CHARACTER_ID", character.getId());
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }

    public static class LoadMoreViewHolder extends RecyclerView.ViewHolder {
        private Button btnLoadMore;
        private ProgressBar progressBar;

        public LoadMoreViewHolder(@NonNull View itemView) {
            super(itemView);
            btnLoadMore = itemView.findViewById(R.id.btnLoadMore);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}