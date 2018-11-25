package com.example.lenovo.mymeituanapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class log extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        dbHelper = new MyDatabaseHelper(this,"Users.db",null,1);
        Button btn1 = (Button)findViewById(R.id.btn1);
        Button btn2 = (Button)findViewById(R.id.btn2);
        final EditText thename = (EditText)findViewById(R.id.thename);
        final EditText thecode = (EditText)findViewById(R.id.thecode);
        final EditText phone = (EditText)findViewById(R.id.phone);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Nametext = thename.getText().toString();
                String Codetext = thecode.getText().toString();
                Editable Phonetext = phone.getText();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor;
                cursor = db.query("Users",new String[]{"id"},null,null,null,null,null);
                if (cursor.moveToFirst()){
                    do {
                        String name = cursor.getString(cursor.getColumnIndex("id"));
                        if (name.equals(Nametext)){
                            Toast.makeText(log.this,"该用户名已注册",Toast.LENGTH_SHORT).show();
                        }
                    }while (cursor.moveToNext());
                }
                cursor.close();
                if(Nametext.equals(checkUsername(Nametext))||Codetext.equals("")||Phonetext.length()!=11){
                    Toast.makeText(log.this,"请填入正确信息",Toast.LENGTH_SHORT).show();
                }
                else {
                    ContentValues values = new ContentValues();
                    values.put("id",Nametext);
                    values.put("Code",Codetext);
                    db.insert("Users",null,values);
                    values.clear();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(log.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public static boolean checkUsername(String username) {
        String regex = "([a-zA-Z0-9]{6,12})";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(username);
        return m.matches();
    }
}
