package com.example.tp6;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
public class UserAdapter extends
        RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    public List<User> userList;
    public UserAdapter(List<User> userList){
        this.userList = userList;
    }
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder(view);
    }
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user);
    }
    public int getItemCount() {
        return userList.size();
    }
    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView nameTextView;
        private TextView spesiesTextView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.avatarImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            spesiesTextView = itemView.findViewById(R.id.spesiesTextView);


        }
        public void bind(User user) {
            Picasso.get().load(user.getImage()).into(img);
            nameTextView.setText(user.getName());
            spesiesTextView.setText(user.getSpecies());

            itemView.setOnClickListener(v -> {
                // Membuat Intent untuk membuka DetailActivity
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra("user_data", user);  // Mengirim User sebagai Parcelable
                itemView.getContext().startActivity(intent);
            });
        }
    }
}
