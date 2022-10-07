package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
{

    private ListView list_todo;

    private Context context;

    private FloatingActionButton add_task;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_task = findViewById(R.id.add_float);

        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        });

        list_todo = findViewById(R.id.list_todo);

        Dbclass db_class = new Dbclass(this);

        Cursor cursor = db_class.getCursor();

        DbAdapter db_adapter = new DbAdapter(this,cursor);

        list_todo.setAdapter(db_adapter);

    }
}