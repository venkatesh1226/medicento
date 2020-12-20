package com.example.basic;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ShopList extends FirebaseRecyclerAdapter<ShoppingItems, ShopList.shopListViewHolder> {
    Context context;
    String content;

    public ShopList(@NonNull FirebaseRecyclerOptions<ShoppingItems> options, String btn) {

        super(options);
        content = btn;
    }

    @NonNull
    @Override
    public shopListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.shop_item, parent, false);
        return new shopListViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull shopListViewHolder shopListViewHolder, int i, @NonNull ShoppingItems shoppingItems) {
        Log.e("info", shoppingItems.toString());
        init(shopListViewHolder, shoppingItems);
        listeners(shopListViewHolder, shoppingItems);
    }

    void init(shopListViewHolder v, ShoppingItems item) {
        v.txtName.setText(item.getVaccine());
        v.txtPrice.setText("₹ " + item.price);
        if (!content.equals("add")) {
            v.btnAddCart.setBackgroundColor(Color.RED);
            v.btnAddCart.setTextColor(Color.WHITE);
            v.btnAddCart.setText("X Remove");
        }
    }

    void listeners(shopListViewHolder v, ShoppingItems item) {
        v.btnAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (content.equals("add")) {
                    FirebaseDatabase.getInstance().getReference().child("Cart").child("Userid").child(item.getId()).setValue(item);
                    Toast.makeText(context, item.getVaccine() + " is added to your cart", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance().getReference().child("Cart").child("Userid").child(item.getId()).removeValue();
                    Toast.makeText(context, item.getVaccine() + " is removed from your cart", Toast.LENGTH_LONG).show();
                }
            }
        });
        v.txtSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "Item Name - " + item.vaccine + "\n Price " + item.price + "\n Available Stock " + item.availabilityNo + "\n From " + item.shopName + "\n Address " + item.address;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(s);
                builder.create().show();
            }
        });
    }

    public class shopListViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtPrice, txtSeeMore;
        Button btnAddCart;

        public shopListViewHolder(@NonNull View v) {
            super(v);
            txtName = v.findViewById(R.id.txt_name);
            txtPrice = v.findViewById(R.id.txt_price);
            txtSeeMore = v.findViewById(R.id.txt_more);
            btnAddCart = v.findViewById(R.id.btn_add);
        }
    }
}
