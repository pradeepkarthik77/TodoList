package com.example.todolist;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DbAdapter extends CursorAdapter
{
    public DbAdapter(Context context,Cursor cursor)
    {
        super(context,cursor,0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup)
    {
        return LayoutInflater.from(context).inflate(R.layout.itemtodo,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {

        TextView content = (TextView) view.findViewById(R.id.task_title);

        String db_content;

        try {
            db_content = cursor.getString(cursor.getColumnIndexOrThrow("Content"));
        }
        catch (Exception e)
        {
            db_content = "";
            System.out.println("Cannot find Content");
        }

        content.setText(db_content);
    }

}
