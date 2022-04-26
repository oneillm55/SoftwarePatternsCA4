package com.example.patterns;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class usersRecyclerAdapter extends RecyclerView.Adapter<usersRecyclerAdapter.MyViewHolder> {

    private ArrayList<User> usersList;// add data from firebase

    public usersRecyclerAdapter(ArrayList<User> usersList){
        this.usersList = usersList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView userName;

        public MyViewHolder(final View view) {
            super(view);
            userName = view.findViewById(R.id.item_name_display);
        }
    }
    @NonNull
    @Override
    public usersRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent,false);
       return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull usersRecyclerAdapter.MyViewHolder holder, int position) {
    String name = usersList.get(position).getUsernname();
    holder.userName.setText(name);

    }

    @Override
    public int getItemCount() {

        return usersList.size();
    }
}
