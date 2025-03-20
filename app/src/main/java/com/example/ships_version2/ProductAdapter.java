package com.example.ships_version2;

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

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<user> productList = new ArrayList<>();

    public void setProductList(List<user> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    interface OnItemProductClickListener {
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
        user product = productList.get(position);

        holder.binding.times.setText(product.getTime());
        holder.binding.names.setText(product.getName());
        holder.binding.resMatch.setText(product.getResult());
        holder.binding.getRoot().setOnClickListener(v->{
            if (onItemProductClickListener !=null) {
                onItemProductClickListener.onProductClick(position);
            }
        });


       /* holder.binding.getRoot().setOnClickListener(v -> {
            new AlertDialog.Builder(v.getContext())
                    .setTitle("Удалить элемент")
                    .setMessage("Удалить элемент?")
                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            productList.remove(productList.get(position));
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_delete)
                    .show();

        });*/

    }
    public user getItem(int position){
        return  productList.get(position);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        UserBinding binding;

        public ProductViewHolder(@NonNull UserBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

}
