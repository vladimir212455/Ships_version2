package com.example.ships_version2.AppCompactAtcivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.ships_version2.VIewModels.AddNewProductViewModel;
import com.example.ships_version2.databinding.ActivityGameMenuBinding;


public class AddNewProductActivity extends AppCompatActivity {
    private final static String EXTRA_NAME = "name";
    private final static String EXTRA_DESCRIPTION = "description";
    private final static String EXTRA_PRICE = "price";
    private String name;
    private String description;
    private String price;
    //private ProductRepository repository;
    private AddNewProductViewModel viewModel;
    private Boolean close = false;
    private Handler handler = new Handler(Looper.getMainLooper());

    public static Intent newIntent(Context context, String name, String description, String price) {
        Intent intent = new Intent(context, AddNewProductActivity.class);
        intent.putExtra(EXTRA_NAME, name);
        intent.putExtra(EXTRA_DESCRIPTION, description);
        intent.putExtra(EXTRA_PRICE, price);
        return intent;

    }


    private ActivityGameMenuBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel.getClose().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                close = aBoolean;
                if (close) {
                    finish();
                }
            }
        });
        //repository = ProductRepository.newInstance(getApplication());


        name = getIntent().getStringExtra(EXTRA_NAME);
        description = getIntent().getStringExtra(EXTRA_DESCRIPTION);
        price = getIntent().getStringExtra(EXTRA_PRICE);


    }

}
