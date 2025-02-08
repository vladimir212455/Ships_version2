package com.example.ships_version2;

import static com.example.ships_version2.GameActivity.bomb_pos;
import static com.example.ships_version2.GameActivity.buttons;
import static com.example.ships_version2.GameActivity.ship_pos;

import android.annotation.SuppressLint;
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

import com.example.ships_version2.databinding.ActivityGameMatchBinding;
import com.example.ships_version2.databinding.ActivityMainBinding;

import java.util.Calendar;

public class GameMatch extends AppCompatActivity {
    ActivityGameMatchBinding binding;
    private static final String LOG_TAG = "ActivityPlayingField";
    private PrefsManager prefsManager;
    Entity entity;
    Boolean state_game = true;
    ImageButton[][] buttons1;
    int hp;
//    @Override
//    public void onPause()
//    {
//        super.onPause();
//        try {
//            prefsManager.Set_state(entity, ship_pos, bomb_pos);
//        }
//      catch (Exception e)
//      {
//            throw new RuntimeException(e);
//      }
//        Log.d(LOG_TAG, "create Atcivity");
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        try {
//            entity.setBomb_pos(prefsManager.getEntity_bomb());
//            entity.setShip_pos(prefsManager.getEntity_ship());
//            ship_pos = prefsManager.getPlayer_ship();
//            bomb_pos = prefsManager.getPlayer_bomb();
//
//        }
//        catch (Exception e)
//        {
//            throw new RuntimeException(e);
//        }
//    }

    public static Intent getInstance(Context context) {
        return new Intent(context, GameMatch.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityGameMatchBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        hp = 2;
        entity = new Entity(4);
        entity.Create_field();
        ImageButton[][] buttons1 = {
                {binding.button00, binding.button01, binding.button02, binding.button03},
                {binding.button10, binding.button11, binding.button12, binding.button13},
                {binding.button20, binding.button21, binding.button22, binding.button23},
                {binding.button30, binding.button31, binding.button32, binding.button33},
        };
        Log.d(LOG_TAG, "TIme");
        binding.Time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Calendar c = Calendar.getInstance();
                        int hours = c.get(Calendar.HOUR_OF_DAY);
                        int minutes = c.get(Calendar.MINUTE);
                        int seconds = c.get(Calendar.SECOND);
                        String time = hours + ":" + minutes + ":" + seconds;
                        binding.Time.post(new Runnable() {
                            public void run() {
                                binding.Time.setText(time);
                            }
                        });
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });



//            while (state_game) {
//            curect_entity(entity.Atack_rand());
//            curect_user();
//        }

    }


    private void curect_user() {
    }

    private void curect_entity(int[][] atacks) {
        for (int i = 0; i < atacks.length; i++) {
            if(ship_pos[atacks[i][0]][atacks[i][1]] == 1)
            {
                buttons1[atacks[i][0]][atacks[i][1]].setImageResource(R.drawable.img);
                hp--;
            }
            if(bomb_pos[atacks[i][0]][atacks[i][1]] == 1)
            {
                buttons1[atacks[i][0]][atacks[i][1]].setImageResource(R.drawable.img);
                entity.hp--;
            }

        }

    }
}