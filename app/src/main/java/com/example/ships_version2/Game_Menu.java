package com.example.ships_version2;

import static com.example.ships_version2.GameMatch.winn_ent;
import static com.example.ships_version2.GameMatch.winn_plr;
import static com.example.ships_version2.MainActivity.intent;

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
        return new Intent(context, Game_Menu.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityGameMenuBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        if(winn_ent)
        {
            binding.winer.setText("YOU ARE LOOOOOSE");
        }
        if(winn_plr)
        {
            binding.winer.setText("NICE");
        }
        stopService(new Intent(Game_Menu.this, Media_service.class));
    }
}