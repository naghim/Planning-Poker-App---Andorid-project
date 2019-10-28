package com.example.notesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText mNameText;
    private SharedPreferences mPreferences;
    private  SharedPreferences.Editor mEditor;
    private DatabaseHelper databaseHelper;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        databaseHelper = new DatabaseHelper(this);

        mNameText = findViewById(R.id.nameText);

        if (getUserNameBySharedPreferences() != null){
            Intent intent = new Intent(this, VoteActivity.class);
            startActivity(intent);
        }

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void saveUserNameToSharedPreferences() {
        //save the name
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();
        mEditor.putString(getString(R.string.username), mNameText.getText().toString());
        mEditor.commit();
    }

    private String getUserNameBySharedPreferences(){
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        return mPreferences.getString(getString(R.string.username),null);
    }

    private void login(){
        databaseHelper.insertUser(mNameText.getText().toString());
        saveUserNameToSharedPreferences();

        Intent intent = new Intent(this, VoteActivity.class);
        startActivity(intent);
    }
}
