package com.example.notesmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class VoteActivity extends AppCompatActivity {

    private int note, userID, taskID;
    private DatabaseHelper databaseHelper;
    private EditText mTaskDescriptionText;
    private Button mVoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        this.databaseHelper = new DatabaseHelper(this);
        mTaskDescriptionText = findViewById(R.id.taskDescriptionText);


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
        findViewById(R.id.button34).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(34);
            }
        });
        findViewById(R.id.buttonCoffe).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteWhat(-1);
            }
        });

        mVoteButton = findViewById(R.id.voteButon);
        mVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listingVote();
            }
        });

        //this.setTaskAndSelected();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.setTaskAndSelected();
    }

    private void setTaskAndSelected(){
        Cursor c = databaseHelper.getLastUser();
        while (c.moveToNext()){
            this.userID = c.getInt(0);
        }

        Cursor res = this.databaseHelper.fetchTasks(userID);
        if(res!=null &&  res.moveToFirst())
        {
            this.taskID = res.getInt(0);
            mTaskDescriptionText.setText(res.getString(1));
        }else{
            mTaskDescriptionText.setText("There are no more tasks.");
            mVoteButton.setEnabled(false);
        }
    }

    private void listingVote()
    {
        Cursor c = databaseHelper.getLastUser();
        while (c.moveToNext()){
            this.databaseHelper.insertVotes(this.userID,this.taskID, this.note);
            Intent intent = new Intent(this, ListingActivity.class);
            intent.putExtra("EXTRA_TASK_ID", this.taskID);
            startActivity(intent);
        }
    }

    public void voteWhat(int note){
        this.note = note;
    }
}
