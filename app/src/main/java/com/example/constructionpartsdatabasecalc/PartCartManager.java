package com.example.constructionpartsdatabasecalc;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class PartCartManager extends SQLiteOpenHelper {
    public PartCartManager(Context context){
        super(context, "PartDB", null,1);
    }

    public void onCreate(SQLiteDatabase db){
        String sql = "create table PartTable(";
        sql += "id integer primary key autoincrement, ";
        sql += "name text, price text, store text)";
        db.execSQL(sql);
    }

    public void insert(String partName, String partPrice, String storeName){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into PartTable values(";
        sql += "null, '"+partName+"', '"+partPrice+"', '"+storeName+"')";
        db.execSQL(sql);
        db.close();
    }

    public void delete(String partName){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "delete from PartTable where name = '"+partName+"'";
        db.execSQL(sql);
        db.close();
    }

    public void updateByName(String name, String price, String store){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "update PartTable set price = '"+price+"'";
        sql += "where name = '"+name+"'";
        sql += "where store = '"+store+"'";
        db.execSQL(sql);
        db.close();
    }

    public ArrayList<String> getParts(){
        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from PartTable";
        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            list.add(name);
        }
        db.close();
        return list;
    }

    public String[] get(String partName){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from PartTable where name = '"+partName+"'";
        Cursor cursor = db.rawQuery(sql, null);
        String[] entry = new String[3];
        if (cursor.moveToFirst()){
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String store = cursor.getString(3);
            entry[0] = name;
            entry[1] = price;
            entry[2] = store;
        }
        db.close();
        return entry;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

}

