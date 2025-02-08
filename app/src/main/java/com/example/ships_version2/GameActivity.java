package com.example.ships_version2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ships_version2.databinding.ActivityGameBinding;

public class GameActivity extends AppCompatActivity {
    private int count_ship = 0;
    private int count_bomb = 0;
    private ActivityGameBinding binding;
    static  ImageButton[][] buttons;
    static int ship_pos[][];
    static int bomb_pos[][];

    private static final String LOG_TAG = "ActivityPlayingField";


    public static Intent getInstance(Context context) {
        return new Intent(context, GameActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityGameBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        initGameStap(2, 2);
    }
    private void initGameStap(int c_ship, int c_bomb) {
        ship_pos = new int[4][4];
        bomb_pos = new int[4][4];
        ImageButton[][] buttons = {
                {binding.button00, binding.button01, binding.button02, binding.button03},
                {binding.button10, binding.button11, binding.button12, binding.button13},
                {binding.button20, binding.button21, binding.button22, binding.button23},
                {binding.button30, binding.button31, binding.button32, binding.button33},
        };
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                int finalJ = j;
                int finalK = k;

                buttons[j][k].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (count_ship <= c_ship) {
                            buttons[finalJ][finalK].setImageResource(R.drawable.boat);
                            Log.d(LOG_TAG, "ship =" + finalJ + " " + finalK);
                            count_ship++;
                            ship_pos[finalJ][finalK]++;
                        }
                        if (count_ship > c_ship && count_bomb < c_bomb) {
                            buttons[finalJ][finalK].setImageResource(R.drawable.bomb);
                            Log.d(LOG_TAG, "bomb =" + finalJ + " " + finalK);
                            count_bomb++;
                            bomb_pos[finalJ][finalK]++;
                        }
                        if (count_ship >= c_ship && count_bomb >= c_bomb)
                        {  startActivity(GameMatch.getInstance(getApplicationContext()));}
                    }

                });
                Log.d(LOG_TAG, "Click");

            }
        }


        Log.d(LOG_TAG, "end");
    }
}