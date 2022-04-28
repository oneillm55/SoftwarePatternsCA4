package com.example.patterns;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.patterns.databinding.ActivityNavBinding;
import com.example.patterns.databinding.ActivityUpdateItemBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UpdateItemActivity extends AppCompatActivity {
    private EditText title,price,stock;
    private String id;
    private Item item;
    private Button updateButton,deleteButton;
    private ActivityUpdateItemBinding binding;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_item);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        binding = ActivityUpdateItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        title=binding.editTextEditItemTitle;
        price=binding.editTextEditPrice;
        stock=binding.editTextEditStock;
        updateButton=binding.buttonUpdateItem;
        deleteButton= binding.buttonDeleteItem;

    Bundle extras = getIntent().getExtras();
    if(extras != null){
        id = extras.getString("id");

        title.setText(extras.getString("title"));
        price.setText(extras.getString("price"));
        stock.setText(extras.getString("stock"));



        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("items").child(id);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                item = dataSnapshot.getValue(Item.class);
                Log.d("item title",item.getTitle());
               // System.out.println(item);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

    updateButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            title.getText();
            price.getText();
            stock.getText();

            mDatabase.child("items").child(id).child("title").setValue(title.getText().toString());
            mDatabase.child("items").child(id).child("price").setValue(Double.parseDouble(price.getText().toString()));
            mDatabase.child("items").child(id).child("stockLevel").setValue(Integer.parseInt(stock.getText().toString()));


        }
    });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase.child("items").child(id).removeValue();


            }
        });

    }
}