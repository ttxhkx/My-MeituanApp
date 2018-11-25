package com.example.lenovo.mymeituanapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;import static com.example.lenovo.mymeituanapp.R.menu.user_item;

public class SecondActivity extends AppCompatActivity {

    int i = 0;
    private MyDatabaseHelper dbHelper;
    private MyDatabaseHelper dbHelper2;
    private List<MyShop> list = new ArrayList<>();
    private DrawerLayout mDrawerLayout;
    String id_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Intent intent1 = getIntent();
        id_data = intent1.getStringExtra("ID");
        dbHelper = new MyDatabaseHelper(this, "Users.db", null, 1);
        dbHelper2 = new MyDatabaseHelper(this, "Shops.db", null, 1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.user_drawer_layout);
        NavigationView user_inf = (NavigationView) findViewById(R.id.user_inf);
        user_inf.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if(id == R.id.Buy){
                    Intent intent = new Intent(SecondActivity.this, OthersShopActivity.class);
                    startActivity(intent);
                }
                if(id == R.id.exit){
                    finish();
                }
                if(id == R.id.cancel){
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    SQLiteDatabase db2 = dbHelper2.getWritableDatabase();
                    db.delete("Users", "id=?", new String[]{id_data});
                    db2.delete("Shops","Owner=?",new String[]{id_data});
                    Toast.makeText(SecondActivity.this, "注销完成", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                if(id == R.id.MyOrder||id == R.id.OthersOrder){
                    Intent intent = new Intent(SecondActivity.this,OrderActivity.class);
                    startActivity(intent);
                }
                return true;
            }
        });

        final FloatingActionButton newShop = (FloatingActionButton) findViewById(R.id.newShop);
        newShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, CreatShop.class);
                Intent intent2 = new Intent(SecondActivity.this,MyShopAdapter.class);
                intent.putExtra("owner", id_data);
                intent.putExtra("SName",id_data);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        list.clear();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyShopAdapter adapter = new MyShopAdapter(list);
        recyclerView.setAdapter(adapter);
        initShops();
    }
    private void initShops(){
        String[] name = new String[100];
        String[] owner = new String[100];
        Intent intent1 = getIntent();
        id_data = intent1.getStringExtra("ID");
        SQLiteDatabase db2 = dbHelper2.getWritableDatabase();
        Cursor cursor;
        cursor = db2.query("Shops",new String[]{"Owner","ShopName"},null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                owner[i] = cursor.getString(cursor.getColumnIndex("Owner"));
                name[i] = cursor.getString(cursor.getColumnIndex("ShopName"));
                if (owner[i].equals(id_data)){
                    MyShop shop = new MyShop();
                    shop.setName(name[i]);
                    shop.setOwner(owner[i]);
                    list.add(shop);
                }
                else{}
                i++;
            }while (cursor.moveToNext());
        }
        cursor.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.user_item, menu);
            return true;
        }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
        }
        return true;
    }
}
