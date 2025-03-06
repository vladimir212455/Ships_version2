package com.example.ships_version2;

import static com.example.ships_version2.GameMatch.winn_ent;
import static com.example.ships_version2.GameMatch.winn_plr;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ships_version2.databinding.ActivityCountPlayerBinding;
import com.example.ships_version2.databinding.ActivityGameMenuBinding;

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
        binding.one.setOnClickListener(v ->
        {
            startActivity(GameActivity.getInstance(getApplicationContext()));
        });
        binding.one.setOnClickListener(v ->
        {
            startActivity(GameActivity.getInstance(getApplicationContext()));
        });
    }
}