package com.example.basic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileInfo extends AppCompatActivity {
    TextView tvName, tvEmail, tvAddress,tvVacBooked,tvVacPending;
    Button button;
    ImageView imageView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference  user_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_info);
        tvName = findViewById(R.id.tvname);
        tvEmail = findViewById(R.id.tvemail);
        tvAddress = findViewById(R.id.tvaddress);
        button = findViewById(R.id.Edit);
        tvVacBooked = findViewById(R.id.vacBooked);
        tvVacPending = findViewById(R.id.vacPending);
        imageView = findViewById(R.id.ivPf);
        //user_data = database.getReference("usedetails").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        user_data = database.getReference("usedetails").child("-MOyHTXPSTfL8jS4Z86R");
        user_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Item item = snapshot.getValue(Item.class);
                 //Log.e("snapshot",snapshot.getValue(Item.class).toString());
                 tvName.setText(item.getName());
                 tvAddress.setText(item.getAddress());
                 tvEmail.setText(item.getEmail());
                Glide.with(getApplicationContext()).load(item.getImage()).into(imageView);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}

