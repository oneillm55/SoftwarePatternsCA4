package com.example.patterns.customerFragments.home;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustHomeFragment extends Fragment {

    //private ArrayList<Item> itemsList;
    private RecyclerView recyclerView;
    private List<Item> itemsList = new ArrayList<>();
    private RecyclerAdapter recyclerAdapter;
    private RecyclerAdapter.recyclerOnClickListener listener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.admin_recycler_view);
        setItemInfo();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), layoutManager.getOrientation()));

        // Inflate the layout for this fragment
        return view;
    }


    private void setItemInfo() {
        //set items from firebase to a list
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("items");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Item item = dataSnapshot.getValue(Item.class);
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
                    }
                };
            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}