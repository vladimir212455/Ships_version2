package com.example.ships_version2.AppCompactAtcivity;

import static com.example.ships_version2.AppCompactAtcivity.GameMatch.hours;
import static com.example.ships_version2.AppCompactAtcivity.GameMatch.minutes;
import static com.example.ships_version2.AppCompactAtcivity.GameMatch.seconds;
import static com.example.ships_version2.AppCompactAtcivity.GameMatch.winn_ent;
import static com.example.ships_version2.AppCompactAtcivity.GameMatch.winn_plr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ships_version2.BD.ProductDatabase;
import com.example.ships_version2.VIewModels.MainViewModel;
import com.example.ships_version2.Adapters.ProductAdapter;
import com.example.ships_version2.Adapters.StateAdapter;
import com.example.ships_version2.VIewModels.AddNewProductViewModel;
import com.example.ships_version2.databinding.ActivityGameMenuBinding;
import com.example.ships_version2.user;

import java.util.List;
import java.util.Random;

public class Game_Menu extends AppCompatActivity {
    ActivityGameMenuBinding binding;
    StateAdapter adapter;
    RecyclerView recyclerView;
    private ProductDatabase repository;
    private AddNewProductViewModel main_viewModel;
    private MainViewModel viewModel;
    private Boolean close = false;
    private ProductAdapter arrayAdapter;
    private Random random;

    public static Intent getInstance(Context context) {
        return new Intent(context, Game_Menu.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityGameMenuBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        Random random1 = new Random();
        int p = random1.nextInt() ;
        Log.d("Game_Menu", p + " ");
        main_viewModel = new ViewModelProvider(this).get(AddNewProductViewModel.class);
        if (winn_ent) {
            binding.winer.setText("YOU ARE LOOOOOSE");
            user product = new user(hours +  " " + minutes + " " + seconds, "you", "LOSE", p);
            main_viewModel.saveData(product);
        }
        if (winn_plr) {
            binding.winer.setText("NICE");
            user product = new user(hours +  " " + minutes + " " + seconds, "you", "WIN",  p);
            main_viewModel.saveData(product);
        }
        try {
            viewModel = new ViewModelProvider(this).get(MainViewModel.class);
            arrayAdapter = new ProductAdapter();
            viewModel.getProducts().observe(this, new Observer<List<user>>() {
                @Override
                public void onChanged(List<user> products) {
                    arrayAdapter.setProductList(products);
                }
            });
            binding.TableList.setAdapter(arrayAdapter);
            touch();
        }catch (Exception e)
        {
            Log.d("Array", e.getMessage().toString());
        }

    }
    void touch()
    {
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
                        user product = arrayAdapter.getItem(position);
                        viewModel.remove(product);

                    }
                });
        itemTouchHelper.attachToRecyclerView(binding.TableList);
        arrayAdapter.setOnItemProductClickListener(new ProductAdapter.OnItemProductClickListener() {
            @Override
            public void onProductClick(int position) {}
        });

    }
}