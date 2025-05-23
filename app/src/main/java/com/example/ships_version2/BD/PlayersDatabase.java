package com.example.ships_version2.BD;

import android.app.Application;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.ships_version2.user;


@Database(entities = {user.class}, version = 1)
public abstract class PlayersDatabase extends RoomDatabase {
    private static PlayersDatabase instance = null;
    private static final String DATABASE_NAME ="room_database.db";
    public static PlayersDatabase newInstance(Application application){
        if (instance==null){
            instance = Room.databaseBuilder(application,
                    PlayersDatabase.class,
                    DATABASE_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract PlayersDao productDao();
}
