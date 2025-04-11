package com.example.ships_version2.AppCompactAtcivity;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ships_version2.Service.Media_service;
import com.example.ships_version2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    static Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.play.setOnClickListener(view-> {
            intent = new Intent(this, Media_service.class);
            startService(intent);
            startActivity(count_player.getInstance(getApplicationContext()));
        });

    }
}