package com.example.ships_version2;

import static com.example.ships_version2.GameActivity.bomb_pos;
import static com.example.ships_version2.GameActivity.ship_pos;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ships_version2.databinding.ActivityGameMatchBinding;

import java.util.concurrent.atomic.AtomicInteger;

public class GameMatch extends AppCompatActivity {
    private ActivityGameMatchBinding binding;
    private static final String LOG_TAG = "ActivityPlayingField";
    private Entity entity;
    private Boolean state_game = true;
    static ImageButton[][] buttons1;
    private int hp;
    private int hours = 0;
    private int minutes = 0;
    private int seconds = 0;
    int at = 0;
    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("time1", hours);
        outState.putInt("time2", minutes);
        outState.putInt("time3", seconds);
        outState.putInt("hp", hp);
        outState.putInt("hp_entity", entity.hp);

    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        hours =  savedInstanceState.getInt("time1");
        minutes =  savedInstanceState.getInt("time2");
        seconds =  savedInstanceState.getInt("time3");
        hp =  savedInstanceState.getInt("hp");
        entity.hp =  savedInstanceState.getInt("hp_entity");
    }

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
        Thread thread = getThread();
        thread.start();
        Thread thread1 = getThread1();
        thread1.start();
        Thread thread2 = getThread2();
        thread2.start();

        entity_atack1();

        Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    Log.d(LOG_TAG, "start user_curect atack");
                    for (int j = 0; j < 4; j++) {
                        for (int k = 0; k < 4; k++) {
                            int finalJ = j;
                            int finalK = k;

                            try {
                                if (!buttons1[finalJ][finalK].isActivated()) {
                                    buttons1[finalJ][finalK].setOnClickListener(v -> {
                                        if (at < 3 && entity.hp > 0) {
                                            if (entity.getShip_pos()[finalJ][finalK] == 1) {
                                                Log.d(LOG_TAG, "DMG_ent");
                                                entity.hp--;
                                            }
                                            Log.d(LOG_TAG, "Click");
                                            buttons1[finalJ][finalK].setImageResource(R.drawable.img);
                                            at++;
                                        } else if (at < 3 && hp > 0) {
                                            if (entity.getBomb_pos()[finalJ][finalK] == 1) {
                                                Log.d(LOG_TAG, "DMG");
                                                hp--;
                                            }
                                            Log.d(LOG_TAG, "Click");
                                            buttons1[finalJ][finalK].setImageResource(R.drawable.img);
                                            at++;
                                        }
                                    });
                                }
                            } catch (Exception ex) {
                                Log.d(LOG_TAG, ex.getMessage().toString());
                            }
                        }
                    }
                }
            };
            Thread thread3 = new Thread(runnable);
            thread3.start() ;
            Thread thread4 = startcheckeAT();
            thread4.start();
    }

    private Thread startcheckeAT() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // Log.d(LOG_TAG, "st");
                    if(at >= 3) {
                        entity_atack1();
                        at = 0;
                    }
                }

            }
        };
        return new Thread(runnable);
    }

    private void entity_atack1() {
        int[][] attacks = entity.Atack_rand();
        Log.d(LOG_TAG, "start curect atack");

        for (int i = 0; i < attacks.length; i++) {
            for (int j = 0; j < attacks.length; j++) {
                if (attacks[i][j] > 0 && ship_pos[i][j] > 0) {
                    Log.d(LOG_TAG, "damage player");
                    //    buttons1[i][j].setImageResource(R.drawable.img);
                    hp--;
                }
                if (attacks[i][j] > 0 && bomb_pos[i][j] > 0) {
                //    buttons1[i][j].setImageResource(R.drawable.img);
                    Log.d(LOG_TAG, "damage entity");
                    entity.hp--;
                }

            }
        }
        Log.d(LOG_TAG, "end curect atack");

    }




private Thread getThread2() {
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                binding.entityHP.setText(entity.hp + "");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    };
    return new Thread(runnable);
}
    private Thread getThread1() {
        Runnable runnable = new Runnable() {
            @Override
           public void run() {
                while (true) {
                    binding.HP.setText(hp + "");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        return new Thread(runnable);
    }

    private @NonNull Thread getThread() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (seconds > 60)
                    {
                        seconds = 0;
                        minutes++;
                    }
                    if (minutes > 60)
                    {
                        minutes = 0;
                        hours++;
                    }
                    String time = hours + ":" + minutes + ":" + seconds;
                    binding.Time.post(new Runnable() {
                        public void run() {
                            binding.Time.setText(time);
                        }
                    });
                    seconds++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        return new Thread(runnable);
    }
}