package com.example.ships_version2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.ships_version2.databinding.ActivityCountPlayerBinding;
import com.example.ships_version2.databinding.ActivityGameBinding;
import com.example.ships_version2.databinding.ActivityTwoPlayersBinding;

import java.util.concurrent.ExecutionException;

public class Two_players extends AppCompatActivity {
    private static final String LOG_TAG = "ActivityPlayingField";
    ActivityTwoPlayersBinding binding;
    static int[][] ship_pos_1;
    static int[][] ship_pos_2;
    static int[][] bomb_pos_1;
    static int[][] bomb_pos_2;
    Boolean player;
    int hp_player1;
    int hp_player2;
    int i;

    public static Intent getInstance(Context context) {
        return new Intent(context, Two_players.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityTwoPlayersBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        ship_pos_1 = new int[7][4];
        bomb_pos_1 = new int[7][4];
        ship_pos_2 = new int[7][4];
        bomb_pos_2 = new int[7][4];
        try {
            ImageButton[][] buttons = {
                    {binding.button00, binding.button01, binding.button02, binding.button03},
                    {binding.button10, binding.button11, binding.button12, binding.button13},
                    {binding.button20, binding.button21, binding.button22, binding.button23},
                    {binding.button30, binding.button31, binding.button32, binding.button33},
                    {binding.button001, binding.button011, binding.button031, binding.button031},
                    {binding.button101, binding.button111, binding.button131, binding.button131},
                    {binding.button201, binding.button211, binding.button231, binding.button231},
            };


        hp_player1 = 4;
        hp_player2 = 4;
        i = 0;
        Log.d(LOG_TAG, "create i");

        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 7; k++) {
                int finalK = j;
                int finalJ = k;
                buttons[finalJ][finalK].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            if (!buttons[finalJ][finalK].isActivated()) {
                                if (i <= 2) {
                                    ship_pos_1[finalJ][finalK]++;
                                    Log.d(LOG_TAG, finalJ + " " + finalK);
                                    buttons[finalJ][finalK].setImageResource(R.drawable.boat);
                                    i++;
                                }
                                if (i <= 4 && i > 2) {
                                    bomb_pos_1[finalJ][finalK]++;
                                    Log.d(LOG_TAG, finalJ + " " + finalK);
                                    buttons[finalJ][finalK].setImageResource(R.drawable.bomb);
                                    i++;
                                }
                                if (i== 5) {
                                   replaceFragment();
                                }
                            }
                }
                });
            }
        }
        }catch (RuntimeException e) {
            Log.d(LOG_TAG, e.getMessage().toString());
        }
    }
    private void replaceFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.root_container);

        if(fragment==null){
            fragment = new Fragment_field();
            fragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.root_container,fragment)
                    .commit();
        }
    }
}