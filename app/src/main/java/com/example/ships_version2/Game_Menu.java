package com.example.ships_version2;

import static com.example.ships_version2.GameMatch.winn_ent;
import static com.example.ships_version2.GameMatch.winn_plr;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ships_version2.BD.ProductDatabase;
import com.example.ships_version2.databinding.ActivityGameMenuBinding;
import java.util.ArrayList;
import java.util.List;

public class Game_Menu extends AppCompatActivity {
    ActivityGameMenuBinding binding;
    ArrayList<user> list = new ArrayList<user>();
    StateAdapter adapter;
    RecyclerView recyclerView;
    private ProductDatabase repository;
    public static Intent getInstance(Context context) {
        return new Intent(context, Game_Menu.class);
    }
    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityGameMenuBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        if (winn_ent) {
            binding.winer.setText("YOU ARE LOOOOOSE");
        }
        if (winn_plr) {
            binding.winer.setText("NICE");
        }
        stopService(new Intent(Game_Menu.this, Media_service.class));
        repository = ProductDatabase.newInstance(getApplication());
        recyclerView = findViewById(R.id.TableList);
        list.add(new user("1", "2", "Win", 34324234));
        //repository.productDao().insertAll(new user("122222", "2222222", "Win", 34324234));
        ProductAdapter table = new ProductAdapter();
        //  table.setProductList(repository.productDao().getProductList());
        table.setProductList(list);
        recyclerView.setAdapter(table);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                    @Override
                    public boolean onMove(@NonNull RecyclerView recyclerView,
                                          @NonNull RecyclerView.ViewHolder viewHolder,
                                          @NonNull RecyclerView.ViewHolder target) {
                        return false;
                    }
                    @Override
                    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                        int position = viewHolder.getAdapterPosition();
                        user product = repository.productDao().getProductList().get(position);
                        repository.productDao().delete(product);
                        table.setProductList(repository.productDao().getProductList());
                    }
                });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}



//    @Override
//    public void onResume() {
//        super.onResume();
//        DatabaseAdapter adapter = new DatabaseAdapter(this);
//        adapter.open();
//
//        List<user> users = adapter.getUsers();
//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
//        userList.setAdapter(arrayAdapter);
//        adapter.close();
//    }