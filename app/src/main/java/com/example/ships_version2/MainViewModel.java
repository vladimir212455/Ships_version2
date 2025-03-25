package com.example.ships_version2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.ships_version2.BD.ProductDatabase;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private ProductDatabase repository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repository = ProductDatabase.newInstance(application);

    }

    public LiveData<List<user>> getProducts() {
        return repository.productDao().getProductList();
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
