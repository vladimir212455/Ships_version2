package com.example.ships_version2;

import static androidx.core.content.PackageManagerCompat.LOG_TAG;
import static com.example.ships_version2.GameMatch.hours;
import static com.example.ships_version2.GameMatch.minutes;
import static com.example.ships_version2.GameMatch.seconds;
import static com.example.ships_version2.GameMatch.winn_ent;
import static com.example.ships_version2.GameMatch.winn_plr;
import static com.example.ships_version2.MainActivity.intent;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ships_version2.databinding.ActivityGameBinding;
import com.example.ships_version2.databinding.ActivityGameMenuBinding;

import java.util.ArrayList;
import java.util.List;

public class Game_Menu extends AppCompatActivity {
    ActivityGameMenuBinding binding;
    DataBaseHelper databaseHelper;
    SQLiteDatabase db;
    List<user> list = new ArrayList<>();
    public static Intent getInstance(Context context) {
        return new Intent(context, Game_Menu.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityGameMenuBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        if(winn_ent)
        {
            binding.winer.setText("YOU ARE LOOOOOSE");
        }
        if(winn_plr)
        {
            binding.winer.setText("NICE");
        }
        stopService(new Intent(Game_Menu.this, Media_service.class));
try {
    list.add(new user("1", 1, "Win"));
    TableAdapter table = new TableAdapter();
    table.setProductList(list);
    binding.recyclerView.setAdapter(table);
}catch (Exception e){
        Log.d("BDes", e.getMessage().toString());
    }
}
//    @Override
//    public void onResume() {
//        super.onResume();
//        DatabaseAdapter adapter = new DatabaseAdapter(this);
//        adapter.open();
//
//        List<user> users = adapter.getUsers();
//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
//        userList.setAdapter(arrayAdapter);
//        adapter.close();
//    }
}