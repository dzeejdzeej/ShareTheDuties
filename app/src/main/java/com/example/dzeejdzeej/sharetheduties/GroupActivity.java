package com.example.dzeejdzeej.sharetheduties;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView loggedUser, logOut, groupNameText;
    private Button searchButton, createButton;
    private String username;
    String URL = "http://192.168.1.217/sharetheduties/indexGroup.php";

    JSONParser jsonParser = new JSONParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        username = getIntent().getStringExtra("username");
        groupNameText = (TextView) findViewById(R.id.groupNameText);
        logOut = (TextView) findViewById(R.id.logOut);
        logOut.setOnClickListener(this);
        loggedUser = (TextView) findViewById(R.id.loggedUser);
        loggedUser.setText(username);
        searchButton = (Button) findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
        createButton = (Button) findViewById(R.id.createButton);
        createButton.setOnClickListener(this);
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

            case R.id.createButton:
                CreateGroup createGroup = new CreateGroup();
                createGroup.execute(groupNameText.getText().toString(), username);
                break;
        }
    }

    private class CreateGroup extends AsyncTask<String, String, JSONObject> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected JSONObject doInBackground(String... args) {

            String username = args[1];
            String groupname = args[0];

            ArrayList params = new ArrayList();
            params.add(new BasicNameValuePair("username", username));
            params.add(new BasicNameValuePair("groupname", groupname));

            JSONObject json = jsonParser.makeHttpRequest(URL, "POST", params);

            return json;
        }

        protected void onPostExecute(JSONObject result) {

            try {
                if (result != null) {
                    Toast.makeText(getApplicationContext(),result.getString("message"),Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Unable to retrieve any data from server", Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
