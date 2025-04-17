package com.example.ships_version2.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.example.ships_version2.structures.User_authorization;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class AuthManager {
    private static final String TAG = "AuthManager";
    private static final String FILE_NAME = "users.json";
    private Context context;
    private Gson gson;
    public AuthManager(Context context) {
        this.context = context;
        this.gson = new Gson();
    }
    private List<User_authorization> loadUsers() {
        List<User_authorization> users = new ArrayList<>();
        try (FileInputStream fis = context.openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(isr)) {

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
            Type userListType = new TypeToken<ArrayList<User_authorization>>(){}.getType();
            users = gson.fromJson(stringBuilder.toString(), userListType);

            if (users == null) {
                users = new ArrayList<>();
            }
        } catch (java.io.FileNotFoundException e) {
            Log.i(TAG, "User file not found, creating new list.");
            users = new ArrayList<>();
        } catch (Exception e) {
            Log.e(TAG, "Error loading users from file", e);
            users = new ArrayList<>();
        }
        return users;
    }
    private void saveUsers(List<User_authorization> users) {
        String json = gson.toJson(users);
        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fos.write(json.getBytes(StandardCharsets.UTF_8));
            Log.i(TAG, "Users saved successfully to " + FILE_NAME);
        } catch (Exception e) {
            Log.e(TAG, "Error saving users to file", e);
        }
    }
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "SHA-256 Algorithm not found", e);
            return null;
        }
    }
    public boolean registerUser(String username, String password) {
        if (username.isEmpty() || password.isEmpty()){
            Log.w(TAG, "Username or password empty during registration.");
            return false;
        }
        List<User_authorization> users = loadUsers();
        for (User_authorization existingUser : users) {
            if (existingUser.getUsername().equalsIgnoreCase(username)) {
                Log.w(TAG, "Username '" + username + "' already exists.");
                return false;
            }
        }
        String passwordHash = hashPassword(password);
        if (passwordHash == null) {
            Log.e(TAG, "Failed to hash password during registration.");
            return false;
        }

        User_authorization newUser = new User_authorization(username, passwordHash);
        users.add(newUser);
        saveUsers(users);
        Log.i(TAG, "User '" + username + "' registered successfully.");
        return true;
    }
    public boolean loginUser(String username, String password) {
        if (username.isEmpty()  || password.isEmpty()) {
            Log.w(TAG, "Username or password empty during login.");
            return false;
        }
        List<User_authorization> users = loadUsers();
        String enteredPasswordHash = hashPassword(password);
        if (enteredPasswordHash == null) {
            Log.e(TAG, "Failed to hash password during login.");
            return false;
        }
        for (User_authorization user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                if (user.getPasswordHash().equals(enteredPasswordHash)) {
                    Log.i(TAG, "User '" + username + "' logged in successfully.");
                    return true;
                } else {
                    Log.w(TAG, "Incorrect password for user '" + username + "'.");
                    return false;
                }
            }
        }
        Log.w(TAG, "User '" + username + "' not found.");
        return false;
    }
    private void saveLoginState(String username) {
        SharedPreferences prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isLoggedIn", true);
        editor.putString("loggedInUser", username);
        editor.apply();
    }
    public boolean isLoggedIn() {
         SharedPreferences prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);
         return prefs.getBoolean("isLoggedIn", false);
    }
     public String getLoggedInUser() {
         SharedPreferences prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);
         return prefs.getString("loggedInUser", null);
     }
     public void logoutUser() {
        SharedPreferences prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("isLoggedIn");
        editor.remove("loggedInUser");
        editor.apply();
     }
}
