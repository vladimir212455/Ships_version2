package com.example.ships_version2.fragments;


import static android.content.Context.MODE_PRIVATE;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.bomb_pos_1;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.bomb_pos_2;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.hp_player1;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.hp_player2;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.i;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.i2;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.mutex;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.ship_pos_1;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.ship_pos_2;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ships_version2.AppCompactAtcivity.Two_players;
import com.example.ships_version2.R;
import com.example.ships_version2.databinding.FragmentFieldBinding;
import com.google.gson.Gson;

import java.util.HashMap;

public class Fragment_field2 extends Fragment {
    private final static String LOG_TAG = "ContentFragment";
    private final static String BOMB= "bomb";
    private static final String SHIP = "ship";
    private static final String FILE_NAME = "data.json";
    HashMap<String, int[][]> input_hash;
    HashMap<String, int[][]> output_hash;
    private SharedPreferences sharedPreferences;
    private FragmentFieldBinding binding;
    Gson gson;
    private ImageButton[][] buttons;
    public Fragment_field2(){
        super(R.layout.fragment_field_);
        ship_pos_1 =  new int[4][4];
        bomb_pos_1 =  new int[4][4];
        i2 = 0;
        Log.d(LOG_TAG, "Constructor");
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(LOG_TAG, "onAttach");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(LOG_TAG, "onCreate");
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentFieldBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("PreferencesName", MODE_PRIVATE);

        buttons = new ImageButton[][]{
                {binding.button00, binding.button01, binding.button02, binding.button03},
                {binding.button10, binding.button11, binding.button12, binding.button13},
                {binding.button20, binding.button21, binding.button22, binding.button23},
                {binding.button30, binding.button31, binding.button32, binding.button33},
        };
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                int finalK = j;
                int finalJ = k;
                buttons[finalJ][finalK].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!buttons[finalJ][finalK].isActivated()) {
                            if (i2 <= 2 && mutex == 2) {
                                ship_pos_2[finalJ][finalK]++;
                                Log.d(LOG_TAG, finalJ + " " + finalK);
                                buttons[finalJ][finalK].setImageResource(R.drawable.boat);
                                i2++;
                            }
                            if (i2 <= 4 && i2 > 2 && mutex == 2) {
                                bomb_pos_2[finalJ][finalK]++;
                                Log.d(LOG_TAG, finalJ + " " + finalK);
                                buttons[finalJ][finalK].setImageResource(R.drawable.bomb);
                                i2++;
                            }

                            if (i2 == 5 && mutex == 2)
                            {
                                for (int j1 = 0; j1 < 4; j1++) {
                                    for (int k1 = 0; k1 < 4; k1++) {
                                        buttons[j1][k1].setImageResource(0);
                                    }
                                }
                                i2++;
                                mutex = 2;
                                Log.d(LOG_TAG, "mutex 1");
                            }
                        }
                    }
                });

            }

        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)
                {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (i2 == 6 && mutex == 2)
                {
                    Log.d(LOG_TAG, "start match");
                    match();
                    break;
                }}
            }
        });
        thread.start();
        Log.d(LOG_TAG, "onViewCreated");
    }
    void match()
    {
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
                int finalK = j;
                int finalJ = k;
                buttons[finalK][finalJ].setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (mutex == 2) {
                            buttons[finalK][finalJ].setImageResource(R.drawable.img);
                            if(ship_pos_1[finalK][finalJ] > 0){
                                hp_player2--;
                            Log.d(LOG_TAG, hp_player2 + "hp player 2");}

                            if(bomb_pos_1[finalK][finalJ] > 0){
                                hp_player1--;
                                Log.d(LOG_TAG, hp_player1 + "hp player 1");}
                            mutex = 1;
                        }
                    }
                });
            }
        }
    }
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Log.d(LOG_TAG, "onViewStateRestored");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
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
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(LOG_TAG, "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(LOG_TAG, "onDetach");
    }
}