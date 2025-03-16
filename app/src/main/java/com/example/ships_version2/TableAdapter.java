package com.example.ships_version2;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ships_version2.user;
import com.example.ships_version2.databinding.UserBinding;
import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.ProductViewHolder> {
    private List<user> UserList = new ArrayList<>();

    public void setProductList(List<user> uiserlist) {
        this.UserList = uiserlist;
        notifyDataSetChanged();
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
        user product = UserList.get(position);
        holder.binding.name.setText(product.name);
        holder.binding.time.setText(product.time);
        holder.binding.resMatch.setText(product.result);

//        holder.binding.getRoot().setOnClickListener(v->{
//            new AlertDialog.Builder(v.getContext())
//                    .setTitle("Удалить элемент")
//                    .setMessage("Удалить элемент?")
//                    .setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialogInterface, int i) {
//                            productList.remove(productList.get(position));
//                            notifyDataSetChanged();
//                        }
//                    })
//                    .setNegativeButton(android.R.string.no, null)
//                    .setIcon(android.R.drawable.ic_delete)
//                    .show();
//
//        });

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        UserBinding binding;
        public ProductViewHolder(UserBinding b) {
            super(b.getRoot());
            binding = b;
        }
    }

}
