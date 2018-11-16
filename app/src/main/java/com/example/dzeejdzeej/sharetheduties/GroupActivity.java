package com.example.dzeejdzeej.sharetheduties;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GroupActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView loggedUser, logOut;
    private Button searchButton, createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        String username = getIntent().getStringExtra("username");
        logOut = (TextView) findViewById(R.id.logOut);
        logOut.setOnClickListener(this);
        loggedUser = (TextView) findViewById(R.id.loggedUser);
        loggedUser.setText(username);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.logOut:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;

            case R.id.searchButton:
                Intent intentGroup = new Intent(this, SerachActivity.class);
                startActivity(intentGroup);
                break;
        }
    }

}
