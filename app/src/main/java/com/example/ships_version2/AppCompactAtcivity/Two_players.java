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

    Boolean player;
    int hp_player1;
    int hp_player2;
    static public  int i;
    static public  int i2;
    private SharedPreferences sharedPreferences;

    private final static String BOMB= "bomb";
    private static final String SHIP = "ship";
    private static final String FILE_NAME = "data.json";
    HashMap<String, int[][]> input_hash;
    HashMap<String, int[][]> output_hash;
    Gson gson;

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
//        getHash();
        Log.d(LOG_TAG, "onResume");
    }


    
    void Attack(int[][] bomb, int[][] ship_ent)
    {


    }


    @Override
    public void onPause() {
        super.onPause();
//        setHash();
        Log.d(LOG_TAG, "onPause");
    }
//    public void getHash() {
//            String storedHashMapString = sharedPreferences.getString("hashString", "oopsDintWork");
//            java.lang.reflect.Type type = new TypeToken<HashMap<String,  int[][]>>(){}.getType();
//            output_hash = gson.fromJson(storedHashMapString, type);
//            ship_pos_1 = output_hash.get(SHIP);
//            bomb_pos_1 = output_hash.get(BOMB);
//
//    }
//
//    private void setHash()
//    {
//        input_hash = new HashMap<>();
//        input_hash.put(SHIP, ship_pos_1);
//        input_hash.put(BOMB, bomb_pos_1);
//        gson = new Gson();
//        String hashMapString = gson.toJson(input_hash);
//        sharedPreferences.edit().putString("hashString", hashMapString).apply();
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityTwoPlayersBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        //   SharedPreferences sharedPreferences = binding.getRoot().getContext().getSharedPreferences("PreferencesName", MODE_PRIVATE);


            Log.d(LOG_TAG, "create i");
            replaceFragment();
            replaceFragment2();
            binding.hpFirst.setText("4");
            binding.hpSecond.setText("4");

//        for (int j = 0; j < 4; j++) {
//            for (int k = 0; k < 4; k++) {
//                int finalK = j;
//                int finalJ = k;
//                buttons[finalJ][finalK].setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                            if (!buttons[finalJ][finalK].isActivated()) {
//                                if (i <= 2) {
//                                    ship_pos_1[finalJ][finalK]++;
//                                    Log.d(LOG_TAG, finalJ + " " + finalK);
//                                    buttons[finalJ][finalK].setImageResource(R.drawable.boat);
//                                    i++;
//                                }
//                                if (i <= 4 && i > 2) {
//                                    bomb_pos_1[finalJ][finalK]++;
//                                    Log.d(LOG_TAG, finalJ + " " + finalK);
//                                    buttons[finalJ][finalK].setImageResource(R.drawable.bomb);
//                                    i++;
//                                }
//                                if (i== 5) {
//                                 //  replaceFragment();
//                                }
//                            }
//                }
//                });
//            }
//        }

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
        // setHash();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

}