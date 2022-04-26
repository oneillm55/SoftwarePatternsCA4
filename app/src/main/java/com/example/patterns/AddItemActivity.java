package com.example.patterns;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.patterns.databinding.ActivityAddItemBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText itemTitle,itemPrice;
    private Button addItemButton;
    private Spinner manufacturerSpinner,categorySpinner;
    private ActivityAddItemBinding binding;
    private String  manufacturerString,categoryString;

    private FirebaseAuth firebaseAuth;
    private String userID;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_add_item);

        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        itemTitle = binding.editTextItemTitle;
        itemPrice= binding.editTextPrice;
        addItemButton=binding.buttonAddItem;
        manufacturerSpinner= binding.manufacturerSpinner;
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this, R.array.manufacturer_spinner_options, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        manufacturerSpinner.setAdapter(mAdapter);
        manufacturerSpinner.setOnItemSelectedListener(this);

        categorySpinner= binding.categorySpinner;
        ArrayAdapter<CharSequence> cAdapter = ArrayAdapter.createFromResource(this, R.array.category_spinner_options, android.R.layout.simple_spinner_item);
        cAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        categorySpinner.setAdapter(cAdapter);
        categorySpinner.setOnItemSelectedListener(this);

        firebaseAuth = FirebaseAuth.getInstance();


        mDatabase = FirebaseDatabase.getInstance().getReference();


        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btitle=itemTitle.getText().toString().trim();
                String bprice=itemPrice.getText().toString().trim();
                String bmaufacturer=manufacturerSpinner.getSelectedItem().toString().trim();
                String bcategory=categorySpinner.getSelectedItem().toString().trim();




                if(btitle.isEmpty() || bprice.isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Please insure all fields are filled out", Toast.LENGTH_SHORT).show();
                }else{

                    //add item

                            if(true){//to do :add check

                                //add item to database here



                                Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT).show();


                            }else{
                                Toast.makeText(getApplicationContext(), "Failed to add item", Toast.LENGTH_SHORT).show();
                                //Log.e("error", "onComplete: Failed=" + task.getException().getMessage());

                            }


                }
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}