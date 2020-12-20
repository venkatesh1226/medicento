package com.example.basic;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopFragment extends Fragment {
    RecyclerView menu;
    DatabaseReference ref;
    ShopList menuItem;
    EditText edtQuery;
    Button search;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop, container, false);
        init(view);
        listeners(view);
        return view;
    }

    private void init(View v) {
        menu = v.findViewById(R.id.rec_shop);
        edtQuery = v.findViewById(R.id.searchview);
        search = v.findViewById(R.id.search);
        ref = FirebaseDatabase.getInstance().getReference().child("Hospital/");

        FirebaseRecyclerOptions<ShoppingItems> options =
                new FirebaseRecyclerOptions.Builder<ShoppingItems>()
                        .setQuery(ref, ShoppingItems.class)
                        .build();

        menuItem = new ShopList(options, "add");
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

    private void listeners(View v) {
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = edtQuery.getText().toString();
                ref = FirebaseDatabase.getInstance().getReference().child("Hospital");

                FirebaseRecyclerOptions<ShoppingItems> options =
                        new FirebaseRecyclerOptions.Builder<ShoppingItems>()
                                .setQuery(ref, ShoppingItems.class)
                                .build();

                menuItem = new ShopList(options, "add");
                menu.setLayoutManager(new LinearLayoutManager(getActivity()));
                menu.setAdapter(menuItem);

            }
        });
    }
}