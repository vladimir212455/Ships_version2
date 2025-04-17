package com.example.ships_version2.AppCompactAtcivity;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ships_version2.Adapters.AuthManager;
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
        AuthManager authManager = new AuthManager(this);
        binding.play.setOnClickListener(view-> {
            authManager.logoutUser();
            Intent intent = new Intent(MainActivity.this, AuthorizationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

    }
}