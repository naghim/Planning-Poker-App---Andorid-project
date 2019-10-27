package com.example.notesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        //hogyha a felhasznalo be van jelentkezve login inaktiv logoff megjelenik

        SharedPreferences loginSettings = getSharedPreferences("loginPreferences", MODE_PRIVATE);
        final String loginName = loginSettings.getString("LoginName", "");
        if (loginName != "") {
            findViewById(R.id.login).setEnabled(false);
            findViewById(R.id.logOff).setVisibility(View.VISIBLE);
            ((EditText)findViewById(R.id.nameText)).setText(loginName);
            findViewById(R.id.voteNext).setVisibility(View.VISIBLE);
        }

        findViewById(R.id.logOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoff();
            }
        });

        findViewById(R.id.voteNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vote();
            }
        });

    }

    private void vote()
    {
        Intent intent = new Intent(this, VoteActivity.class);
        startActivity(intent);
    }

    private void logoff(){
        findViewById(R.id.login).setEnabled(true);

        findViewById(R.id.logOff).setVisibility(View.INVISIBLE); //elrejtjuk a logoff gombot
        findViewById(R.id.voteNext).setVisibility(View.INVISIBLE);

        ((EditText)findViewById(R.id.nameText)).setText("");

        SharedPreferences loginSettings = getSharedPreferences("loginPreferences", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = loginSettings.edit();
        prefEditor.clear();
    }

    private void login(){
        Intent intent = new Intent(this, VoteActivity.class);
        startActivity(intent);

        // Felhasznalo bejelentkezesekor eltaroljuk  a nevet egy shared preferencebe.
        SharedPreferences loginSettings = getSharedPreferences("loginPreferences", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = loginSettings.edit();
        prefEditor.clear();

        EditText loginText = findViewById(R.id.nameText);
        String loginString= loginText.getEditableText().toString();
        prefEditor.putString("LoginName",loginString);

        // Itt beallitja gombok allapotat
        findViewById(R.id.login).setEnabled(false);
        findViewById(R.id.logOff).setVisibility(View.VISIBLE);
        findViewById(R.id.voteNext).setVisibility(View.VISIBLE);



        prefEditor.commit();
    }
}
