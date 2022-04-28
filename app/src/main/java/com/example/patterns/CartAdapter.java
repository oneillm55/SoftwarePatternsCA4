package com.example.patterns;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    //private ArrayList<Item> itemsList;// add data from firebase
    private Context context;
    private List<Item> itemList;
    private recyclerOnClickListener listener;


    public CartAdapter(Context context, List<Item> itemList, recyclerOnClickListener listener) {
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
        private TextView itemName, itemPrice;
        private ImageView itemImage;

        public MyViewHolder(final View view) {
            super(view);
            itemName = view.findViewById(R.id.cart_item_name_title);
            itemPrice = view.findViewById(R.id.cart_item_price);
            itemImage = view.findViewById(R.id.cart_item_image);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items, parent,false);
       return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
   // String name= itemList.get(position).getTitle();
    holder.itemName.setText(itemList.get(position).getTitle());
    holder.itemPrice.setText(Double.toString(itemList.get(position).getPrice()) );
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
