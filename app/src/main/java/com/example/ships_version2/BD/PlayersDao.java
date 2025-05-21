package com.example.ships_version2.BD;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.ships_version2.user;

import java.util.List;

@Dao
public interface PlayersDao {

    @Query("SELECT * FROM user_room ORDER BY name")
    LiveData<List<user>> getPlayerList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(user... products);

    @Delete
    void delete(user... product);

    @Update
    void update(user... product);

}