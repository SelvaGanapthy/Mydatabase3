package com.example.dell.mydatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dell on 5/3/2017.
 */

public class Myhelper extends SQLiteOpenHelper {
    SQLiteDatabase database;


    public Myhelper(Context context) {
        super(context, "Create", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table selva1(id integer primary key autoincrement,name text not null,pass text not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists selva1");
        onCreate(db);
    }

    public long insertUser(Info info) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("name", info.getUsername());
        values.put("pass",info.getPassword());
        long g = database.insert("selva1", null, values);
        return g;
    }


}
