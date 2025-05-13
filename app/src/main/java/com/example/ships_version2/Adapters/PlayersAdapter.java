package com.example.ships_version2.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ships_version2.R;
import com.example.ships_version2.databinding.UserBinding;
import com.example.ships_version2.user;

import java.util.ArrayList;
import java.util.List;

public class PlayersAdapter extends RecyclerView.Adapter<PlayersAdapter.ProductViewHolder> {
    private List<user> players = new ArrayList<>();

    public void setProductList(List<user> productList) {
        this.players = productList;
        notifyDataSetChanged();
    }
    public interface OnItemProductClickListener {
        void onProductClick(int position);
    }
    private OnItemProductClickListener onItemProductClickListener;
    public void setOnItemProductClickListener(OnItemProductClickListener onItemProductClickListener){
        this.onItemProductClickListener = onItemProductClickListener;
    }
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user, parent, false);

        return new ProductViewHolder(UserBinding.bind(view));
    }
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        user player = players.get(position);
        holder.binding.times.setText(player.getTime());
        holder.binding.names.setText(player.getName());
        holder.binding.resMatch.setText(player.getResult());
        holder.binding.getRoot().setOnClickListener(v->{
            if (onItemProductClickListener !=null) {
                onItemProductClickListener.onProductClick(position);
            }
        });
    }
    public user getItem(int position){
        return  players.get(position);
    }
    @Override
    public int getItemCount() {
        return players.size();
    }
    static class ProductViewHolder extends RecyclerView.ViewHolder {
        UserBinding binding;
        public ProductViewHolder(@NonNull UserBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

}
