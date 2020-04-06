package com.bdshikkhok.dashboard.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdshikkhok.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<String> myDataset=new ArrayList<String>();

    View view;
    Context ct;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.nameList);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<String> myDataset) {
        this.myDataset = myDataset;
        //ct=postList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        MyViewHolder vh = new MyViewHolder(itemView);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        MyName myName=new MyName(myDataset.get(position));
        holder.title.setText(myName.getName().toString());

    }

    @Override
    public int getItemCount() {
        return myDataset.size();
    }

}