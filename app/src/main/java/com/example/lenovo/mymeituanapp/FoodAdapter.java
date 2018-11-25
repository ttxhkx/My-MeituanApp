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
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private  List<Food> list;
    public FoodAdapter(List<Food> list){this.list = list;}


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView FoodImage;
        TextView FoodName;
        TextView FoodPrice;
        View FoodView;

        public ViewHolder(View view) {
            super(view);
            FoodView = view;
            FoodImage = (ImageView) view.findViewById(R.id.iv_food);
            FoodName = (TextView) view.findViewById(R.id.foodName);
            FoodPrice = (TextView) view.findViewById(R.id.foodPrice);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_food, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Food food = list.get(i);
        viewHolder.FoodName.setText(food.getName());
        viewHolder.FoodPrice.setText(food.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
