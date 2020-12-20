package com.example.basic;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileInfoFragment extends Fragment {
    TextView tvName, tvEmail, tvAddress,tvVacBooked,tvVacPending;
    Button button;
    ImageView imageView;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference  user_data;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_profile_info, container, false);
        init(view);
        return view;
    }

    private void init(View v) {
        tvName =v. findViewById(R.id.tvname);
        tvEmail = v.findViewById(R.id.tvemail);
        tvAddress =v. findViewById(R.id.tvaddress);
        button = v.findViewById(R.id.Edit);
        tvVacBooked = v.findViewById(R.id.vacBooked);
        tvVacPending =v. findViewById(R.id.vacPending);
        imageView = v.findViewById(R.id.ivPf);
        user_data = database.getReference("usedetails").child("-MOyHTXPSTfL8jS4Z86R");
        user_data.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Item item = snapshot.getValue(Item.class);
                //Log.e("snapshot",snapshot.getValue(Item.class).toString());
                tvName.setText(item.getName());
                tvAddress.setText(item.getAddress());
                tvEmail.setText(item.getEmail());
                Glide.with(getContext()).load(item.getImage()).into(imageView);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
               startActivity(new Intent(getActivity(), ProfileActivity.class));
            }
        });
    }
}

