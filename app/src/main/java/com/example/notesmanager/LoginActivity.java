package com.example.notesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText mNameText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mNameText = findViewById(R.id.nameText);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login(){
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        databaseHelper.insertUser(mNameText.getText().toString());
        Intent intent = new Intent(this, VoteActivity.class);
        startActivity(intent);
    }
}
