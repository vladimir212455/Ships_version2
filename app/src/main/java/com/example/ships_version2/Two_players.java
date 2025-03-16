package com.example.ships_version2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ships_version2.databinding.ActivityCountPlayerBinding;
import com.example.ships_version2.databinding.ActivityTwoPlayersBinding;

public class Two_players extends AppCompatActivity {
    ActivityTwoPlayersBinding binding;
    public static Intent getInstance(Context context) {
        return new Intent(context, Two_players.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityTwoPlayersBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
    }
}