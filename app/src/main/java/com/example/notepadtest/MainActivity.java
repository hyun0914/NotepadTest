package com.example.notepadtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int HANDLE_MSG_CHANGE_COUNT = 1000;
    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    RecyclerView recyclerView;
    NotepadAdapter adapter;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDBHelper = new MyDBHelper(this);

        handler = new Handler(){

            @Override
            public void handleMessage(@NonNull Message msg) {
                if(msg.what == HANDLE_MSG_CHANGE_COUNT){
                    Notepad notepad = (Notepad)msg.obj;
                    Log.d("getStringma", "번호" + notepad.getNo());
                    Log.d("getStringma", "제목 " + notepad.getTitle());
                    Log.d("getStringma", "내용" + notepad.getContent());
                    Intent in = new Intent(getApplicationContext(), MainActivity3.class);
                    in.putExtra("No", notepad.getNo());
                    in.putExtra("Title", notepad.getTitle());
                    in.putExtra("Content", notepad.getContent());
                    startActivity(in);
                }
                finish();
            }
        };

        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NotepadAdapter(this, handler);

        sqlDB = myDBHelper.getWritableDatabase();
        Cursor cursor = sqlDB.rawQuery("select * from noteT", null);

        while (cursor.moveToNext()) {
            Integer strNo = cursor.getInt(0);
            String strTitle = cursor.getString(1);
            String strContent = cursor.getString(2);
           adapter.addItem(new Notepad(strNo, strTitle, strContent));
        }
        recyclerView.setAdapter(adapter);
        cursor.close();
        sqlDB.close();
    }

    public void writing(View view){
        Intent in = new Intent(getApplicationContext(),MainActivity2.class);
        startActivity(in);
        finish();
    }
}