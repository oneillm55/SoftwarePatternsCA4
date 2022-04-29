package com.example.patterns.customerFragments.home;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.patterns.Item;
import com.example.patterns.R;
import com.example.patterns.RecyclerAdapter;
import com.example.patterns.UpdateItemActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustHomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<Item> itemsList = new ArrayList<>();
    private RecyclerAdapter recyclerAdapter;
    private RecyclerAdapter.recyclerOnClickListener listener;
    private DatabaseReference mDatabase;
    private Item item;
    private FirebaseAuth firebaseAuth;
    private EditText search;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cust_home, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        search=view.findViewById(R.id.editTextSearch) ;
        itemsList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        recyclerView = view.findViewById(R.id.cust_recycler_view);
        setItemInfo();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
            filter(editable.toString());
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    private void filter(String text) {
        ArrayList<Item> filteredList = new ArrayList<>();
        for (Item i: itemsList){
            if(item.getTitle().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(i);
            };
        }

        recyclerAdapter.filterList(filteredList);
    }


    private void setItemInfo() {
        //set items from firebase to a list
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("items");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    item = dataSnapshot.getValue(Item.class);
                    item.setId(dataSnapshot.getKey());
                    itemsList.add(item);


                    Log.d("list", "Items:" + itemsList.size());
                }
                recyclerView.setHasFixedSize(true);
                setOnClickListener();
                recyclerAdapter = new RecyclerAdapter(getContext(), itemsList, listener);
                recyclerView.setAdapter(recyclerAdapter);
            }

            private void setOnClickListener() {
                listener = new RecyclerAdapter.recyclerOnClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        Toast.makeText(getContext(), "Click", Toast.LENGTH_SHORT).show();
                        //add to cart

                        new AlertDialog.Builder(getContext())
                                .setTitle("Cart")
                                .setMessage("Do you want to add this item to your cart?")

                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //add to cart

                                        mDatabase.child("cart").child(firebaseAuth.getUid()).child("items").setValue(item);
                                        // mDatabase.child("cart").child("1").child("items").setValue(item);
                                    }
                                })

                                .setNegativeButton(android.R.string.no, null)
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .show();
                    }
                };
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}