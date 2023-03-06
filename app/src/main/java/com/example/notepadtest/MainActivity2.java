package com.example.notepadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    MyDBHelper myDBHelper;
    SQLiteDatabase sqlDB;
    Button notepadSave;
    EditText textTitle, textContent;
    DateNotepad dateNotepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        notepadSave = findViewById(R.id.notepadSave);
        textTitle = findViewById(R.id.textTitle);
        textContent = findViewById(R.id.textContent);
        myDBHelper = new MyDBHelper(this);
    }

    public void saveNotepad(View view){
        sqlDB = myDBHelper.getWritableDatabase();
        if(textTitle.getText().toString().replace(" ","").equals("")){
            Toast.makeText(getApplicationContext(), "제목을 입력하세요", Toast.LENGTH_LONG).show();
        }else {
            String str = String.format("insert into noteT (uTitle, uContent) values ('%s', '%s')",
                    textTitle.getText().toString(), textContent.getText().toString());
            sqlDB.execSQL(str);
            sqlDB.close();

            Toast.makeText(getApplicationContext(), "메모저장완료", Toast.LENGTH_SHORT).show();
            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
        }
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(in);
        finish();
    }
}