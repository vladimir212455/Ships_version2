package com.example.ships_version2;

import android.content.SharedPreferences;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PrefsManager {
    public final static String NAME = "MY_GAME";
    private final static String KEY_SCORE = "field entity";
    private final SharedPreferences sharedPreferences;
    int[][] entity_bomb;
    int[][] entity_ship;

    public int[][] getEntity_bomb() {
        return entity_bomb;
    }

    public int[][] getEntity_ship() {
        return entity_ship;
    }

    public int[][] getPlayer_ship() {
        return player_ship;
    }

    public int[][] getPlayer_bomb() {
        return player_bomb;
    }

    int[][] player_bomb;
    int[][] player_ship;

    public PrefsManager(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void Set_state(Entity entity, int[][] ship_pos, int[][] bomb_pos) {
        this.entity_bomb = entity.getBomb_pos();
        this.entity_ship = entity.getShip_pos();
        this.player_bomb = bomb_pos;
        this.player_ship = ship_pos;
    }
}
