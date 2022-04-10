    package com.example.notepadtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity3 extends AppCompatActivity {

    EditText modifyTitle, modifyContent;
    MyDBHelper myDBHelper;
    Integer no;
    DateNotepad dateNotepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        modifyTitle = findViewById(R.id.modifyTitle);
        modifyContent = findViewById(R.id.modifyContent);

        Intent in = getIntent();
        no = in.getExtras().getInt("No");
        String title = in.getStringExtra("Title");
        String content = in.getStringExtra("Content");
        Log.d("getString", no+"");
        Log.d("getString", title);
        Log.d("getString", content);

        modifyTitle.setText(title);
        modifyContent.setText(content);
        myDBHelper = new MyDBHelper(this);
    }

    public void modifyNotepad(View view) {
        SQLiteDatabase sqlDB = myDBHelper.getWritableDatabase();
        if(modifyTitle.getText().toString().replace(" ","").equals("")) {
            Toast.makeText(getApplicationContext(),"제목을 입력하세요", Toast.LENGTH_LONG).show();
        }else{
            String sql = String.format("update noteT set uTitle = '%s', uContent = '%s' where uNO= %d",
                    modifyTitle.getText().toString(), modifyContent.getText().toString(), no);
            sqlDB.execSQL(sql);
            sqlDB.close();

//            SimpleDateFormat sformat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
//            Date date = new Date();
//            String dTime = sformat.format(date);
//            Log.d("DATETIMEDD", dTime);

            Toast.makeText(getApplicationContext(), "수정완료", Toast.LENGTH_LONG).show();
            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
            finish();
        }
    }

    public void deleteNotepad(View view){
        SQLiteDatabase sqlDB = myDBHelper.getWritableDatabase();
        String sql = String.format("delete from noteT where uNO = %d", no);
        Log.d("getStringD", no+"");
        sqlDB.execSQL(sql);
        sqlDB.close();
        Toast.makeText(getApplicationContext(), "삭제완료", Toast.LENGTH_LONG).show();
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(in);
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent in = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(in);
        finish();
    }
}