package com.example.notesmanager;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListingActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<RecyclerItem> recyclerItemsList;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        mDatabaseHelper = new DatabaseHelper(this);

        this.createRecycleList();
        this.buildRecycleView();
    }

    public void createRecycleList(){
        recyclerItemsList = new ArrayList<>();
        this.getAllVotes();
    }

    public void buildRecycleView(){
        mRecyclerView = findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerAdapter(recyclerItemsList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void getAllVotes(){
        //TODO : Add the correct task_id
        Cursor res = mDatabaseHelper.fetchVotes(1);
        if (res.getCount() == 0){
            Toast.makeText(this, "List is empty", Toast.LENGTH_SHORT).show();
            return;
        }else{
            while (res.moveToNext()){
                recyclerItemsList.add(new RecyclerItem(
                        res.getString(0),
                        res.getString(1),
                        res.getInt(2)));
            }
        }
    }
}
