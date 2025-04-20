package com.example.ships_version2.AppCompactAtcivity;


import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.ships_version2.Adapters.AuthManager;
import com.example.ships_version2.R;
import com.example.ships_version2.Service.Media_service;
import com.example.ships_version2.databinding.ActivityMainBinding;
import com.example.ships_version2.fragments.Fragment_settings;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    static Intent intent;
    public static Boolean sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());
        AuthManager authManager = new AuthManager(this);
        sound = true;
        binding.settings.setOnClickListener(view ->
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.settings_container);

            if(fragment==null) {
                fragment = new Fragment_settings();
                fragmentManager.beginTransaction()
                        .addToBackStack(String.valueOf(this))
                        .replace(R.id.settings_container, fragment)
                        .commit();
            }
        });

        binding.play.setOnClickListener(view-> {
            if (sound) {
                startService(new Intent(this, Media_service.class));
            }
            authManager.logoutUser();
            Intent intent = new Intent(MainActivity.this, AuthorizationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }



}