package com.example.lenovo.mymeituanapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    int i = 0;
    private MyDatabaseHelper dbHelper;
    private MyDatabaseHelper dbHelper2;
    private List<Order> list = new ArrayList<>();
    String id_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        id_data = intent.getStringExtra("ID");
        dbHelper = new MyDatabaseHelper(this,"Users.db",null,1);
        dbHelper2 = new MyDatabaseHelper(this,"Foods.db",null,1);
        initShops();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rvOrder);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        OrderAdapter adapter = new OrderAdapter(list);
        recyclerView.setAdapter(adapter);
        }
        private void initShops(){
            String name;
            double price;
            SQLiteDatabase db2 = dbHelper2.getWritableDatabase();
            Cursor cursor;
            cursor = db2.query("Foods",new String[]{"Price","OFoodName"},null,null,null,null,null);
            if (cursor.moveToFirst()){
                do {
                    price = Double.parseDouble(cursor.getString(cursor.getColumnIndex("Prince")));
                    name = cursor.getString(cursor.getColumnIndex("OFoodName"));
                    Toast.makeText(OrderActivity.this,name,Toast.LENGTH_SHORT).show();
                    Order order = new Order();
                    order.setName(name);
                    order.setPrice(price);
                    list.add(order);
                    i++;
                }while (cursor.moveToNext());
            }
            cursor.close();
        }

}
