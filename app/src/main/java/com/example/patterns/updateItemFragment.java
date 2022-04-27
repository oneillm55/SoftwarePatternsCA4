package com.example.patterns;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class updateItemFragment extends Fragment {


    public updateItemFragment() {
        // Required empty public constructor
    }

    public View onCreate(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_update_item, container, false);

        TextView textView;
        // Inflate the layout for this fragment
        return view;


    }
}