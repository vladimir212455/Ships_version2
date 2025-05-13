package com.example.ships_version2.VIewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.ships_version2.BD.PlayersDatabase;
import com.example.ships_version2.user;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private PlayersDatabase repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = PlayersDatabase.newInstance(application);

    }

    public LiveData<List<user>> getProducts() {
        return repository.productDao().getPlayerList();
    }

    public void remove(user product) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                repository.productDao().delete(product);
            }
        });
        thread.start();
    }

}
