package com.example.patterns;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.patterns.databinding.ActivityNavBinding;
import com.example.patterns.databinding.ActivityUpdateItemBinding;

public class UpdateItemActivity extends AppCompatActivity {
    TextView textView ;
    ActivityUpdateItemBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);
        binding = ActivityUpdateItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        textView=binding.updateId;
    Bundle extras = getIntent().getExtras();
    if(extras != null){
        textView.setText(extras.getString("item"));
    }

    }
}