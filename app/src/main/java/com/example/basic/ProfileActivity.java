package com.example.basic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import static android.widget.Toast.makeText;

public class ProfileActivity extends AppCompatActivity {

    EditText etname, etEmail, etAddress;
    Button button;
    ImageView imageView;
    ProgressBar progressBar;
    Uri uri,uri2;
    UploadTask uploadTask;
    StorageReference storageReference;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference;
    private static final int PICK_IMAGE = 404;
    String currUserId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);
        init();
        listeners();
    }




    private void init() {
        etname = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        etEmail = findViewById(R.id.etEmail);
        button = findViewById(R.id.save);
        imageView = findViewById(R.id.ivPf);
        progressBar = findViewById(R.id.progressBar);
    }

    private void listeners() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "PICK an IMAGE"), PICK_IMAGE);
            }

        });

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                if (etname.getText().toString().trim().length() == 0) {
                    etname.setError("Name is Required");
                } else if (etAddress.getText().toString().trim().length() == 0) {
                    etAddress.setError("Address is required");
                } else if (etEmail.getText().toString().trim().length() == 0) {
                    etEmail.setError("Email is required");
                } else {
                    ref = FirebaseDatabase.getInstance().getReference();
                    final String id = ref.push().getKey();
                    //Toast.makeText(getContext(), "hello world", Toast.LENGTH_SHORT).show();
                    Item item = new Item(id,etname.getText().toString(),"",etAddress.getText().toString(),etEmail.getText().toString());

                    ref.child("usedetails").child(id).setValue(item);
                    final StorageReference imgRef = FirebaseStorage.getInstance().getReference().child("usedetails").child(id);
                    imgRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri link) {
                                    uri2 = link;
                                    Log.e("Link",link.toString());
                                    ref.child("usedetails").child(id).child("image").setValue(link.toString());
                                }
                            }) ;
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProfileActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Toast.makeText(ProfileActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    //dismiss();
                }

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == PICK_IMAGE && resultCode == RESULT_OK && null!=data.getData()){

            imageView.setVisibility(View.VISIBLE);
            imageView.setImageURI(data.getData());
            uri = data.getData();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}

