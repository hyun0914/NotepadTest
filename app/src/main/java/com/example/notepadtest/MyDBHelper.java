package com.example.notepadtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {
    public MyDBHelper(Context context) {super(context, "noteDB", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table noteT (uNO integer primary key autoincrement, uTitle String(40) not null, uContent String not null);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists noteT");
        onCreate(db);
    }
}
