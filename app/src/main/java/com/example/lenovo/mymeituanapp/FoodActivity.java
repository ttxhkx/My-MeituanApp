package com.example.lenovo.mymeituanapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {

    int i = 0;
    private MyDatabaseHelper dbHelper;
    private MyDatabaseHelper dbHelper2;
    private List<Food> list = new ArrayList<>();
    String id_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        Intent intent = getIntent();
        id_data = intent.getStringExtra("shop");
        dbHelper = new MyDatabaseHelper(this, "Users.db", null, 1);
        dbHelper2 = new MyDatabaseHelper(this, "Foods.db", null, 1);
        FloatingActionButton newShop = (FloatingActionButton) findViewById(R.id.newFood);
        newShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FoodActivity.this, CreatFoodActivity.class);
                intent.putExtra("owner", id_data);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.food);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        FoodAdapter adapter = new FoodAdapter(list);
        recyclerView.setAdapter(adapter);
        initShops();
    }
    private void initShops(){
        String[] name = new String[100];
        String[] price = new String[100];
        String[] shop = new String[100];
        String[] owner = new String[100];
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor;
        cursor = db.query("Foods",new String[]{"Price","Shop","OFoodName"},null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                shop[i] = cursor.getString(cursor.getColumnIndex("Shops"));
                name[i] = cursor.getString(cursor.getColumnIndex("ShopName"));
                owner[i] = cursor.getString(cursor.getColumnIndex("Owner"));
                price[i] = cursor.getString(cursor.getColumnIndex("Price"));
                if (shop.equals(id_data)){
                    Food food = new Food();
                    food.setName(name[i]);
                    food.setPrice(price[i]);
                    list.add(food);
                }
                i++;
            }while (cursor.moveToNext());
        }
        cursor.close();
}
}
