package com.example.lenovo.mymeituanapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MyShopAdapter extends RecyclerView.Adapter<MyShopAdapter.ViewHolder> {
    private List<MyShop> list;
    private MyDatabaseHelper dbHelper;
    private MyDatabaseHelper dbHelper2;
    String shopname;
    class ViewHolder extends RecyclerView.ViewHolder {
        View ShopView;
        TextView ShopName;
        TextView Owner;
        Button in;
        Button Delete;

        public ViewHolder(View view) {
            super(view);
            ShopView = view;
            ShopName = (TextView) view.findViewById(R.id.shopName);
            Owner = (TextView)view.findViewById(R.id.Owner);
            in = (Button)view.findViewById(R.id.in);
            Delete = (Button)view.findViewById(R.id.delete);
        }
    }
    public MyShopAdapter(List<MyShop> list){ this.list = list;}
    @NonNull
    @Override
    public MyShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewTape) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shop,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),FoodActivity.class);
                v.getContext().startActivities(new Intent[]{intent});
                intent.putExtra("shop",shopname);
            }
        });
        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new MyDatabaseHelper(v.getContext(),"Shops.db",null,1);
                dbHelper2 = new MyDatabaseHelper(v.getContext(),"Foods",null,1);
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setTitle("");
                dialog.setMessage("是否删除？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase db = dbHelper.getWritableDatabase();
                        SQLiteDatabase db2 = dbHelper2.getWritableDatabase();
                        db.delete("Shops","ShopName=?",new String[]{shopname});
                        db2.delete("Foods","OFoodName=?",new String[]{shopname});

                    }
                });
                dialog.show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyShopAdapter.ViewHolder viewHolder, int i) {
        MyShop shop = list.get(i);
        viewHolder.ShopName.setText(shop.getName());
        viewHolder.Owner.setText(shop.getOwner());
        shopname = shop.getName();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
