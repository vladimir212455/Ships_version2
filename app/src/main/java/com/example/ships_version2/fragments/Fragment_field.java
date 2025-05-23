package com.example.ships_version2.fragments;


import static android.content.Context.MODE_PRIVATE;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.bomb_pos_1;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.bomb_pos_2;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.hp_player2;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.hp_player1;

import static com.example.ships_version2.AppCompactAtcivity.Two_players.i;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.i2;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.mutex;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.ship_pos_1;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.ship_pos_2;
import static java.lang.Thread.sleep;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import com.example.ships_version2.R;
import com.example.ships_version2.AppCompactAtcivity.Two_players;
import com.example.ships_version2.databinding.FragmentFieldBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;

public class Fragment_field extends Fragment {
    private final static String LOG_TAG = "ContentFragment";
    private FragmentFieldBinding binding;
    private ImageButton[][] buttons;
    public Fragment_field(){
        super(R.layout.fragment_field_);
        ship_pos_1 =  new int[4][4];
        bomb_pos_1 =  new int[4][4];
        i  = 0;
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
                            if (i <= 2 && mutex == 1) {
                                ship_pos_1[finalJ][finalK]++;
                                Log.d(LOG_TAG, finalJ + " " + finalK);
                                buttons[finalJ][finalK].setImageResource(R.drawable.boat);
                                i++;
                            }
                            if (i <= 4 && i > 2 && mutex == 1) {
                                bomb_pos_1[finalJ][finalK]++;
                                Log.d(LOG_TAG, finalJ + " " + finalK);
                                buttons[finalJ][finalK].setImageResource(R.drawable.bomb);
                                i++;
                            }
                            if (i == 5 && mutex == 1)
                            {
                                for (int j1 = 0; j1 < 4; j1++) {
                                    for (int k1 = 0; k1 < 4; k1++) {
                                        buttons[j1][k1].setImageResource(0);
                                    }
                                }
                                mutex = 2;
                                Log.d(LOG_TAG, "mutex 2");
                                i++;
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
                    if (i == 6 && mutex == 1)
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
                        if (mutex == 1) {
                            buttons[finalK][finalJ].setImageResource(R.drawable.img);
                            if(ship_pos_2[finalJ][finalJ] > 0){
                                hp_player2--;
                            Log.d(LOG_TAG, hp_player2 + "hp player 2");}

                            if(bomb_pos_2[finalK][finalJ] > 0){
                                hp_player1--;
                            Log.d(LOG_TAG, hp_player1 + "hp player 1");}
                            mutex = 2;
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