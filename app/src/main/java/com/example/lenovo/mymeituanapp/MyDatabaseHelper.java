package com.example.lenovo.mymeituanapp;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String CREATE_USERS = "create table Users ("
            + "id text, "
            + "Code text,"
            + "Phone integer)";
    private static final String CREATE_ORDERS = "create table Orders("
            + "id integer primary key autoincrement,"
            + "FoodName text,"
            + "Owner text,"
            + "Price real)";
    private static final String CREATE_FOODS = "create table Foods("
            + "id integer primary key autoincrement,"
            + "OFoodName text,"
            + "Shop text,"
            + "owner text,"
            + "Price text)";
    private static final String CREATE_SHOPS = "create table Shops("
            + "id integer primary key autoincrement,"
            + "ShopName text,"
            + "Owner text)";

    private Context mcontext;
    public MyDatabaseHelper(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mcontext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS);
        db.execSQL(CREATE_ORDERS);
        db.execSQL(CREATE_FOODS);
        db.execSQL(CREATE_SHOPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Users");
        db.execSQL("drop table if exists Orders");
        db.execSQL("drop table if exists Foods");
        db.execSQL("drop table if exists Shops");

        onCreate(db);
    }
}
