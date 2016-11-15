package com.example.tomi.firebasetest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;


/**
 * Created by Tomi Lämsä on 11.11.2016.
 */
public class JournalAdapter extends RecyclerView.Adapter<JournalAdapter.ViewHolder> {


    public JournalAdapter(DatabaseReference databaseReference) {

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(TextView v){
            super(v);
            textView = v;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public JournalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        // ...
        ViewHolder vh = new ViewHolder((TextView)v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //holder.textView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //return mDataset.length;
        return 0;
    }
}
