package com.example.lenovo.mymeituanapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatShop extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_shop);
        dbHelper = new MyDatabaseHelper(this,"Shops.db",null,1);
        Button CShop = (Button)findViewById(R.id.CShop);
        final EditText Sname = (EditText)findViewById(R.id.Sname);
        CShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Sname.getText().toString();
                if(name.equals("")){
                }
                else{
                    Intent intent = getIntent();
                    String towner = intent.getStringExtra("owner");
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("ShopName",name);
                    values.put("Owner",towner);
                    db.insert("Shops",null,values);
                    values.clear();
                }


            }
        });
    }
}
