package com.example.basic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CartFragment extends Fragment {

    RecyclerView menu;
    DatabaseReference ref;
    ShopList menuItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_cart2, container, false);
       init(v);
       listeners(v);
       return v;
    }

    private void listeners(View v) {
    }

    private void init(View v) {
        menu = v.findViewById(R.id.rec_shop);
        ref = FirebaseDatabase.getInstance().getReference().child("Cart").child("Userid");

        FirebaseRecyclerOptions<ShoppingItems> options =
                new FirebaseRecyclerOptions.Builder<ShoppingItems>()
                        .setQuery(ref, ShoppingItems.class)
                        .build();

        menuItem = new ShopList(options, "remove");
        menu.setLayoutManager(new LinearLayoutManager(getActivity()));
        menu.setAdapter(menuItem);
    }
    @Override
    public void onStart() {
        super.onStart();
        menuItem.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        menuItem.stopListening();
    }
}