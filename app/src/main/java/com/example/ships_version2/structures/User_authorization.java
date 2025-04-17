package com.example.ships_version2.structures;

public class User_authorization {
    private String username;
    private String passwordHash; // Храним хеш пароля, НЕ сам пароль

    // Пустой конструктор нужен для Gson
    public User_authorization() {
    }

    public User_authorization(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // Важно переопределить equals и hashCode для корректного поиска в коллекциях
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User_authorization user = (User_authorization) o;
        return username != null ? username.equals(user.username) : user.username == null;
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }
}