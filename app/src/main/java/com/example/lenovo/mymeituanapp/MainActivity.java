package com.example.lenovo.mymeituanapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private MyDatabaseHelper dbHelper;
    private final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDatabaseHelper(this,"Users.db",null,1);
        Button BtnLog = (Button) findViewById(R.id.BtnLog);
        Button BtnSign = (Button)findViewById(R.id.BtnSign);
        final EditText EditName = (EditText)findViewById(R.id.EditName);
        final EditText EditCode = (EditText)findViewById(R.id.EditCode);

        BtnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String NameText = EditName.getText().toString();
                String CodeText = EditCode.getText().toString();
                Cursor cursor;
                cursor = db.query("Users",new String[]{"id","Code"},null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("id"));
                        String code = cursor.getString(cursor.getColumnIndex("Code"));
                        if (name.equals(NameText)&&code.equals(CodeText)){
                            Intent intent1 = new Intent(MainActivity.this,SecondActivity.class);
                            intent1.putExtra("ID",name);
                            startActivity(intent1);
                        }
                        else{}
                    }while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
        BtnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,log.class);
                startActivity(intent);
                finish();
            }
        });

    }

}
