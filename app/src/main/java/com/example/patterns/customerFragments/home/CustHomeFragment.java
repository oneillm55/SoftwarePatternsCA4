package com.example.patterns.customerFragments.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.patterns.R;
import com.example.patterns.User;
import com.example.patterns.usersRecyclerAdapter;

import java.util.ArrayList;


public class CustHomeFragment extends Fragment {

    private ArrayList<User> usersList;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cust_home, container, false);

        usersList = new ArrayList<User>();
        recyclerView = view.findViewById(R.id.cust_recycler_view);
        setUserInfo();
        setAdapter();

        // Inflate the layout for this fragment
        return view;
    }

    private void setAdapter() {
        usersRecyclerAdapter adapter =new usersRecyclerAdapter(usersList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setUserInfo() {
        usersList.add(new User("a","a","a"));
        usersList.add(new User("b","b","b"));
        usersList.add(new User("c","c","c"));
    }
}