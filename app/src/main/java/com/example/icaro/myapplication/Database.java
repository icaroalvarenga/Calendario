package com.example.icaro.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    public Database(Context context){
        super(context, "calendario", null, 3);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(" CREATE TABLE 'feriado' ('_id' INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, 'ano' INTEGER NOT NULL, 'mes' INTEGER NOT NULL, 'dia' INTEGER NOT NULL);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + "feriado");
        onCreate(db);
    }




}
