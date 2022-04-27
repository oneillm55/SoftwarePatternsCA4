package com.example.patterns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    //private ArrayList<Item> itemsList;// add data from firebase
    private Context context;
    private List<Item> itemList;

    public RecyclerAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    //    public RecyclerAdapter(ArrayList<Item> itemsList, Context context) {
//        this.itemsList = itemsList;
//        this.context = context;
//    }

//    public RecyclerAdapter(ArrayList<Item> itemsList){
//        this.itemsList = itemsList;
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView itemName;

        public MyViewHolder(final View view) {
            super(view);
            itemName = view.findViewById(R.id.item_name_title);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent,false);
       return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
   // String name= itemList.get(position).getTitle();
    holder.itemName.setText(itemList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }
}
