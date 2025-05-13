package com.example.ships_version2.AppCompactAtcivity;
import static com.example.ships_version2.AppCompactAtcivity.GameActivity.bomb_pos;
import static com.example.ships_version2.AppCompactAtcivity.GameActivity.ship_pos;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ships_version2.R;
import com.example.ships_version2.Adapters.StateAdapter;
import com.example.ships_version2.databinding.ActivityGameMatchBinding;
import com.example.ships_version2.structures.Entity;
import com.example.ships_version2.structures.state;

import java.util.ArrayList;

public class GameMatch extends AppCompatActivity {
    private ActivityGameMatchBinding binding;
    private static final String LOG_TAG = "ActivityPlayingField";
    private Entity entity;
    static int hp;
    static int hours = 0;
    static int minutes = 0;
    static int seconds = 0;
    int at = 0;
    SensorManager sensorManager;
    Sensor sensorLight;
    ArrayList<state> states = new ArrayList<state>();
    StateAdapter adapter;
    RecyclerView recyclerView;
    public static boolean winn_ent;
    public static boolean winn_plr;
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
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        hp = 4;
        entity = new Entity(4);
        entity.Create_field();
        onClickSensLight();
        try {
        states.add(new state());
        states.add(new state());
        states.add(new state());
        states.add(new state());
        }catch (Exception ex)
        {
            Log.d(LOG_TAG, ex.getMessage().toString());
        }
        recyclerView = findViewById(R.id.list);
        adapter = new StateAdapter(this, states);
        recyclerView.setAdapter(adapter);
        ImageButton[][] buttons1 = {
                {binding.button00, binding.button01, binding.button02, binding.button03},
                {binding.button10, binding.button11, binding.button12, binding.button13},
                {binding.button20, binding.button21, binding.button22, binding.button23},
                {binding.button30, binding.button31, binding.button32, binding.button33},
        };
        Log.d(LOG_TAG, "TIme");
        Thread thread = getThread();
        thread.start();
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
                                                binding.entityHP.setText(entity.hp + "");
                                                if(entity.hp == 0){
                                                    winn_plr= true;
                                                    startActivity(Game_Menu.getInstance(binding.getRoot().getContext()));
                                                }
                                            }
                                            Log.d(LOG_TAG, "Click");
                                            buttons1[finalJ][finalK].setImageResource(R.drawable.img);
                                            at++;
                                        } else if (at < 3 && hp > 0) {
                                            if (entity.getBomb_pos()[finalJ][finalK] == 1) {
                                                Log.d(LOG_TAG, "DMG");
                                                hp--;
                                                binding.HP.setText(hp + "");
                                                if(hp == 0){
                                                    winn_ent= true;
                                                    startActivity(Game_Menu.getInstance(binding.getRoot().getContext()));
                                                }
                                                states.remove(0);
                                                adapter.notifyDataSetChanged();
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
                    hp--;
                    binding.HP.setText(hp + "");
                    if(hp == 0){
                        winn_ent= true;
                        startActivity(Game_Menu.getInstance(binding.getRoot().getContext()));
                    }
                    states.remove(0);
                    adapter.notifyDataSetChanged();
                }
                if (attacks[i][j] > 0 && bomb_pos[i][j] > 0) {
                    Log.d(LOG_TAG, "damage entity");
                    entity.hp--;
                    binding.entityHP.setText(entity.hp + "");
                    if(entity.hp == 0){
                        winn_plr= true;
                        startActivity(Game_Menu.getInstance(binding.getRoot().getContext()));
                    }
                }
            }
        }
        Log.d(LOG_TAG, "end curect atack");
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
    public void onClickSensLight() {
        sensorManager.registerListener(listener, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(listener, sensorLight);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(listener, sensorLight, SensorManager.SENSOR_DELAY_NORMAL);
    }
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            binding.brightness.setText(String.valueOf(event.values[0]));
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };
}
