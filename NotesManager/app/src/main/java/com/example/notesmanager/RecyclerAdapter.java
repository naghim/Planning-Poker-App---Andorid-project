package com.example.notesmanager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecycleViewHolder> {

    private ArrayList<RecyclerItem> mRecyclerItemArrayList;

    public static class RecycleViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextName,mTextTask,mTextValue;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.textName);
            mTextTask = itemView.findViewById(R.id.textTask);
            mTextValue = itemView.findViewById(R.id.textValue);
        }
    }

    public RecyclerAdapter(ArrayList<RecyclerItem> recyclerItemArrayList){
        this.mRecyclerItemArrayList = recyclerItemArrayList;
    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        RecycleViewHolder rvh = new RecycleViewHolder(view);
        return rvh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {
        RecyclerItem currentItem = mRecyclerItemArrayList.get(position);

        holder.mTextName.setText(currentItem.getName());
        holder.mTextTask.setText(currentItem.getTask());
        holder.mTextValue.setText(currentItem.getValue());
    }

    @Override
    public int getItemCount() {
        return mRecyclerItemArrayList.size();
    }
}
