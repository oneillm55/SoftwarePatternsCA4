package com.example.patterns;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patterns.databinding.ActivityCustNavBinding;
import com.example.patterns.databinding.FragmentCustHomeBinding;
import com.example.patterns.databinding.ListItemsBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<User> usersList;// add data from firebase

    public recyclerAdapter(ArrayList<User> usersList){
        this.usersList = usersList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView itemName;

        public MyViewHolder(final View view) {
            super(view);
            itemName = view.findViewById(R.id.item_name_display);
        }
    }
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent,false);
       return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
    String name = usersList.get(position).getUsernname();
    holder.itemName.setText(name);

    }

    @Override
    public int getItemCount() {

        return usersList.size();
    }
}
