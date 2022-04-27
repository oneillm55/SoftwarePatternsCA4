package com.example.patterns;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.patterns.databinding.ActivityAddItemBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class AddItemActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText itemTitle,itemPrice;
    private Button addItemButton, addImageButton;
    private Spinner manufacturerSpinner,categorySpinner;
    private ActivityAddItemBinding binding;
    private String  manufacturerString,categoryString, imageKey;

    private FirebaseAuth firebaseAuth;
    private String userID;
    private DatabaseReference mDatabase;
    public Uri imageUri;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_add_item);

        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        imageView=binding.addItemImageView;
        itemTitle = binding.editTextItemTitle;
        itemPrice= binding.editTextPrice;
        addItemButton=binding.buttonAddItem;
        addImageButton=binding.buttonAddImage;
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

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });


        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String btitle=itemTitle.getText().toString().trim();
                Double bprice=Double.parseDouble(itemPrice.getText().toString().trim());
                String bmaufacturer=manufacturerSpinner.getSelectedItem().toString().trim();
                String bcategory=categorySpinner.getSelectedItem().toString().trim();




                if(btitle.isEmpty() || bprice==0 ){
                    Toast.makeText(getApplicationContext(), "Please insure all fields are filled out", Toast.LENGTH_SHORT).show();
                }else{

                    //add item

                            if(true){//to do :add check

                                //add item to database here

                                uploadImage();
                                final String itemID= UUID.randomUUID().toString();
                                Item item = new Item(btitle,bmaufacturer,bcategory,bprice,imageKey,1,itemID);
                                mDatabase.child("items").child(itemID).setValue(item);

                                Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT).show();


                            }else{
                                Toast.makeText(getApplicationContext(), "Failed to add item", Toast.LENGTH_SHORT).show();
                                //Log.e("error", "onComplete: Failed=" + task.getException().getMessage());

                            }


                }
            }
        });

    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        };
    }

    private void uploadImage() {
        final String randomKey = UUID.randomUUID().toString();
        imageKey=randomKey;
        StorageReference riversRef = storageReference.child("images/" + randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Snackbar.make(findViewById(android.R.id.content), "Image Upload successful",Snackbar.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed to upload image", Toast.LENGTH_SHORT).show();

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