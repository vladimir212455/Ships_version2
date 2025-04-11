package com.example.ships_version2.AppCompactAtcivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ships_version2.databinding.ActivityCountPlayerBinding;

public class count_player extends AppCompatActivity {
    ActivityCountPlayerBinding binding;
    public static Intent getInstance(Context context) {
        return new Intent(context, count_player.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityCountPlayerBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        binding.two.setOnClickListener(v ->
        {
            startActivity(Two_players.getInstance(getApplicationContext()));
        });
        binding.one.setOnClickListener(v ->
        {
            startActivity(GameActivity.getInstance(getApplicationContext()));
        });
    }
}