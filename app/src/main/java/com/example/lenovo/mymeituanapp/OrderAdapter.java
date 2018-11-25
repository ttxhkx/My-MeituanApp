package com.example.lenovo.mymeituanapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<Order> list;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView OrderName;
        TextView OrderPrice;
        TextView Personl;
        View OrderView;

        public ViewHolder(View view) {
            super(view);
            OrderView = view;
            OrderName = (TextView) view.findViewById(R.id.ord_name);
            OrderPrice = (TextView) view.findViewById(R.id.ord_price);
            Personl = (TextView)view.findViewById(R.id.Person);
        }
    }
    public OrderAdapter(List<Order>list){this.list = list;}
    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_order, viewGroup, false);
        final OrderAdapter.ViewHolder holder = new OrderAdapter.ViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Order order = list.get(i);
        viewHolder.OrderName.setText(order.getName());
        viewHolder.OrderPrice.setText(String.valueOf(order.getPrice()));
        viewHolder.Personl.setText(order.getPerson());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

}
