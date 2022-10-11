package com.example.todolist;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity
{

    private ListView list_todo;

    private FloatingActionButton add_task;

    private AlertDialog.Builder dialog;

    private Dbclass db_class;

    private Context context;

    private DbAdapter db_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_task = findViewById(R.id.add_float);

        create_dialogbox();

        add_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                dialog.show();
            }
        });

        list_todo = findViewById(R.id.list_todo);

        db_class = new Dbclass(this);

        Cursor cursor = db_class.getCursor();

        db_adapter = new DbAdapter(this,cursor);

        list_todo.setAdapter(db_adapter);

        context = getApplicationContext();

    }

    public void deleteTask(View view)
    {
        View parent = (View) view.getParent();
        int position = list_todo.getPositionForView(parent)+1;
        db_class.delete_todo(position);
        Cursor c = db_class.getCursor();
        db_adapter.swapCursor(c);
        db_adapter.notifyDataSetChanged();
    }

    private void create_dialogbox()
    {
        Context newcontext = this;
        EditText task_edit = new EditText(this);
        dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Add a new Task");
        dialog.setMessage("What Do you want To Do next?");
        dialog.setView(task_edit);
        dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                String task = task_edit.getText().toString();
                db_class.insert_todo(task,db_adapter);
                //Have to refresh my Listview here

                Cursor cursor = db_class.getCursor();

                db_adapter.swapCursor(cursor);

                db_adapter.notifyDataSetChanged();

            }
        });

        dialog.setNegativeButton("Cancel",null);
        dialog.create();
    }



}