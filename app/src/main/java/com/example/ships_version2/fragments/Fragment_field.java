package com.example.ships_version2.fragments;


import static com.example.ships_version2.AppCompactAtcivity.Two_players.bomb_pos_1;
import static com.example.ships_version2.AppCompactAtcivity.Two_players.ship_pos_1;

import android.content.Context;
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

public class Fragment_field extends Fragment {
    private final static String LOG_TAG = "ContentFragment";
    int i ;
    public Fragment_field(){
        super(R.layout.fragment_field_);
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
    private FragmentFieldBinding binding;

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
        ImageButton[][] buttons = {
                {binding.button00, binding.button01, binding.button02, binding.button03},
                {binding.button10, binding.button11, binding.button12, binding.button13},
                {binding.button20, binding.button21, binding.button22, binding.button23},
                {binding.button30, binding.button31, binding.button32, binding.button33},
        };
        i = 0;
        for (int j = 0; j < 4; j++) {
            for (int k = 0; k < 4; k++) {
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
                                startActivity(Two_players.getInstance(getActivity().getApplicationContext()));
                            }
                        }
                    }
                });
            }
        }
        Log.d(LOG_TAG, "onViewCreated");
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("i", i);
    }



}