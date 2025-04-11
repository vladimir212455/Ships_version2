package com.example.ships_version2.structures;

import android.util.Log;

import java.util.Random;

public class Entity {
    private int ship_pos[][];
    private int bomb_pos[][];
    private int size;
    public int hp = 2;

    public void setBomb_pos(int[][] bomb_pos) {
        this.bomb_pos = bomb_pos;
    }

    public void setShip_pos(int[][] ship_pos) {
        this.ship_pos = ship_pos;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Entity(int size) {
        this.bomb_pos = new int[size][size];
        this.ship_pos = new int[size][size];
        this.size = size;
        this.hp = 4;
    }

    public int[][] getBomb_pos() {
        return bomb_pos;
    }

    public int[][] getShip_pos() {
        return ship_pos;
    }

    public void Create_field() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int k = random.nextInt(size);
            int j = random.nextInt(size);
            if (ship_pos[k][j] == 0) {
                ship_pos[k][j]++;
                Log.d("ActivityPlayingField", "Entity ship" + k + j);
            }
        }
        for (int i = 0; i < size; i++) {
            int k = random.nextInt(size);
            int j = random.nextInt(size);
            if (bomb_pos[k][j] == 0) {
                bomb_pos[k][j]++;
                Log.d("ActivityPlayingField", "Entity bomb" + k + j);
            }
        }
    }

    public int[][] Atack_rand() {
        Random random = new Random();
        int[][] res = new int[size][size];
        for (int i = 0; i < size; i++) {
            int k = random.nextInt(size);
            int j = random.nextInt(size);
            if (ship_pos[k][j] == 0) {
                res[k][j] ++;
                res[k][j] ++;
                Log.d("ActivityPlayingField", "Entity atack" + k + j);
            }
        }
        return res;
    }
}
