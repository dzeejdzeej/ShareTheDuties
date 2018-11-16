package com.example.dzeejdzeej.sharetheduties;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    TextView loginText;
    EditText usernameText;
    EditText passText;
    EditText passConfirmText;
    Button registerButton;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loginText = (TextView) findViewById(R.id.loginText);
        loginText.setOnClickListener(this);
        usernameText = (EditText) findViewById(R.id.usernameText);
        passText = (EditText) findViewById(R.id.passText);
        passConfirmText = (EditText) findViewById(R.id.passConfirmText);
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.loginText:
                openActivity();
                break;

            case R.id.registerButton:
               username = usernameText.getText().toString();
               password = passText.getText().toString();
               if (password.equals(passConfirmText.getText().toString())) {
                   Users user = new Users(username, password);
                   openActivityAndSendData();
               }
               else{
                   Toast toast = Toast.makeText(getApplicationContext(), "Check password!", Toast.LENGTH_SHORT);
                   toast.show();
               }
        }
    }

    public void openActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openActivityAndSendData(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username",username);
        intent.putExtra("password",password);
        startActivity(intent);
    }
}
