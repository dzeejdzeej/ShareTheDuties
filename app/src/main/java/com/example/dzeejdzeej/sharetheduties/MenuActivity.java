package com.example.dzeejdzeej.sharetheduties;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView loggedUser, logOut;
    private Button groupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String username = getIntent().getStringExtra("username");
        logOut = (TextView) findViewById(R.id.logOut);
        logOut.setOnClickListener(this);
        loggedUser = (TextView) findViewById(R.id.loggedUser);
        loggedUser.setText(username);
        groupButton = (Button) findViewById(R.id.groupButton);
        groupButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.logOut:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;

            case R.id.groupButton:
                Intent intentGroup = new Intent(this, GroupActivity.class);
                startActivity(intentGroup);
                break;
        }
    }

}
