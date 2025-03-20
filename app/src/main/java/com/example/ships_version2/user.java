package com.example.ships_version2;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "user_room")
public class user {
    public user(String time, String name, String result, int id) {
        this.time = time;
        this.name = name;
        this.result = result;
        this.id = id;
    }
    @ColumnInfo(name = "time")
    private String time;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "result")
    private String result;

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Override
    public String toString() {
        return "user{" +
                "time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", result='" + result + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        user user = (user) o;
        return id == user.id && Objects.equals(time, user.time) && Objects.equals(name, user.name) && Objects.equals(result, user.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, name, result, id);
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

    public int getId() {
        return id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
