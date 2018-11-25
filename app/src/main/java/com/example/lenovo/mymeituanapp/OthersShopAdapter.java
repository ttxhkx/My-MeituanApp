package com.example.lenovo.mymeituanapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class OthersShopAdapter extends RecyclerView.Adapter<OthersShopAdapter.ViewHolder> {
    private List<OthersShop> list;
    static class ViewHolder extends RecyclerView.ViewHolder {
        View OthersShopView;
        TextView shopName;
        TextView owner;

        public ViewHolder(View view) {
            super(view);
            OthersShopView = view;
            shopName = (TextView) view.findViewById(R.id.OShopName);
            owner = (TextView)view.findViewById(R.id.Oowner);

        }
    }
    public OthersShopAdapter(List<OthersShop> list){ this.list = list;}
    @NonNull
    @Override
    public OthersShopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewTape) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_othersshop,parent,false);
        final OthersShopAdapter.ViewHolder holder = new OthersShopAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OthersShopAdapter.ViewHolder viewHolder, int i) {
        OthersShop Oshop = list.get(i);
        viewHolder.shopName.setText(Oshop.getName());
        viewHolder.owner.setText(Oshop.getOwner());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
