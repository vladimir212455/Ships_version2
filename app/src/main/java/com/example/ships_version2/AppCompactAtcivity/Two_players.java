package com.example.ships_version2.AppCompactAtcivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;


import com.example.ships_version2.R;
import com.example.ships_version2.databinding.ActivityTwoPlayersBinding;
import com.example.ships_version2.fragments.Fragment_field;
import com.example.ships_version2.fragments.Fragment_field2;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class Two_players extends AppCompatActivity {
    private static final String LOG_TAG = "ActivityPlayingField";
    ActivityTwoPlayersBinding binding;
    public static int[][] ship_pos_1;
    public static  int[][] ship_pos_2;
    public static  int[][] bomb_pos_1;
    public static  int[][] bomb_pos_2;
    public static int mutex;
    public static int hp_player1;
    public static int hp_player2;
    static public  int i;
    static public  int i2;
    public static Intent getInstance(Application context) {
        return new Intent(context, Two_players.class);
    }
    public Two_players()
    {
        ship_pos_1 = new int[4][4];
        bomb_pos_1 = new int[4][4];
        ship_pos_2 = new int[4][4];
        bomb_pos_2 = new int[4][4];
        hp_player1 = 4;
        hp_player2 = 4;
        i = 0;
        i2 = 0;
        Log.d(LOG_TAG, "ctor");
        mutex = 1;
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityTwoPlayersBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
            Log.d(LOG_TAG, "create i");
            replaceFragment();
            replaceFragment2();
            binding.hpFirst.setText("4");
            binding.hpSecond.setText("4");
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true)
                    {

                        binding.hpFirst.post(new Runnable() {
                            public void run() {
                                binding.hpFirst.setText(hp_player1 + " ");
                            }
                        });
                        binding.hpSecond.post(new Runnable() {
                            public void run() {
                                binding.hpSecond.setText(hp_player2 + " ");
                            }
                        });

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }
            });
            thread1.start();
    }
    private void replaceFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.root_container);

        if(fragment==null){
            fragment = new Fragment_field();
            fragmentManager.beginTransaction()
                    .addToBackStack(String.valueOf(this))
                    .replace(R.id.root_container,fragment)
                    .commit();
        }
    }
    private void replaceFragment2() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.root_container2);

        if(fragment==null){
            fragment = new Fragment_field2();
            fragmentManager.beginTransaction()
                    .addToBackStack(String.valueOf(this))
                    .replace(R.id.root_container2,fragment)
                    .commit();
        }
    }
    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}