package com.example.dzeejdzeej.sharetheduties;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView registerTextView, loginTextView;
    private EditText loginText, passText, emailText;
    private Button logButton, regButton;
    private String loggedUser;
    String URL = "http://192.168.1.217/sharetheduties/index.php";

    JSONParser jsonParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerTextView = (TextView) findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(this);
        loginTextView = (TextView) findViewById(R.id.loginTextView);
        loginTextView.setOnClickListener(this);
        loginText = (EditText) findViewById(R.id.loginText);
        passText = (EditText) findViewById(R.id.passText);
        emailText = (EditText) findViewById(R.id.emailText);
        logButton = (Button) findViewById(R.id.logButton);
        logButton.setOnClickListener(this);
        regButton = (Button) findViewById(R.id.regButton);
        regButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.registerTextView:
                registerTextView.setVisibility(View.GONE);
                logButton.setVisibility(View.GONE);
                regButton.setVisibility(View.VISIBLE);
                loginTextView.setVisibility(View.VISIBLE);
                emailText.setVisibility(View.VISIBLE);
                break;

            case R.id.loginTextView:
                emailText.setVisibility(View.GONE);
                loginTextView.setVisibility(View.GONE);
                regButton.setVisibility(View.GONE);
                logButton.setVisibility(View.VISIBLE);
                registerTextView.setVisibility(View.VISIBLE);
                break;

            case R.id.logButton:
                AttemptLogin attemptLogin= new AttemptLogin();
                attemptLogin.execute(loginText.getText().toString(),passText.getText().toString(),"");
                break;

            case R.id.regButton:
                AttemptLogin attemptRegister= new AttemptLogin();
                attemptRegister.execute(loginText.getText().toString(),passText.getText().toString(),emailText.getText().toString());
                break;

        }
    }

    private class AttemptLogin extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            String email = args[2];
            String password = args[1];
            String username = args[0];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("password", password));

            if(email.length()>0)
                params.add(new BasicNameValuePair("email",email));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);
            loggedUser = username;

            return json;
        }

        protected void onPostExecute(JSONObject result) {
            // dismiss the dialog once product deleted
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                    if (result.getString("success").equals("1")){
                        openMenu(loggedUser);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void openMenu(String text) {
        Intent intent = new Intent(this, MenuActivity.class);
        intent.putExtra("username", text);
        startActivity(intent);
    }
}