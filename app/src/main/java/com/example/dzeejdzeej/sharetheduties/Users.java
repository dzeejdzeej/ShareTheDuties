package com.example.dzeejdzeej.sharetheduties;

import android.support.v7.app.AppCompatActivity;

public class Users extends AppCompatActivity {

    private String username;
    private String password;


    public Users(String name, String pass){
        this.username = name;
        this.password = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
