package com.example.ships_version2.VIewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ships_version2.BD.ProductDatabase;
import com.example.ships_version2.user;


public class AddNewProductViewModel extends AndroidViewModel {
    private ProductDatabase repository;

    private MutableLiveData<Boolean> isClose = new MutableLiveData<>(false);

    public AddNewProductViewModel(@NonNull Application application) {
        super(application);
        repository = ProductDatabase.newInstance(application);
    }

    public void saveData(user product) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                repository.productDao().insertAll(product);
                isClose.postValue(true);
            }
        });
        thread.start();
    }
    public LiveData<Boolean> getClose() {
        return isClose;
    }

}
