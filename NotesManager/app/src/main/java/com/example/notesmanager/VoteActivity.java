package com.example.notesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class VoteActivity extends AppCompatActivity {

    private int note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(1);
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(2);
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(3);
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(5);
            }
        });
        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(8);
            }
        });
        findViewById(R.id.button13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(13);
            }
        });
        findViewById(R.id.button21).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(21);
            }
        });
        findViewById(R.id.buttonCoffe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(-1);
            }
        });

        findViewById(R.id.voteButon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listingVote();
            }
        });

    }

    private void listingVote()
    {
        
    }

    public void voteWhat(int note){
        this.note = note;
    }
}
