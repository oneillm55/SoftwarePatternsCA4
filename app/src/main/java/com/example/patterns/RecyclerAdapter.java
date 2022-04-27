package com.example.patterns;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    //private ArrayList<Item> itemsList;// add data from firebase
    private Context context;
    private List<Item> itemList;
    private recyclerOnClickListener listener;


    public RecyclerAdapter(Context context, List<Item> itemList, recyclerOnClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener= listener;
    }

    //    public RecyclerAdapter(ArrayList<Item> itemsList, Context context) {
//        this.itemsList = itemsList;
//        this.context = context;
//    }

//    public RecyclerAdapter(ArrayList<Item> itemsList){
//        this.itemsList = itemsList;
//    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView itemName,itemManufacturer,itemCategory, itemPrice;
        private ImageView itemImage;

        public MyViewHolder(final View view) {
            super(view);
            itemName = view.findViewById(R.id.item_name_title);
            itemCategory = view.findViewById(R.id.item_category);
            itemManufacturer = view.findViewById(R.id.item_manufacturer);
            itemPrice = view.findViewById(R.id.item_price);
            itemImage = view.findViewById(R.id.item_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
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
    holder.itemManufacturer.setText(itemList.get(position).getManufacturer());
    holder.itemCategory.setText(itemList.get(position).getCategory());
    holder.itemPrice.setText(Double.toString(itemList.get(position).getPrice()) );
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageReference = storage.getReference();
//        StorageReference ref = storageReference.child("images/" +itemList.get(position).getImage());
        Glide.with(this.context)
                .load(itemList.get(position).getImage())
                .into(holder.itemImage);


    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }

    public interface recyclerOnClickListener{
        void onClick(View v, int position);
    }
}
