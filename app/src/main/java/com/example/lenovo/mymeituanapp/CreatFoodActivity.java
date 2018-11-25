package com.example.lenovo.mymeituanapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreatFoodActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_food);
        dbHelper = new MyDatabaseHelper(this,"Foods.db",null,1);
        Button CFood = (Button)findViewById(R.id.CFood);
        final EditText Cname = (EditText)findViewById(R.id.Cname);
        final EditText CPrince = (EditText)findViewById(R.id.CPrice);
        CFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Cname.getText().toString();
                String price = CPrince.getText().toString();
                if(name.equals("")||price.equals("")){
                }
                else{
                    Intent intent = getIntent();
                    String owner = intent.getStringExtra("owner");
                    String shop = intent.getStringExtra("shopname");
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("OFoodName",name);
                    values.put("owner",owner);
                    values.put("Price",price);
                    values.put("Shop",shop);
                    db.insert("Foods",null,values);
                    values.clear();
                }


            }
        });

    }
}
