package com.example.ships_version2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ships_version2.databinding.ActivityGameBinding;
import com.example.ships_version2.databinding.ActivityGameMenuBinding;

public class Game_Menu extends AppCompatActivity {
    ActivityGameMenuBinding binding;
    public static Intent getInstance(Context context) {
        return new Intent(context, GameActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityGameMenuBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}