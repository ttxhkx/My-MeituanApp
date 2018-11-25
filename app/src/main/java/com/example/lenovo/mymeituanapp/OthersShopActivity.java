package com.example.lenovo.mymeituanapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OthersShopActivity extends AppCompatActivity {

    int i = 0;
    private MyDatabaseHelper dbHelper;
    private MyDatabaseHelper dbHelper2;
    private List<OthersShop> list = new ArrayList<>();
    String id_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_others_shop);
        Intent intent1 = getIntent();
        id_data = intent1.getStringExtra("ID");
        dbHelper = new MyDatabaseHelper(this,"Users.db",null,1);
        dbHelper2 = new MyDatabaseHelper(this,"Shops.db",null,1);
        initShops();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.othersShop);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        OthersShopAdapter adapter = new OthersShopAdapter(list);
        recyclerView.setAdapter(adapter);
    }
    private void initShops(){
        String[] name = new String[100];
        String[] owner = new String[100];
        SQLiteDatabase db2 = dbHelper2.getWritableDatabase();
        Cursor cursor;
        cursor = db2.query("Shops",new String[]{"Owner","ShopName"},null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                owner[i] = cursor.getString(cursor.getColumnIndex("Owner"));
                name[i] = cursor.getString(cursor.getColumnIndex("ShopName"));
                Toast.makeText(OthersShopActivity.this,name[i],Toast.LENGTH_SHORT).show();
                OthersShop shop = new OthersShop();
                shop.setName(name[i]);
                shop.setOwner(owner[i]);
                list.add(shop);
                i++;
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

}
